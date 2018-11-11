package xyz.crearts.xyz.crearts.model.fb;

import lombok.Data;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.Text;

/**
 * @author ivan.kishchenko
 */
@Data
@Root(name="annotation")
public class Fb2Annotation {
    @Text(required = false)
    private String text;
}
