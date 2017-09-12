package data.utils;

import data.VO.RequestParameter;

import java.util.List;

/**
 * Created by Ameer on 9/10/17.
 */
public class RequestUtils {

    public static String queryBuilder(List<RequestParameter> requestParameters, String baseURL) {
        StringBuilder output = new StringBuilder();
        output.append(baseURL);
        for (RequestParameter parm: requestParameters) {
            output.append(parm.getName()+"="+parm.getValue());
            output.append("&");
        }
        return output.toString();
    }


}
