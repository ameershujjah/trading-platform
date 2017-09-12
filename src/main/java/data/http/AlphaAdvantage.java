package data.http;

import data.VO.RequestParameter;
import data.VO.StockQuote;
import data.utils.RequestUtils;
import lombok.extern.log4j.Log4j;
import org.json.JSONObject;
import service.ConsumerService;
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

    public AlphaAdvantage(String apiKey, ConsumerService consumer){
        this.APIKey = new RequestParameter(API_KEY_PARMNAME,apiKey);
        this.consumer = consumer;
    }

    public StockQuote getStockQuote(String ticker) {
        log.debug("Getting stock quote for ticker " + ticker);
        RequestParameter function = new RequestParameter(FUNCTION_PARMNAME,TIMESERIES_INTRADAY);
        RequestParameter symbol = new RequestParameter(SYMBOL_PARMNAME, MICOROSFT_TICKER);
        RequestParameter interval = new RequestParameter(INTERVAL_PARMNAME, FIFTEEN_MIN_INTERVAL);
        RequestParameter outputSize = new RequestParameter(OUTPUT_SIZE_PARMNAME, OUTPUT_COMPRESSED);
        List<RequestParameter> parms = new ArrayList<RequestParameter>();
        parms.addAll(Arrays.asList(function,symbol,interval,outputSize,APIKey));
        String query = RequestUtils.queryBuilder(parms, baseURL);
        StockQuote quote = new StockQuote();
        quote.setTicker(MICOROSFT_TICKER);
        return quote;
    }

}
