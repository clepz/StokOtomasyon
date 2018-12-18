package com.tarikaskin.stok.repositories;

import com.tarikaskin.stok.models.Urun;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UrunRepository extends CrudRepository<Urun,Integer> {

    Urun findByBarkod(String barkod);

    List<Urun> findAll();

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "update URUNLER set URUNLER.adet =:a where barkod =:b ")
    void urunSat(@Param("a") int yeniAdet,@Param("b") String barkod);


    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "update URUNLER set URUNLER.ACIKLAMA = :a, URUNLER.BOLUM_NO = :b," +
            "URUNLER.BUNDLE = :c, URUNLER.FIYAT = :d, URUNLER.MARKA = :e, URUNLER.MODEL = :f, URUNLER.SERI_NO = :g, URUNLER.ADET = :h")
    void urunGuncelle(@Param("a") String aciklama,@Param("b") String bolumNo,@Param("c") int bundle,@Param("d") float fiyat,
                      @Param("e") String marka, @Param("f")String model,@Param("g") String seriNo,@Param("h") int adet );


}
