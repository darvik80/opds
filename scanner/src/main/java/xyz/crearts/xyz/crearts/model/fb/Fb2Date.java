package xyz.crearts.xyz.crearts.model.fb;

import lombok.Data;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.Text;

/**
 * @author ivan.kishchenko
 */
@Data
@Root(name="date")
public class Fb2Date {
    @Attribute(required = false)
    private String value;

    @Text(required = false)
    private String text;
}
