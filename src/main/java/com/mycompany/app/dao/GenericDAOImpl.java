package com.mycompany.app.dao;

import com.mycompany.app.model.Entity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public abstract class GenericDAOImpl<T extends Entity> implements GenericDAO<T> {

    protected Connection connection;
    protected String tableName;

    protected List<String> columns;
    private Supplier<T> supplier;


    public GenericDAOImpl(Supplier<T> supplier, Connection connection) {
        this.supplier = supplier;
        this.connection = connection;
    }


    @Override
    public List<T> getAll() {
        List<T> productTypes = new ArrayList<>();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM " + tableName);
            while (rs.next()) {
                T productType = (T) supplier.get().constructFromResultSet(rs);
                productTypes.add(productType);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productTypes;
    }

    @Override
    public void upsert(T object) {

    }

    @Override
    public T getById(int id) {
        T object = null;
        try {
            PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM " + tableName + " WHERE id = ?");
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                object = (T) supplier.get().constructFromResultSet(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return object;
    }

    @Override
    public void delete(int id) {
        try {
            PreparedStatement pstmt =
                    connection.prepareStatement("DELETE FROM "
                            + tableName
                            + " WHERE id = ?");
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
