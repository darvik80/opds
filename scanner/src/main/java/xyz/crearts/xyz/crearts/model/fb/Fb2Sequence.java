package xyz.crearts.xyz.crearts.model.fb;

import lombok.Data;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

/**
 * @author ivan.kishchenko
 */
@Data
@Root(name="sequence")
public class Fb2Sequence {
    @Attribute
    private String name;

    private @Attribute
    Integer number;
}
