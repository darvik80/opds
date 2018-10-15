package xyz.crearts.xyz.crearts.model.fb;

import lombok.Data;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.Text;

/**
 * @author ivan.kishchenko
 */
@Data
@Root(name="binary")
public class Fb2Binary {
    @Attribute
    private String id;
    @Attribute(name="content-type")
    private String contentType;

    @Text
    private String data;
}
