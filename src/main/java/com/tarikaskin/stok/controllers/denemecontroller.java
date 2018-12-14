package com.tarikaskin.stok.controllers;

import com.tarikaskin.stok.models.Bundle;
import com.tarikaskin.stok.models.Urun;
import com.tarikaskin.stok.models.User;
import com.tarikaskin.stok.repositories.BundleRepository;
import com.tarikaskin.stok.repositories.UrunRepository;
import com.tarikaskin.stok.repositories.UserRepository;
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
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
public class denemecontroller {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UrunRepository urunRepository;

    @Autowired
    BundleRepository bundleRepository;

    @Autowired
    DataSource dataSource;


    @GetMapping(value = "/rol")
    String girisKontrol(@RequestParam String username){
        User user = userRepository.findByUsername(username);
        if(user.getPassword().equals(user.getPassword()))
            return user.getRol();
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
        List<SqlParameter> parameters = Arrays.asList(new SqlParameter(Types.INTEGER), new SqlParameter(Types.VARCHAR), new SqlParameter(Types.VARCHAR), new SqlParameter(Types.VARCHAR));
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        Map<String, Object> t = jdbcTemplate.call(new CallableStatementCreator() {
            @Override
            public CallableStatement createCallableStatement(Connection con) throws SQLException {
                CallableStatement callableStatement = con.prepareCall("{call kullanici_ekle (?,?,?,?)}");
                callableStatement.setInt(1, user.getKullaniciId());
                callableStatement.setString(2,user.getUsername());
                callableStatement.setString(3,user.getPassword());
                callableStatement.setString(4,user.getRol());
                return callableStatement;
            }
        },parameters);
        return 1;
    }

    @PostMapping(value = "/admin/guncelle")
    void kullaniciGuncelle(@RequestBody User user) {
        List<SqlParameter> parameters = Arrays.asList(new SqlParameter(Types.INTEGER), new SqlParameter(Types.VARCHAR), new SqlParameter(Types.VARCHAR));
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        jdbcTemplate.call(connection -> {
            CallableStatement callableStatement = connection.prepareCall("{call kullanici_guncelle(?,?,?)}");
            callableStatement.setInt(1,user.getKullaniciId());
            callableStatement.setString(2,user.getPassword());
            callableStatement.setString(3,user.getRol());
            return callableStatement;
        },parameters);


    }

    @GetMapping(value = "/admin/sil")
    void kullaniciSil(@RequestParam int id){
        System.out.println(id);
        List<SqlParameter> parameters = Arrays.asList(new SqlParameter(Types.VARCHAR));
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        Map<String, Object> t = jdbcTemplate.call(new CallableStatementCreator() {
            @Override
            public CallableStatement createCallableStatement(Connection con) throws SQLException {
                CallableStatement callableStatement = con.prepareCall("{call kullanici_sil (?)}");
                callableStatement.setInt(1, id);
                return callableStatement;
            }
        },parameters);
    }

}
