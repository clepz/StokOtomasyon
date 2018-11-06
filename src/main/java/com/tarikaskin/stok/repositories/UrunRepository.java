package com.tarikaskin.stok.repositories;

import com.tarikaskin.stok.models.Urun;
import org.springframework.data.repository.CrudRepository;

public interface UrunRepository extends CrudRepository<Urun,Integer> {

    Urun findByBarkod(String barkod);
}
