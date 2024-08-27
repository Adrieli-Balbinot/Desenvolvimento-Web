package com.mycompany.app.dao;

import com.mycompany.app.model.Product;

import java.sql.Connection;
import java.util.List;

public class ProductDAO extends GenericDAOImpl<Product> implements GenericDAO<Product>{

    private Connection connection;
    private final String TABLE_NAME = "product";

    private final List<String> COLUMNS = List.of("description", "value", "productType");

    public ProductDAO(Connection connection) {
        super(Product::new, connection);
        super.tableName = TABLE_NAME;
        super.columns = COLUMNS;
    }

}
