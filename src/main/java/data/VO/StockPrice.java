package data.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Created by Ameer on 10/5/17.
 */
@Data
public class StockPrice {
    private String open;
    private String high;
    private String low;
    private String close;
    private String volume;
}
