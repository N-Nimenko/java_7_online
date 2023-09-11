package module.service;

import module.dao.CsvDao;

import java.util.Date;

public class CsvService {
    CsvDao csvDao = new CsvDao();
    public boolean exportAccountStatementToCsv(String accountNumber, Date startDate, Date endDate) {
        return csvDao.exportAccountStatementToCsv(accountNumber, startDate, endDate);
    }
}
