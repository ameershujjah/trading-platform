package data.VO;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by Ameer on 9/10/17.
 */
@Data
@AllArgsConstructor
public class RequestParameter {
    private final String name;
    private final String value;
}
