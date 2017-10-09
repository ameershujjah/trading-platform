package data.VO;

import lombok.Builder;

/**
 * Created by Ameer on 9/14/17.
 */
@Builder
public class QueryString {
    private String function;
    private String symbol;
    private String interval;
    private String outputsize;
    private String apikey;
}
