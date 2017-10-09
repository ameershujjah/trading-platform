package data.consumer;

import lombok.extern.log4j.Log4j;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONObject;
import service.ConsumerService;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import static data.utils.Constants.REQUEST_HEADER_USER_AGENT;
import static org.apache.http.HttpHeaders.USER_AGENT;

/**
 * Created by Ameer on 9/11/17.
 */
@Log4j
public class HTTP implements ConsumerService {

    public String getRequest(String url){
        log.debug("Making HTTP get request");
        HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(url);
        request.addHeader(REQUEST_HEADER_USER_AGENT, USER_AGENT);
        String resultString ="";
        try {
            HttpResponse response = client.execute(request);

            log.debug("Response Code : " + response.getStatusLine().getStatusCode());

            BufferedReader rd = new BufferedReader(
                    new InputStreamReader(response.getEntity().getContent()));

            StringBuffer result = new StringBuffer();
            String line = "";
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
            resultString = result.toString();
            JSONObject obj = new JSONObject(resultString);
        } catch(java.io.IOException e) {
            log.error("Something went wrong" + e);
        }
        return resultString;
    }

}
