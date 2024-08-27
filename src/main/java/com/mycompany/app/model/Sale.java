package com.mycompany.app.model;

import com.mycompany.app.dao.SaleDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public class Sale implements Entity {
    private int id;
    private List<SaleItem> saleItems;
    private Timestamp insertAt;

    // Construtor principal
    public Sale(int id, List<SaleItem> saleItems, Timestamp insertAt) {
        this.id = id;
        this.saleItems = saleItems;
        this.insertAt = insertAt;
    }

    // Construtor para criar a partir de ResultSet
    public Sale(ResultSet rs) throws SQLException {
        this.id = rs.getInt("id");
        this.insertAt = rs.getTimestamp("insertAt");

    }

    public Sale() {

    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<SaleItem> getSaleItems() {
        return saleItems;
    }

    public void setSaleItems(List<SaleItem> saleItems) {
        this.saleItems = saleItems;
    }

    public Timestamp getInsertAt() {
        return insertAt;
    }

    public void setInsertAt(Timestamp insertAt) {
        this.insertAt = insertAt;
    }

    // Método para construir a partir de ResultSet
    @Override
    public Entity constructFromResultSet(ResultSet rs) throws SQLException {
        return new Sale(rs);
    }

    // Método para preparar a instrução SQL
    @Override
    public PreparedStatement prepareStatement(PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setInt(1, getId());
        preparedStatement.setTimestamp(2, getInsertAt());
        return preparedStatement;
    }

    // Método toString para representação textual
    @Override
    public String toString() {
        StringBuilder saleItemsStr = new StringBuilder();
        for (SaleItem item : saleItems) {
            saleItemsStr.append(item.toString()).append(", ");
        }

        return "Sale{" +
                "id=" + id +
                ", saleItems=[" + (saleItemsStr.length() > 0 ? saleItemsStr.substring(0, saleItemsStr.length() - 2) : "") + "]" +
                ", insertAt=" + insertAt +
                '}';
    }
}
