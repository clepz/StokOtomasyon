package com.tarikaskin.stok.controllers;

import com.tarikaskin.stok.models.Bundle;
import com.tarikaskin.stok.models.Urun;
import com.tarikaskin.stok.models.User;
import com.tarikaskin.stok.repositories.BundleRepository;
import com.tarikaskin.stok.repositories.UrunRepository;
import com.tarikaskin.stok.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class denemecontroller {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UrunRepository urunRepository;

    @Autowired
    BundleRepository bundleRepository;

    @GetMapping(value = "/rol")
    String girisKontrol(@RequestParam String username){
        User user = userRepository.findByUsername(username);
        if(user.getPassword().equals(user.getPassword()))
            return user.getRol();
        return "-1";
    }

    @GetMapping(value = "/user/urun")
    Urun urunAl(@RequestParam String barkod) {
        Urun urun = urunRepository.findByBarkod(barkod);
        return urun;
    }


    @GetMapping(value = "/kasiyer")
    String yapp(){
        return "kasiyer oldu";
    }

    @GetMapping(value = "/user/uruncek")
    Urun urunAl(){
        return urunRepository.findByBarkod("1111111111111111");
    }
    @GetMapping(value = "/user/bundlecek")
    Bundle bundleAl(){
        return bundleRepository.findByBarkod("1111111111111111");
    }

    @PostMapping(value = "/user/ekle")
    int ekle(@RequestBody User user)
    {
        System.out.println(user.getUsername());
        userRepository.save(user);
        return 1;
    }


}
