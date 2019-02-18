package com.tarikaskin.stok.repositories;

import com.tarikaskin.stok.models.Analiz;
import com.tarikaskin.stok.models.Fatura;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface FaturaRepository extends CrudRepository<Fatura, Integer> {

    Fatura findByFaturaId(int faturaId);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "insert into FATURA (FATURA_ID,MUSTERI_TC,FATURA_TUTARI,FATURA_BILGISI,KULLANICI_ID,ADET,FATURA_NO) values(FATURA_SEQ.nextval,:b,:f,:d,:e,:g,:h)")
    void kaydet(@Param("b") String tc,@Param("f") float tutar,@Param("d") String bilgi,@Param("e") int kullaniciId,@Param("g") int adet, @Param("h") int faturaNo);

    @Query(nativeQuery = false, value = "select new com.tarikaskin.stok.models.Analiz(b.username,sum(a.faturaTutari),b.rol) from com.tarikaskin.stok.models.Fatura as a, com.tarikaskin.stok.models.User as b where a.kullaniciId=b.kullaniciId group by b.kullaniciId,b.username,b.rol")
    List<Analiz> analizTumKullaniciBilgiAl();

    @Query(nativeQuery = true, value = "select b.kullanici_adi,sum(a.fatura_tutari),b.rol from fatura a, kullanicilar b where a.kullanici_id = b.kullanici_id and b.kullanici_adi = :a group by b.kullanici_id, b.kullanici_adi, b.rol")
    String analizKullaniciBilgiAl(@Param("a") String adi);

}
