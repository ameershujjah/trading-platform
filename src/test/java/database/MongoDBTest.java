package database;

import data.VO.MonthlyStockQuote;
import org.jongo.FindOne;
import org.jongo.Jongo;
import org.jongo.MongoCollection;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;
import service.Database;

import static data.utils.Constants.MICROSFT_TICKER;
import static data.utils.Constants.MONTHLY_COLLECTION;
import static org.mockito.Mockito.*;

/**
 * Created by Ameer on 10/8/17.
 */
public class MongoDBTest {
    private static MongoDB database;

    @BeforeClass
    public static void setup() {database = new MongoDB();};

    // TODO : revisit these

//    @Test
//    public void checkMonthlyStockQuoteExistsTest(){
//        Jongo mockJongo = Mockito.mock(Jongo.class);
//        database.setJongo(mockJongo);
//
//        MongoCollection mockCollection = Mockito.mock(MongoCollection.class);
//        when(mockJongo.getCollection(MONTHLY_COLLECTION)).thenReturn(mockCollection);
//
//        String ticker = MICROSFT_TICKER;
//        MonthlyStockQuote msq = new MonthlyStockQuote();
//
//        FindOne mockFindOne = Mockito.mock(FindOne.class);
//        when(mockCollection.findOne("{ticker : #}",ticker)).thenReturn(mockFindOne);
//        when(mockFindOne.as(MonthlyStockQuote.class)).thenReturn(msq);
//
//        database.checkMonthlyStockQuoteExists(MICROSFT_TICKER);
//
//        verify(mockJongo, times(1)).getCollection(MONTHLY_COLLECTION);
//        verify(mockCollection, times(1)).findOne("{ticker : #}",ticker);
//        verify(mockFindOne, times(1)).as(MonthlyStockQuote.class);
//
//    }


    @Test
    public void addMonthlyStockQuoteTest() {
        Jongo mockJongo = Mockito.mock(Jongo.class);
        database.setJongo(mockJongo);

        MongoCollection mockCollection = Mockito.mock(MongoCollection.class);
        when(mockJongo.getCollection(MONTHLY_COLLECTION)).thenReturn(mockCollection);

        MonthlyStockQuote msq = new MonthlyStockQuote();

        database.addMonthlyStockQuote(msq);

        verify(mockJongo, times(1)).getCollection(MONTHLY_COLLECTION);
        verify(mockCollection, times(1)).insert(msq);
    }

    @Test
    public void getMonthlyStockQuoteTest() {
        Jongo mockJongo = Mockito.mock(Jongo.class);
        database.setJongo(mockJongo);

        MongoCollection mockCollection = Mockito.mock(MongoCollection.class);
        when(mockJongo.getCollection(MONTHLY_COLLECTION)).thenReturn(mockCollection);

        String ticker = MICROSFT_TICKER;
        MonthlyStockQuote msq = new MonthlyStockQuote();
        FindOne mockFindOne = Mockito.mock(FindOne.class);

        when(mockCollection.findOne("{ticker : #}",ticker)).thenReturn(mockFindOne);
        when(mockFindOne.as(MonthlyStockQuote.class)).thenReturn(msq);

        database.getMonthlyStockQuote(ticker);

        verify(mockJongo, times(1)).getCollection(MONTHLY_COLLECTION);
        verify(mockCollection, times(1)).findOne("{ticker : #}",ticker);
        verify(mockFindOne, times(1)).as(MonthlyStockQuote.class);

    }
}
