package xyz.crearts.server.models.atom;

import lombok.Builder;
import lombok.Data;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;

import java.util.Date;
import java.util.List;

/**
 * @author ivan.kishchenko
 */
@Data
@Builder
public class Entry {
    @Element(required = false)
    private String id;
    @Element(required = false)
    private Date updated;
    @Element
    private String title;

    @ElementList(name="content", inline = true)
    List<Content> contents;

    @Element(required = false)
    private String rights;


    @Element(name="dc:identifier", required = false)
    private String identifier;
    @Element(name="dc:publisher", required = false)
    private String publisher;
    @Element(name="dc:language", required = false)
    private String language;
    @Element(name="dc:issued", required = false)
    private Date issued;

    @Element(required = false)
    private String category;
    @Element(required = false)
    private Summary summary;
}
