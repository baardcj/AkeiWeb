package org.example.furniture.aeki;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@AllArgsConstructor
@SpringBootApplication
public class AekiApplication {


    private TestData testdata;


    public static void main(String[] args) {
        SpringApplication.run(AekiApplication.class, args);
    }

    @PostConstruct
    public void init() {
        testdata.init();
    }
}
