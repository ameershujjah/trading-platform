package data.http;

import data.VO.StockQuote;
import data.consumer.HTTP;
import org.junit.Before;
import org.junit.Test;
import service.ConsumerService;
import service.StockQuoteService;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.text.IsEqualIgnoringCase.equalToIgnoringCase;

/**
 * Created by Ameer on 9/10/17.
 */
public class AlphaAdvantageTest {

    private StockQuoteService service;
    private String MICROSOFT = "MSFT";

    @Before
    public void setup() {
        service = new AlphaAdvantage("demo", new HTTP());
    }

    @Test
    public void getSingleStockQuote(){
        StockQuote quote  = service.getStockQuote(MICROSOFT);
        assertThat(quote.getTicker(), equalToIgnoringCase(MICROSOFT));
    }


}
