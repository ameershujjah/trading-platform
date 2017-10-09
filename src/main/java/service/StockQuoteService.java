package service;

import data.VO.MonthlyStockQuote;
import data.VO.StockQuote;

/**
 * Created by Ameer on 9/10/17.
 */
public interface StockQuoteService {

    public StockQuote getStockQuote(String ticker);

    public MonthlyStockQuote getMonthlyStockQuote(String ticker);

}
