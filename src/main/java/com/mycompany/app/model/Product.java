package com.mycompany.app.model;

import com.mycompany.app.model.ProductType;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Product implements Entity {

        private int id;
        private ProductType productType;
        private String description;
        private double value;

        // Construtor principal
        public Product(int id, ProductType productType, String description, double value) {
            this.id = id;
            this.productType = productType;
            this.description = description;
            this.value = value;
        }

        // Construtor para criar a partir de ResultSet
        public Product(ResultSet rs) throws SQLException {
            this.id = rs.getInt("id");
            this.productType = new ProductType(rs); // Supondo que ProductType tenha um construtor que aceite ResultSet
            this.description = rs.getString("description");
            this.value = rs.getDouble("value");
        }

    public Product() {

    }

    // Getters
        public int getId() {
            return id;
        }

        public ProductType getProductType() {
            return productType;
        }

        public String getDescription() {
            return description;
        }

        public double getValue() {
            return value;
        }

        // Setters
        public void setId(int id) {
            this.id = id;
        }

        public void setProductType(ProductType productType) {
            this.productType = productType;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public void setValue(double value) {
            this.value = value;
        }

        // Método para construir a partir de ResultSet
        @Override
        public Entity constructFromResultSet(ResultSet rs) throws SQLException {
            return new Product(rs);
        }

        // Método para preparar a instrução SQL
        @Override
        public PreparedStatement prepareStatement(PreparedStatement preparedStatement) throws SQLException {
            preparedStatement.setInt(1, getId());
            preparedStatement.setInt(2, getProductType().getId());
            preparedStatement.setString(3, getDescription());
            preparedStatement.setDouble(4, getValue());
            return preparedStatement;
        }

        // Método toString para representação textual
        @Override
        public String toString() {
            return "Product{" +
                    "id=" + id +
                    ", productType=" + productType.getDescription() +
                    ", description='" + description + '\'' +
                    ", value=" + value +
                    '}';
        }
    }