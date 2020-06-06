package com.neosoft.repo;

import com.neosoft.config.DatabaseConfiguration;
import com.neosoft.exception.MethodNotSupport;
import com.neosoft.modelhb.Order;
import com.neosoft.util.QueryProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;

@Repository
public class OrderRepoImp implements Repo<Order> {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderRepoImp.class);
    private final DatabaseConfiguration databaseConfiguration;
    private final QueryProperty queryProperty;

    @Autowired
    public OrderRepoImp(DatabaseConfiguration databaseConfiguration , QueryProperty queryProperty) {
        this.databaseConfiguration = databaseConfiguration;
        this.queryProperty = queryProperty;
    }

    @Override
    public Connection createConnection() throws SQLException, ClassNotFoundException {
        return databaseConfiguration.getConnection();
    }

    @Override
    public int insert(Order order) {
        LOGGER.debug("Inserting "+order);
        int generatedOrderId =0;
        try {
            Connection connection = createConnection();
            PreparedStatement ps = connection.prepareStatement(queryProperty.query("order.insert"), Statement.RETURN_GENERATED_KEYS);
            ps.setDate(1, order.getDate());
            ps.executeUpdate();
            ResultSet rs =  ps.getGeneratedKeys();
            rs.first();
            generatedOrderId = rs.getInt(1);
        } catch (SQLException | ClassNotFoundException e) {
            LOGGER.error("Database connection failed Or May be Driver class not found.");
            e.printStackTrace();
        }
        LOGGER.info("Successfully insert order and generate orderId.");
        LOGGER.debug("Order id is "+generatedOrderId);
        return generatedOrderId;
    }

    @Override
    public Order selectById(int id) {
        LOGGER.debug("order id "+id);
        Order order = new Order();
        try {
            Connection connection = createConnection();
            PreparedStatement ps = connection.prepareStatement(queryProperty.query("order.select.by.id"));
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            rs.first();
            order.setId(rs.getInt(1));
            order.setDate(rs.getDate(2));
        }catch (SQLException | ClassNotFoundException e){
            LOGGER.error("Database connection failed Or May be Driver class not found.");
            e.printStackTrace();
        }
        LOGGER.debug("Order details "+order);
        return order;
    }




    @Override
    public int update(Order order) {
        throw new MethodNotSupport("This method not supported now.");
    }

    @Override
    public void delete(Order order) {
        throw new MethodNotSupport("This method not supported now.");

    }

    @Override
    public List<Order> selectAll() {
        throw new MethodNotSupport("This method not supported now.");
    }
}
