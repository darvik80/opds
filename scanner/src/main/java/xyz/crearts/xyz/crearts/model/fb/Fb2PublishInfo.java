package xyz.crearts.xyz.crearts.model.fb;

import lombok.Data;
import org.simpleframework.xml.Element;

/**
 * @author ivan.kishchenko
 */
@Data
public class Fb2PublishInfo {
    @Element(name="book-name", required = false)
    private String bookName;
    @Element(required = false)
    private String publisher;
    @Element(required = false)
    private String year;
    @Element(required = false)
    private String city;
}
