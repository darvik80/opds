package xyz.crearts.xyz.crearts.model.fb;

import lombok.Data;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;

import java.util.List;

/**
 * @author ivan.kishchenko
 */
@Data
public class Fb2DocumentInfo {
    @ElementList(inline = true, required = false)
    private List<Fb2Author> author;
    @Element(name="program-used", required = false)
    private String programUsed;
    @Element(required = false)
    private Fb2Date date;
    @Element(required = false)
    private String id;
    @Element(required = false)
    private String version;
    //private Fb2History history;
}
