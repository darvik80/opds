package xyz.crearts.server.models.atom;

import lombok.Builder;
import lombok.Data;
import org.simpleframework.xml.Element;

/**
 * @author ivan.kishchenko
 */
@Data
@Builder
public class Author {
    @Element
    private String name;
    @Element
    private String uri;
}
