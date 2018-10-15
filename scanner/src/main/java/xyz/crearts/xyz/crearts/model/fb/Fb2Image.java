package xyz.crearts.xyz.crearts.model.fb;

import lombok.Data;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

/**
 * @author ivan.kishchenko
 */
@Data
@Root(name="image")
public class Fb2Image {
    @Attribute(name="href", required = false)
    private String href;
}
