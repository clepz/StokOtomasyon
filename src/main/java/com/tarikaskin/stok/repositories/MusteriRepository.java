package com.tarikaskin.stok.repositories;

import com.tarikaskin.stok.models.Musteri;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MusteriRepository extends CrudRepository<Musteri,Integer> {

        Musteri findByTc(String tc);

        @Query(nativeQuery = true, value = "select * from MUSTERI")
        List<Musteri> hepsiniAl();

}
