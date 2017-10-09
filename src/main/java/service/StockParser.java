package service;

import data.VO.MonthlyStockQuote;

/**
 * Created by Ameer on 10/6/17.
 */
public interface StockParser {
    public MonthlyStockQuote parseMonthlyStockQuote(String json, String ticker);
}
