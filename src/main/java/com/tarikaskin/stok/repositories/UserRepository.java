package com.tarikaskin.stok.repositories;

import com.tarikaskin.stok.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,String> {

    User findByUsername(String username);
}
