package data.parser;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import data.VO.MonthlyStockQuote;
import data.VO.StockPrice;
import lombok.extern.log4j.Log4j;
import service.StockParser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static data.utils.Constants.*;

/**
 * Created by Ameer on 10/6/17.
 */
@Log4j
public class AlphaAdvantageParser implements StockParser{

    public MonthlyStockQuote parseMonthlyStockQuote(String json, String ticker) {
        log.debug("Attempting to parse monthly stock quote");
        JsonElement root = new JsonParser().parse(json);
        JsonObject object = root.getAsJsonObject().get(KEY_MONTHLY_TIME_SERIES).getAsJsonObject();
        MonthlyStockQuote monthlyStockQuote = new MonthlyStockQuote();
        monthlyStockQuote.setTicker(ticker);
        monthlyStockQuote.setQuotes(parseStockPrice(object));
        return monthlyStockQuote;
    }

    public Map<Date, StockPrice> parseStockPrice(JsonObject object){
        log.debug("Parsing stock price");
        Map<Date, StockPrice> map = new HashMap<>();
        for (Map.Entry<String,JsonElement> entry : object.entrySet()){
            JsonObject obj = entry.getValue().getAsJsonObject();
            StockPrice stockPrice = new StockPrice();
            stockPrice.setOpen(removeQuotes(obj.get(OPEN).toString()));
            stockPrice.setHigh(removeQuotes(obj.get(HIGH).toString()));
            stockPrice.setLow(removeQuotes(obj.get(LOW).toString()));
            stockPrice.setClose(removeQuotes(obj.get(CLOSE).toString()));
            stockPrice.setVolume(removeQuotes(obj.get(VOLUME).toString()));
            map.put(parseDate(entry.getKey()),stockPrice);
        }
        return map;
    }

    public Date parseDate(String dateString){
        SimpleDateFormat parser=new SimpleDateFormat(YYYY_MM_DD);
        Date date = new Date();
        try {
            date = parser.parse(dateString);
        } catch (ParseException e){
            log.error("Error parsing date " + dateString + " from JSON : " + e);
        }
        return date;
    }

    public String removeQuotes(String str){
        return str.substring(1,str.length()-1);
    }

}
