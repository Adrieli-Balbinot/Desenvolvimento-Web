package com.mycompany.app.dao;

import com.mycompany.app.model.ProductType;
import com.mycompany.app.model.Sale;
import com.mycompany.app.model.SaleItem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class SaleDAO extends GenericDAOImpl<Sale> implements GenericDAO<Sale> {

    private Connection connection;
    private final String TABLE_NAME = "sale";

    private final List<String> COLUMNS = List.of("saleItems", "insertAt");

    public SaleDAO(Connection connection) {
        super(Sale::new, connection);
        super.tableName = TABLE_NAME;
        super.columns = COLUMNS;
    }

    // Método para obter SaleItems associados a uma Sale
    public List<SaleItem> getSaleItemsBySaleId(int saleId) throws SQLException {
        List<SaleItem> saleItems = new ArrayList<>();
        String sql = "SELECT * FROM sale_item WHERE sale_id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, saleId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    SaleItem saleItem = new SaleItem(rs);
                    saleItems.add(saleItem);
                }
            }
        }

        return saleItems;
    }


}
