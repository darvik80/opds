package xyz.crearts.server.models.atom;

import lombok.Builder;
import lombok.Data;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Text;

/**
 * @author ivan.kishchenko
 */
@Data
@Builder
public class Price {
    @Text
    private Double data;

    @Attribute
    private String currencyCode;
}
