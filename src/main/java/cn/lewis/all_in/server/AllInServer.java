package cn.lewis.all_in.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@SpringBootApplication
@ComponentScan(basePackages = {"cn.lewis.all_in"})
public class AllInServer {
    public static void main(String[] args) {
        SpringApplication.run(AllInServer.class, args);
    }
}
