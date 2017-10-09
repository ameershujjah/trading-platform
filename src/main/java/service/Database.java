package service;

import data.VO.MonthlyStockQuote;

/**
 * Created by Ameer on 10/6/17.
 */
public interface Database {

//    public boolean checkMonthlyStockQuoteExists(String ticker);
    public void addMonthlyStockQuote(MonthlyStockQuote msq);
    public MonthlyStockQuote getMonthlyStockQuote(String ticker);
}
