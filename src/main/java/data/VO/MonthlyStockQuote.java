package data.VO;

import lombok.Data;

import java.util.Date;
import java.util.Map;

/**
 * Created by Ameer on 10/5/17.
 */
@Data
public class MonthlyStockQuote {
    private String ticker;
    private Map<Date, StockPrice> quotes;
}
