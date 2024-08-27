package com.mycompany.app.controller;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;

public   class HelloWorldHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange)
            throws IOException {
        byte[] helloWordByte =
                "Hello World.".getBytes();

        exchange.sendResponseHeaders(200, helloWordByte.length);

        OutputStream OutputStream = exchange.getResponseBody();
        OutputStream.write(helloWordByte);
        OutputStream.close();
    }
}
