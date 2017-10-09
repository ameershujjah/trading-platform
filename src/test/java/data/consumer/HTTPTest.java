package data.consumer;

import org.junit.BeforeClass;
import org.junit.Test;
import service.ConsumerService;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.text.IsEmptyString.isEmptyString;
/**
 * Created by Ameer on 9/11/17.
 */
public class HTTPTest {

    private static ConsumerService service;

    @BeforeClass
    public static void setup(){
        service = new HTTP();
    }

    // TODO: shouldnt make a live call, mock the call
    @Test
    public void getRequestResultNotEmpty() throws IOException{
        String url = "https://www.alphavantage.co/query?function=TIME_SERIES_MONTHLY&symbol=MSFT&apikey=demo";
        String result = service.getRequest(url);
        assertThat(result,  not(isEmptyString()));
    }
}
