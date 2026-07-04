package br.ufscar.dc.compraevenda.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ClientApplication {
    public static void main(String[] args) {
        SpringApplication.run(ClientApplication.class, args);
        System.out.println("=============================================");
        System.out.println("🚀 StyleStore Client - T8");
        System.out.println("📍 Acesse: http://localhost:8081");
        System.out.println("📡 API Base: http://localhost:8080/api");
        System.out.println("=============================================");
    }
}
