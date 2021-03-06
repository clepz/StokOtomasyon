package com.tarikaskin.stok.controllers;

import com.tarikaskin.stok.models.*;
import com.tarikaskin.stok.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.GenericStoredProcedure;
import org.springframework.jdbc.object.StoredProcedure;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@RestController
public class denemecontroller {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UrunRepository urunRepository;

    @Autowired
    BundleRepository bundleRepository;

    @Autowired
    BolumRepository bolumRepository;

    @Autowired
    DataSource dataSource;

    @Autowired
    MusteriRepository musteriRepository;

    @Autowired
    FaturaRepository faturaRepository;

    @GetMapping(value = "/rol")
    String girisKontrol(@RequestParam String username){
        User user = userRepository.findByUsername(username);
        if(user.getPassword().equals(user.getPassword()))
            return user.getRol() + "," + user.getKullaniciId();
        return "-1";
    }

    @GetMapping(value = "/user/urun")
    Urun urunAl(@RequestParam String barkod) {
        Urun urun = urunRepository.findByBarkod(barkod);
        return urun;
    }


    @GetMapping(value = "/kasiyer")
    String yapp(){
        return "kasiyer oldu";
    }

    @GetMapping(value = "/user/uruncek")
    Urun urunAl(){
        return urunRepository.findByBarkod("1111111111111111");
    }
    @GetMapping(value = "/user/bundlecek")
    Bundle bundleAl(){
        return bundleRepository.findByBarkod("1111111111111111");
    }

    @PostMapping(value = "/admin/ekle")
    int ekle(@RequestBody User user)
    {
        List<SqlParameter> parameters = Arrays.asList(new SqlParameter(Types.INTEGER), new SqlParameter(Types.VARCHAR), new SqlParameter(Types.VARCHAR),
                new SqlParameter(Types.VARCHAR));
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        Map<String, Object> t = jdbcTemplate.call(con -> {
            CallableStatement callableStatement = con.prepareCall("{call kullanici_ekle (?,?,?,?)}");
            callableStatement.setInt(1, user.getKullaniciId());
            callableStatement.setString(2,user.getUsername());
            callableStatement.setString(3,user.getPassword());
            callableStatement.setString(4,user.getRol());
            return callableStatement;
        },parameters);
        return 1;
    }

    @PostMapping(value = "/admin/guncelle")
    String kullaniciGuncelle(@RequestBody User user) {
        List<SqlParameter> parameters = Arrays.asList(new SqlParameter(Types.INTEGER), new SqlParameter(Types.VARCHAR), new SqlParameter(Types.VARCHAR));
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        User kullanici = userRepository.findByKullaniciId(user.getKullaniciId());
        if(kullanici == null)
            return "hatalı id";
        jdbcTemplate.call(connection -> {
            CallableStatement callableStatement = connection.prepareCall("{call kullanici_guncelle(?,?,?)}");
            callableStatement.setInt(1,user.getKullaniciId());
            callableStatement.setString(2,user.getPassword());
            callableStatement.setString(3,user.getRol());
            return callableStatement;
        },parameters);
        return "başarılı";
    }

    @GetMapping(value = "/admin/sil")
    String kullaniciSil(@RequestParam int id){
        System.out.println(id);
        User user = userRepository.findByKullaniciId(id);
        if (user == null)
            return "başarısız";
        List<SqlParameter> parameters = Arrays.asList(new SqlParameter(Types.VARCHAR));
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.call(con -> {
            CallableStatement callableStatement = con.prepareCall("{call kullanici_sil (?)}");
            callableStatement.setInt(1, id);
            return callableStatement;
        },parameters);
        return "başarılı";
    }

    @GetMapping(value = "/user/tumurunal")
    List<Urun> tumUrunAl(){
        return urunRepository.findAll();
    }

    @GetMapping(value = "/user/tumbolumnoal")
    List<String> tumBolumNolarıAl(){
        List<String> list = bolumRepository.findBolumNo().stream().map(Bolum::getBolumNo).collect(toList());
        return list;
    }

    List<Fatura> faturaList;
    List<Kasiyer> kasiyerList;

