package data.VO;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by Ameer on 9/10/17.
 */
@Data
public class StockQuote {
    String ticker;
    String name;
    BigDecimal price;
}
