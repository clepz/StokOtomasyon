package com.tarikaskin.stok.repositories;

import com.tarikaskin.stok.models.User;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;

public interface UserRepository extends CrudRepository<User,String> {

    User findByUsername(String username);
}