    @PostMapping(value = "/user/urunsat")
    String urunSat(@RequestBody SatisHazirlik satisHazirlik){
        if(faturaList == null){
            faturaList = new ArrayList<>();
        }
        if(kasiyerList == null){
            kasiyerList = new ArrayList<>();
        }
        Urun ayniUrun = urunRepository.findByBarkod(satisHazirlik.getBarkod());
        float kazanilanPara = satisHazirlik.getSatilanAdet() * ayniUrun.getFiyat();
        int kalanAdet = ayniUrun.getAdet()-satisHazirlik.getSatilanAdet();
        Musteri eskiMusteri = musteriRepository.findByTc(satisHazirlik.getMusteri().getTc());
        System.out.println(satisHazirlik.getMusteri().getTc());
        System.out.println(ayniUrun.getFiyat());
        if(eskiMusteri == null){
            System.out.println(satisHazirlik.getMusteri().getCinsiyet());
            musteriRepository.save(satisHazirlik.getMusteri());
        }
        int faturaNo= -1;
        for(Fatura fatura : faturaList){
            if(fatura.getMusteriTc().equals(satisHazirlik.getMusteri().getTc())) {
                faturaNo = fatura.getFaturaNo();
                break;
            }
            else if(faturaList.size() == faturaList.lastIndexOf(fatura)+1){
                try {
                    Connection con = dataSource.getConnection();
                    Statement st = con.createStatement();
                    ResultSet rs = st.executeQuery("select FATURALAR_SEQ.nextval from dual");
                    rs.next();
                    faturaNo = rs.getInt(1);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        if(faturaNo == -1){
            Connection con = null;
            try {
                con = dataSource.getConnection();
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("select FATURALAR_SEQ.nextval from dual");
                rs.next();
                faturaNo = rs.getInt(1);
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        String faturaBilgisi = ayniUrun.getBarkod()+" "+ayniUrun.getMarka() + " " + ayniUrun.getModel() + " " + ayniUrun.getAciklama();
        Fatura fatura = new Fatura(satisHazirlik.getMusteri().getTc(),satisHazirlik.getSatilanAdet()*ayniUrun.getFiyat(),faturaBilgisi,satisHazirlik.getKullaniciId(),satisHazirlik.getSatilanAdet(),faturaNo);
        Kasiyer kasiyer = new Kasiyer(faturaBilgisi,satisHazirlik.getSatilanAdet(),ayniUrun.getFiyat(),satisHazirlik.getSatilanAdet()*ayniUrun.getFiyat(),ayniUrun.getSeri_no(),satisHazirlik.getMusteri().getTc());
        faturaList.add(fatura);
        kasiyerList.add(kasiyer);
        urunRepository.urunSat(kalanAdet,satisHazirlik.getBarkod());
        return "Başarılı";
    }

    @PostMapping(value = "/admin/urunekle")
    void urunEkle(@RequestBody Urun urun){
        Urun eskiUrun = urunRepository.findByBarkod(urun.getBarkod());
        if(eskiUrun == null){
            urunRepository.save(urun);
            return;
        }
        int adet = eskiUrun.getAdet() + urun.getAdet();
        urunRepository.urunGuncelle(urun.getAciklama(),urun.getBolum_no(),urun.getBundleVarMi(),urun.getFiyat(),urun.getMarka(),urun.getModel(),urun.getSeri_no(),urun.getAdet(),urun.getBarkod());
    }

    @GetMapping(value = "/kasiyer/musterileriAl")
    List<Musteri> musteriAdSoyadAl(){
        if(kasiyerList == null || kasiyerList.isEmpty())
            return null;
        List<Musteri> liste = new ArrayList<>();
        List<String> tc = faturaList.stream().map(Fatura::getMusteriTc).distinct().collect(toList());

        while (!tc.isEmpty()) {
            liste.add(musteriRepository.findByTc(tc.remove(0)));
        }
        return liste;
    }

    @GetMapping(value = "/kasiyer/urunsatistamamla")
    KasiyerEkranBilgiler satisitamamla(@RequestParam String tc){
        List<Fatura> listeFatura ;
        Musteri musteri = musteriRepository.findByTc(tc);
        List<Kasiyer> listeKasiyer ;


        listeFatura =  faturaList.stream().filter(u -> u.getMusteriTc().equals(tc)).map(fatura -> {
            try {
                return (Fatura)fatura.clone();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
            return null;
        }).collect(toList());
        listeKasiyer = kasiyerList.stream().filter(u -> u.getMusteriTc().equals(tc)).map(kasiyer -> {
            try {
                return (Kasiyer)kasiyer.clone();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
            return null;
        }).collect(toList());
        KasiyerEkranBilgiler bilgiler = new KasiyerEkranBilgiler(musteri,listeKasiyer,listeFatura);
        return bilgiler;
    }

    @GetMapping(value = "/kasiyer/urunsatisonay")
    void onay(@RequestParam String tc){
        List<Fatura> listeFatura ;
        listeFatura =  faturaList.stream().filter(u -> u.getMusteriTc().equals(tc)).map(fatura -> {
            try {
                return (Fatura)fatura.clone();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
            return null;
        }).collect(Collectors.toList());
        kasiyerList.removeAll(kasiyerList.stream().filter(u -> u.getMusteriTc().equals(tc)).collect(toList()));
        faturaList.removeAll(faturaList.stream().filter(u -> u.getMusteriTc().equals(tc)).collect(toList()));
        listeFatura.forEach(f -> faturaRepository.kaydet(f.getMusteriTc(),f.getFaturaTutari(),f.getFaturaBilgisi(),f.getKullaniciId(),f.getAdet(),f.getFaturaNo()));
    }

    @GetMapping(value = "/user/musterilistele")
    List<Musteri> musteriListele(){
        return musteriRepository.hepsiniAl();
    }

    @GetMapping(value = "/user/musterial")
    Musteri musteriAl(@RequestParam String tc){
        return musteriRepository.findByTc(tc);
    }


    @GetMapping(value = "/admin/analiztekkisial")
    Analiz tekKisiAnaliz(@RequestParam("adi") String adi){
        String gidecek = faturaRepository.analizKullaniciBilgiAl(adi);
        System.out.println(gidecek);
        String[] a = gidecek.split(",");
        return new Analiz(a[0],Double.valueOf(a[1]),a[2]);
    }

    @GetMapping(value = "/admin/analizherkesal")
    List<Analiz> analizHerkesAl(){
        List<Analiz> herkes = faturaRepository.analizTumKullaniciBilgiAl();
        return herkes;
    }

}
