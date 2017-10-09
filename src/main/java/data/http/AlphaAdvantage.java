package data.http;

import data.VO.MonthlyStockQuote;
import data.VO.RequestParameter;
import data.VO.StockQuote;
import data.consumer.HTTP;
import data.parser.AlphaAdvantageParser;
import data.utils.RequestUtils;
import database.MongoDB;
import lombok.extern.log4j.Log4j;
import org.json.JSONObject;
import service.ConsumerService;
import service.Database;
import service.StockParser;
import service.StockQuoteService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static data.utils.Constants.*;

/**
 * Created by Ameer on 9/10/17.
 */
@Log4j
public class AlphaAdvantage implements StockQuoteService{

    private final String baseURL = "https://www.alphavantage.co/query?";
    private final RequestParameter APIKey;
    private final ConsumerService consumer;
    private final StockParser parser;
    private final Database database;

    public AlphaAdvantage(String apiKey, ConsumerService consumer, StockParser parser, Database db){
        this.APIKey = new RequestParameter(API_KEY_PARMNAME,apiKey);
        this.consumer = consumer;
        this.parser = parser;
        this.database = db;
    }

    public StockQuote getStockQuote(String ticker) {
        log.debug("Getting stock quote for ticker " + ticker);
        RequestParameter function = new RequestParameter(FUNCTION_PARMNAME,TIMESERIES_INTRADAY);
        RequestParameter symbol = new RequestParameter(SYMBOL_PARMNAME, ticker);
        RequestParameter interval = new RequestParameter(INTERVAL_PARMNAME, FIFTEEN_MIN_INTERVAL);
        RequestParameter outputSize = new RequestParameter(OUTPUT_SIZE_PARMNAME, OUTPUT_COMPRESSED);
        List<RequestParameter> parms = new ArrayList<RequestParameter>();
        parms.addAll(Arrays.asList(function,symbol,interval,outputSize,APIKey));
        String query = RequestUtils.queryBuilder(parms, baseURL);
        String result = consumer.getRequest(query);
        StockQuote quote = new StockQuote();
        quote.setTicker(MICROSFT_TICKER);
        return quote;
    }

    public MonthlyStockQuote getMonthlyStockQuote(String ticker){
        log.debug("Getting stock quote for ticker " + ticker);
        MonthlyStockQuote msq = database.getMonthlyStockQuote(ticker);
        if (msq != null)
            return msq;
        return getMonthlyStockQuoteHelper(ticker);
    }

    public MonthlyStockQuote getMonthlyStockQuoteHelper(String ticker){
        RequestParameter function = new RequestParameter(FUNCTION_PARMNAME,TIMESERIES_MONTHLY);
        RequestParameter symbol = new RequestParameter(SYMBOL_PARMNAME, ticker);
        List<RequestParameter> parms = new ArrayList<RequestParameter>();
        parms.addAll(Arrays.asList(function,symbol,APIKey));
        String query = RequestUtils.queryBuilder(parms, baseURL);
        log.debug(query);
        String result = consumer.getRequest(query);
        MonthlyStockQuote msq = parser.parseMonthlyStockQuote(result, ticker);
        database.addMonthlyStockQuote(msq);
        return msq;
    }

    public static void main(String[] args) {
        AlphaAdvantage a = new AlphaAdvantage("demo", new HTTP(), new AlphaAdvantageParser(), new MongoDB());
        MonthlyStockQuote msq ;//= a.getMonthlyStockQuote(MICROSFT_TICKER);
        MongoDB m = new MongoDB();
        msq = a.getMonthlyStockQuote(MICROSFT_TICKER);

//        m.addMonthlyStockQuote(msq);
//        System.out.println(m.checkMonthlyStockQuoteExists(MICROSFT_TICKER));
        System.out.println(msq.toString());
    }

}
