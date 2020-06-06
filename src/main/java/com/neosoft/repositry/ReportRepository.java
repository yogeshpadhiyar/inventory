package com.neosoft.repositry;

import com.neosoft.model.Invoice;
import com.neosoft.util.QueryProperty;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ReportRepository {
    private static final Logger LOGGER=Logger.getLogger(ReportRepository.class);
    private final SessionFactory sessionFactory;
    private final QueryProperty queryProperty;

    @Autowired
    public ReportRepository(SessionFactory sessionFactory, QueryProperty queryProperty) {
        this.sessionFactory = sessionFactory;
        this.queryProperty = queryProperty;
    }


    public List<Invoice> onDate(Date date){
        LOGGER.info("Generating Report on specific date.");
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(queryProperty.query("report.on.data"));
        query.setParameter("date" , date);
        List<Object[]> objectList = query.getResultList();
        List<Invoice> invoiceList = new ArrayList<>();
        Invoice invoice;
        for (Object[] objects : objectList) {
            invoice= new Invoice();
            invoice.setProductName(objects[0].toString());
            invoice.setQuantity(Integer.parseInt(objects[1].toString()));
            invoice.setPrice(Integer.parseInt(objects[2].toString()));
            invoiceList.add(invoice);
        }
        invoiceList.forEach(e->LOGGER.debug(e));
        return invoiceList;
    }

    public Invoice productBtwDate(Date startDate, Date endDate, int productId){
        LOGGER.info("Generating Report on specific product.");
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(queryProperty.query("report.product.btw.date"));
        query.setParameter("startdate" , startDate);
        query.setParameter("enddate" , endDate);
        query.setParameter("id" , productId);
        List<Object[]> objectList = query.getResultList();
        Invoice invoice = new Invoice();
        for (Object[] objects : objectList) {
            invoice.setProductName(objects[0].toString());
            invoice.setQuantity(Integer.parseInt(objects[1].toString()));
            invoice.setPrice(Integer.parseInt(objects[2].toString()));
        }
        LOGGER.debug(invoice);
        return invoice;
    }
}
