package xyz.crearts.server.models.atom;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.Date;
import java.util.List;

/**
 * @author ivan.kishchenko
 */
@Root
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Feed {
    @Attribute(required = false)
    String xmlns;
    @Attribute(name="xmlns:thr", required = false)
    String xmlnsThr;
    @Attribute(name="xmlns:opds", required = false)
    String xmlnsOpds;

    @Element
    private String id;
    @Element(required = false)
    private String icon;
    @Element
    private String title;
    @Element
    private Date updated;

    @ElementList(inline = true, required = false)
    List<Link> links;
    @ElementList(inline = true, required = false)
    List<Entry> entries;
}
