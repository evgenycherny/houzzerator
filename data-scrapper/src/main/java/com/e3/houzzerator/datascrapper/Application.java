package com.e3.houzzerator.datascrapper;

import com.e3.houzzerator.datascrapper.download.ParametrizedHttpGet;
import com.e3.houzzerator.datascrapper.download.RestScanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Author:  etshiorny
 * Date:    6/25/16.
 */

@SpringBootApplication
public class Application implements CommandLineRunner {
    @Autowired
    private RestScanner restScanner;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    @Override
    public void run(String... strings) throws Exception {
        restScanner.setRequest(new ParametrizedHttpGet());
        System.out.println("Hello");
    }
}
