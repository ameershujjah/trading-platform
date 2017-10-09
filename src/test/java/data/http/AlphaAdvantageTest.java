package data.http;

import data.VO.MonthlyStockQuote;
import data.VO.StockQuote;
import data.consumer.HTTP;
import data.parser.AlphaAdvantageParser;
import database.MongoDB;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;
import service.ConsumerService;
import service.Database;
import service.StockParser;
import service.StockQuoteService;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.text.IsEqualIgnoringCase.equalToIgnoringCase;
import static org.mockito.Mockito.*;

/**
 * Created by Ameer on 9/10/17.
 */
public class AlphaAdvantageTest {

    private Database database;
    private ConsumerService consumer;
    private StockParser parser;
    private StockQuoteService service;

    @Before
    public void setup() {
        database = Mockito.mock(MongoDB.class);
        consumer = Mockito.mock(HTTP.class);
        parser = Mockito.mock(AlphaAdvantageParser.class);
        service = new AlphaAdvantage("demo", consumer, parser, database);
    }

    @Test
    public void getMonthlyStockQuoteFromDBTest() {
        String ticker = "MSFT";
        MonthlyStockQuote msq = new MonthlyStockQuote();

        when(database.getMonthlyStockQuote(ticker)).thenReturn(msq);
        service.getMonthlyStockQuote(ticker);

        String query = "https://www.alphavantage.co/query?function=TIME_SERIES_MONTHLY&symbol=MSFT&apikey=demo";

        verify(database, times(1)).getMonthlyStockQuote(ticker);
        verify(database, never()).addMonthlyStockQuote(msq);
        verify(consumer, never()).getRequest(query);
    }

    @Test
    public void getMonthlyStockQuoteFromAPITest() {
        String ticker = "MSFT";
        when(database.getMonthlyStockQuote(ticker)).thenReturn(null);

        String result = "";
        MonthlyStockQuote msq = new MonthlyStockQuote();
        String query = "https://www.alphavantage.co/query?function=TIME_SERIES_MONTHLY&symbol=MSFT&apikey=demo";

        when(consumer.getRequest(query)).thenReturn(result);
        when(parser.parseMonthlyStockQuote(result,ticker)).thenReturn(msq);

        service.getMonthlyStockQuote(ticker);

        verify(database, times(1)).getMonthlyStockQuote(ticker);
        verify(consumer, times(1)).getRequest(query);
        verify(parser, times(1)).parseMonthlyStockQuote(result,ticker);
        verify(database, times(1)).addMonthlyStockQuote(msq);

    }

}
