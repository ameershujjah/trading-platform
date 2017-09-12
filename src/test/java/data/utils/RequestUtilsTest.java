package data.utils;

import data.VO.RequestParameter;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Ameer on 9/10/17.
 */
public class RequestUtilsTest {

    @Test
    public void buildQuery() {
        RequestParameter parm1 = new RequestParameter("name","John");
        RequestParameter parm2 = new RequestParameter("age","25");
        List<RequestParameter> parms = new ArrayList<RequestParameter>();
        parms.add(parm1);
        parms.add(parm2);
        String baseURL = "http://www.google.com/query?";
        String result = RequestUtils.queryBuilder(parms, baseURL);
        assertThat(result, containsString("name=John"));
        assertThat(result, containsString("age=25"));
    }
}
