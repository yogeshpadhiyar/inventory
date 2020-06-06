package com.neosoft.services;

import com.neosoft.model.Invoice;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public interface ReportServices {
    List<Invoice> onDate(Date date);
    Invoice productBtwDate(Date startDate, Date endDate, int productId);
}
