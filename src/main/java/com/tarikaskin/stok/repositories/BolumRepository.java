package com.tarikaskin.stok.repositories;

import com.tarikaskin.stok.models.Bolum;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BolumRepository extends CrudRepository<Bolum,String> {

    @Query("select new Bolum(b.bolumNo) from Bolum b")
    List<Bolum> findBolumNo();
}
