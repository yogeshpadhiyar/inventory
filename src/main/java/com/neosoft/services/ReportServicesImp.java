package com.neosoft.services;

import com.neosoft.model.Invoice;
import com.neosoft.repositry.ReportRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.List;

@Service
@Transactional
public class ReportServicesImp implements ReportServices {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReportRepository.class);
    private final ReportRepository reportRepository;
    @Autowired
    public ReportServicesImp(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    @Override
    public List<Invoice> onDate(Date date) {
        LOGGER.info("Generating report in given data");
        LOGGER.debug("Report date is "+date);
        return reportRepository.onDate(date);
    }

    @Override
    public Invoice productBtwDate(Date startDate, Date endDate, int productId) {
        LOGGER.info("Generating report between data");
        LOGGER.debug("Report date is "+startDate+" to "+ endDate+" on product id "+productId);
        return reportRepository.productBtwDate(startDate,endDate,productId);
    }
}
