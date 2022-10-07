package com.yupi;

import com.yupi.server.CollectionServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.CompletableFuture;

/**
 * @author dhwc
 * @create 2022-10-02 12:20
 */
@SpringBootApplication
public class CollectionApplication implements CommandLineRunner {
    @Autowired
    CollectionServer collectionServer;

    public static void main(String[] args) {
        SpringApplication.run(CollectionApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        collectionServer.start();
    }
}
