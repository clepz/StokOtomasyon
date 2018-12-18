package com.tarikaskin.stok.repositories;

import com.tarikaskin.stok.models.Fatura;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface FaturaRepository extends CrudRepository<Fatura, Integer> {

    Fatura findByFaturaId(int faturaId);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "insert into FATURA (FATURA_ID,MUSTERI_TC,FATURA_TUTARI,FATURA_BILGISI,KULLANICI_ID) values(FATURA_SEQ.nextval ,:b,:f,:d,:e)")
    void kaydet(@Param("b") String tc,@Param("f") float tutar,@Param("d") String bilgi,@Param("e") int kullaniciId);

}
