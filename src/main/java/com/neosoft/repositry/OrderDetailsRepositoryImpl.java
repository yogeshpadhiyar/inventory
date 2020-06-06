package com.neosoft.repositry;

import com.neosoft.exception.MethodNotSupport;
import com.neosoft.model.Invoice;
import com.neosoft.modelhb.OrderDetails;
import com.neosoft.util.QueryProperty;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class OrderDetailsRepositoryImpl implements Repo<OrderDetails> {
    private static final Logger LOGGER = Logger.getLogger(OrderRepositoryImpl.class);
    private final SessionFactory sessionFactory;
    private final QueryProperty queryProperty;

    @Autowired
    public OrderDetailsRepositoryImpl(SessionFactory sessionFactory, QueryProperty queryProperty) {
        this.sessionFactory = sessionFactory;
        this.queryProperty = queryProperty;
    }

    @Override
    public Session databaseConnector() {
        return sessionFactory.getCurrentSession();
    }

    public List<Invoice> getInvoiceByOrderId(int orderId){
        LOGGER.info("In side invoice generate on order id.");
        Session session = databaseConnector();
        Query query = session.createQuery(queryProperty.query("order.invoice"));
        query.setParameter("id",orderId);
        List<Object[]> objectsList = query.getResultList();
        List<Invoice> invoiceList = new ArrayList<>();
        Invoice invoice;
        for (Object[] od : objectsList) {
            invoice = new Invoice();
            invoice.setProductName(od[0].toString());
            invoice.setQuantity(Integer.parseInt(od[1].toString()));
            invoice.setPrice(Integer.parseInt(od[2].toString()));
            invoiceList.add(invoice);
        }
        invoiceList.forEach(e->LOGGER.debug(e));
        return invoiceList;
    }



    @Override
    public OrderDetails selectById(int id) {
        throw new MethodNotSupport("This method not supported now.");
    }

    @Override
    public int insert(OrderDetails orderDetails) {
        throw new MethodNotSupport("This method not supported now.");
    }

    @Override
    public void update(OrderDetails orderDetails) {
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

}
