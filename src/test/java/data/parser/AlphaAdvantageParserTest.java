package data.parser;

import data.VO.MonthlyStockQuote;
import data.VO.StockPrice;
import org.json.JSONObject;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.*;

import static data.utils.Constants.KEY_MONTHLY_TIME_SERIES;
import static data.utils.Constants.MICROSFT_TICKER;
import static org.junit.Assert.assertEquals;

/**
 * Created by Ameer on 10/8/17.
 */
public class AlphaAdvantageParserTest {

    private static AlphaAdvantageParser parser;

    @BeforeClass
    public static void setup() {
        parser = new AlphaAdvantageParser();
    }


    @Test
    public void removeQuotesTest(){
        sampleJSON();
        String sampleString = "\"hello\"";
        assertEquals("hello", parser.removeQuotes(sampleString));
    }

    @Test
    public void parseDateTest(){
        String sampleDate = "2016-10-10";
        Date date2 = new GregorianCalendar(2016, Calendar.OCTOBER, 10).getTime();
        assertEquals(date2, parser.parseDate(sampleDate));
    }

    @Test
    public void parseMonthlyStockQuoteTest(){
        MonthlyStockQuote msq = sampleMSQ();
        assertEquals(msq, parser.parseMonthlyStockQuote(sampleJSON(),MICROSFT_TICKER));
    }


    public MonthlyStockQuote sampleMSQ() {
        MonthlyStockQuote msq = new MonthlyStockQuote();
        msq.setTicker(MICROSFT_TICKER);
        StockPrice sp = new StockPrice();
        sp.setVolume("100");
        sp.setLow("10");
        sp.setClose("10");
        sp.setOpen("10");
        sp.setHigh("11");
        Map<Date, StockPrice> map = new HashMap<>();
        map.put(new GregorianCalendar(2010, Calendar.OCTOBER, 10).getTime(),sp);
        msq.setQuotes(map);
        return msq;
    }

    public static String sampleJSON() {
        String jsonString = new JSONObject()
                .put(KEY_MONTHLY_TIME_SERIES,
                        new JSONObject().put("2010-10-10",
                                new JSONObject().put("1. open","10")
                                                .put("2. high", "11")
                                                .put("3. low", "10")
                                                .put("4. close", "10")
                                                .put("5. volume", "100"))).toString();
        return jsonString;
    }

}
