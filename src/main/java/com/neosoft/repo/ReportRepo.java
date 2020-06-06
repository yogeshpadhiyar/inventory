package com.neosoft.repo;

import com.neosoft.config.DatabaseConfiguration;
import com.neosoft.model.Invoice;
import com.neosoft.util.QueryProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ReportRepo {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReportRepo.class);
    private final DatabaseConfiguration databaseConfiguration;
    private final QueryProperty queryProperty;

    @Autowired
    public ReportRepo(DatabaseConfiguration databaseConfiguration, QueryProperty queryProperty) {
        this.databaseConfiguration = databaseConfiguration;
        this.queryProperty = queryProperty;
    }

    public List<Invoice> onDate(Date date){
        List<Invoice> invoices = new ArrayList<>();
        try {
            Connection connection = databaseConfiguration.getConnection();
            PreparedStatement ps = connection.prepareStatement(queryProperty.query("report.on.data"));
            ps.setDate(1,date);
            ResultSet rs = ps.executeQuery();
            Invoice invoice;
            while (rs.next()){
                invoice = new Invoice();
                invoice.setProductName(rs.getString(1));
                invoice.setQuantity(rs.getInt(2));
                invoice.setPrice(rs.getInt(3));
                invoices.add(invoice);
            }
        } catch (SQLException | ClassNotFoundException e) {
            LOGGER.error("Database connection failed Or May be Driver class not found.");
            e.printStackTrace();
        }
        invoices.forEach(e-> LOGGER.debug("Reports is "+e));
        return invoices;
    }

    public Invoice productBtwDate(Date startDate, Date endDate, int productId){
        Invoice invoice = new Invoice();
            try {
                Connection connection = databaseConfiguration.getConnection();
                PreparedStatement ps = connection.prepareStatement(queryProperty.query("report.product.btw.date"));
                ps.setInt(1,productId);
                ps.setDate(2,startDate);
                ps.setDate(3, endDate);
                ResultSet rs = ps.executeQuery();
                rs.first();
                invoice.setProductName(rs.getString(1));
                invoice.setQuantity(rs.getInt(2));
                invoice.setPrice(rs.getInt(3));
            } catch (SQLException | ClassNotFoundException e) {
                LOGGER.error("Database connection failed Or May be Driver class not found.");
                e.printStackTrace();
            }
            LOGGER.debug("Report is "+invoice);
            return invoice;
    }

}
