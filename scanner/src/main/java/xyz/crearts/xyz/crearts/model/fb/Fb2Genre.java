package xyz.crearts.xyz.crearts.model.fb;

import lombok.Data;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.Text;

/**
 * @author ivan.kishchenko
 */
@Data
@Root(name="genre")
public class Fb2Genre {
    @Text(required = false)
    String text;
}
