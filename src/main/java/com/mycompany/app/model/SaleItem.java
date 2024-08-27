package com.mycompany.app.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SaleItem implements Entity {
    private int id;
    private Product product;
    private int quantity;
    private double percentualDiscount;

    // Construtor principal
    public SaleItem(int id, Product product, int quantity, double percentualDiscount) {
        this.id = id;
        this.product = product;
        this.quantity = quantity;
        this.percentualDiscount = percentualDiscount;
    }

    // Construtor para criar a partir de ResultSet
    public SaleItem(ResultSet rs) throws SQLException {
        this.id = rs.getInt("id");
        this.product = new Product(rs); // Supondo que o Product tenha um construtor que aceite ResultSet
        this.quantity = rs.getInt("quantity");
        this.percentualDiscount = rs.getDouble("percentual_discount");
    }

    public SaleItem() {

    }

    // Getters
    public int getId() {
        return id;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPercentualDiscount() {
        return percentualDiscount;
    }

    // Setters
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPercentualDiscount(double percentualDiscount) {
        this.percentualDiscount = percentualDiscount;
    }

    // Método para construir a partir de ResultSet
    @Override
    public Entity constructFromResultSet(ResultSet rs) throws SQLException {
        return new SaleItem(rs);
    }

    // Método para preparar a instrução SQL
    @Override
    public PreparedStatement prepareStatement(PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setInt(1, getId());
        preparedStatement.setInt(2, getProduct().getId());
        preparedStatement.setInt(3, getQuantity());
        preparedStatement.setDouble(4, getPercentualDiscount());
        return preparedStatement;
    }

    // Método toString para representação textual
    @Override
    public String toString() {
        return "SaleItem{" +
                "id=" + id +
                ", product=" + product.getDescription() +
                ", quantity=" + quantity +
                ", percentualDiscount=" + percentualDiscount +
                '}';
    }
}
