package com.mycompany.app;

import com.mycompany.app.controller.HelloWorldHandler;
import com.mycompany.app.dao.SaleDAO;
import com.mycompany.app.model.Sale;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class App
{
    public static void main( String[] args ) {
        try{
            HttpServer servidor = HttpServer.create(
                    new InetSocketAddress( 8080),0
            );

            servidor.createContext("/helloWorld", new HelloWorldHandler());

            Connection connection =  DriverManager.getConnection("","","");

            SaleDAO saleDAO = new SaleDAO(connection);
            Sale sale = saleDAO.getById(1);
            sale.setSaleItems(saleDAO.getSaleItemsBySaleId(1));

            servidor.setExecutor(null);
            servidor.start();
            System.out.println("Servidor rodando na porta 8080");
        }catch( IOException e ){
            System.out.println(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}