package com.tarikaskin.stok.repositories;

import com.tarikaskin.stok.models.Bundle;
import org.springframework.data.repository.CrudRepository;

public interface BundleRepository extends CrudRepository<Bundle,String> {
    Bundle findByBarkod(String barkod);
}
