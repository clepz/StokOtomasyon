package com.tarikaskin.stok;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StokApplication {

    public static void main(String[] args) {
        SpringApplication.run(StokApplication.class, args);


    }

    /*@Bean
    public WebMvcConfigurer corsConfigurer(){
        return new WebMvcConfigurerAdapter(){
            @Override
            public void addCorsMappings(CorsRegistry registry){
                registry.addMapping("/api*").allowedOrigins("http://localhost:8080");
            }
        };
    }*/
}
