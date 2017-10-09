package data.utils;

import data.VO.QueryString;
import data.VO.RequestParameter;
import lombok.extern.log4j.Log4j;

import java.util.List;

/**
 * Created by Ameer on 9/10/17.
 */
@Log4j
public class RequestUtils {

    public static String queryBuilder(List<RequestParameter> requestParameters, String baseURL) {
        StringBuilder output = new StringBuilder();
        output.append(baseURL);
        for (RequestParameter parm: requestParameters) {
            log.debug("Adding paramter:" + parm);
            output.append(parm.getName()+"="+parm.getValue());
            output.append("&");
        }
        output.deleteCharAt(output.length()-1);
        return output.toString();
    }


}
