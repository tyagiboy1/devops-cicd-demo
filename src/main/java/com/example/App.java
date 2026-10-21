package com.example;

import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

public class App {
    public static void main(String[] args) throws IOException {
        int port = Integer.parseInt(
            System.getenv().getOrDefault("PORT", "8080")
        );

        HttpServer server = HttpServer.create(
            new InetSocketAddress("0.0.0.0", port), 0
        );

        server.createContext("/", exchange -> {
            byte[] response =
                "Hello from my DevOps CI/CD pipeline!\n"
                .getBytes(StandardCharsets.UTF_8);

            exchange.getResponseHeaders().set(
                "Content-Type", "text/plain; charset=utf-8"
            );

            exchange.sendResponseHeaders(200, response.length);

            try (var body = exchange.getResponseBody()) {
                body.write(response);
            }
        });

        server.start();
        System.out.println("Application listening on port " + port);
    }
}
