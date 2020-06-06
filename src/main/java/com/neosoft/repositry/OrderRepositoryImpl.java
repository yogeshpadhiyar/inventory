package com.neosoft.repositry;

import com.neosoft.exception.MethodNotSupport;
import com.neosoft.modelhb.Order;
import com.neosoft.util.Formatter;
import com.neosoft.util.QueryProperty;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderRepositoryImpl implements Repo<Order> {
    private static final Logger LOGGER = Logger.getLogger(OrderRepositoryImpl.class);
    private final SessionFactory sessionFactory;
    private final QueryProperty queryProperty;
    @Autowired
    public OrderRepositoryImpl(SessionFactory sessionFactory, QueryProperty queryProperty) {
        this.sessionFactory = sessionFactory;
        this.queryProperty = queryProperty;
    }

    @Override
    public Session databaseConnector() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public int insert(Order order) {
        LOGGER.info("Inside orderRepo insert");
        Session session = databaseConnector();
        return Formatter.convertToInt(session.save(order));
    }

    @Override
    public Order selectById(int id) {
        LOGGER.info("Finding invoice...");
        Session session= databaseConnector();
        Query query = session.createQuery(queryProperty.query("order.select.by.id"));
        query.setParameter("id", id);
        return (Order) query.list().get(0);
    }



    @Override
    public void update(Order order) {
        throw new MethodNotSupport("This method is not valid");
    }

    @Override
    public void delete(Order order) {
        throw new MethodNotSupport("This method is not valid");
    }

    @Override
    public List<Order> selectAll() {
        throw new MethodNotSupport("This method is not valid");
    }
}
