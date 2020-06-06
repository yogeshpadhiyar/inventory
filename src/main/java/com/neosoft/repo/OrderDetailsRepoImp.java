package com.neosoft.repo;

import com.neosoft.config.DatabaseConfiguration;
import com.neosoft.exception.MethodNotSupport;
import com.neosoft.model.Invoice;
import com.neosoft.modelhb.OrderDetails;
import com.neosoft.util.QueryProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class OrderDetailsRepoImp implements Repo<OrderDetails> {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderDetailsRepoImp.class);
    private final DatabaseConfiguration databaseConfiguration;
    private final QueryProperty queryProperty;

    @Autowired
    public OrderDetailsRepoImp(DatabaseConfiguration databaseConfiguration, QueryProperty queryProperty) {
        this.databaseConfiguration = databaseConfiguration;
        this.queryProperty = queryProperty;
    }

    @Override
    public int insert(OrderDetails orderDetails) {
        LOGGER.debug("Inserting "+orderDetails);
        int affectedRow = 0;
        try {
            Connection connection =createConnection();
            PreparedStatement ps = connection.prepareStatement(queryProperty.query("order_details.insert"));
            //ps.setInt(1,orderDetails.getOrderId());
            //ps.setInt(2,orderDetails.getProductId());
            ps.setInt(3,orderDetails.getQuantity());
            ps.setInt(4,orderDetails.getPrice());
            affectedRow = ps.executeUpdate();
        }catch (SQLException | ClassNotFoundException e){
            LOGGER.error("Database connection failed Or May be Driver class not found.");
            e.printStackTrace();
        }
        LOGGER.info("Insert successfully.");
        return affectedRow;
    }

    public List<Invoice> getInvoiceByOrderId(int orderId){
        LOGGER.info("Generate order details using order id.");
        List<Invoice> invoices = new ArrayList<>();
        try{
            Connection connection = createConnection();
            PreparedStatement ps = connection.prepareStatement(queryProperty.query("order.invoice"));
            ps.setInt(1, orderId);
            ResultSet rs = ps.executeQuery();
            Invoice invoice;
            while (rs.next()){
                invoice = new Invoice();
                invoice.setProductName(rs.getString(1));
                invoice.setQuantity(rs.getInt(2));
                invoice.setPrice(rs.getInt(3));
                invoices.add(invoice);
            }
        }catch (SQLException | ClassNotFoundException e){
            LOGGER.error("Database connection failed Or May be Driver class not found.");
            e.printStackTrace();
        }
        invoices.forEach(e-> LOGGER.debug(String.valueOf(e)));
        return invoices;
    }

    @Override
    public Connection createConnection() throws SQLException, ClassNotFoundException {
        return databaseConfiguration.getConnection();
    }




    @Override
    public int update(OrderDetails orderDetails) {
        throw new MethodNotSupport("This method not supported now.");
    }

    @Override
    public void delete(OrderDetails orderDetails) {
        throw new MethodNotSupport("This method not supported now.");
    }

    @Override
    public List<OrderDetails> selectAll() {
        throw new MethodNotSupport("This method not supported now.");
    }

    @Override
    public OrderDetails selectById(int id) {
        throw new MethodNotSupport("This method not supported now.");
    }
}
