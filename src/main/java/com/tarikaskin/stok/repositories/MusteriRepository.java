package com.tarikaskin.stok.repositories;

import com.tarikaskin.stok.models.Musteri;
import org.springframework.data.repository.CrudRepository;

public interface MusteriRepository extends CrudRepository<Musteri,Integer> {

        Musteri findByTc(String tc);

}
