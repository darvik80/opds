package xyz.crearts.server.models.atom;

import lombok.Builder;
import lombok.Data;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;

import java.util.List;

/**
 * @author ivan.kishchenko
 */
@Data
@Builder
public class Link {
    @Attribute
    private String href;

    @Attribute(required = false)
    private String rel;

    @Attribute
    private String type;

    @Attribute(required = false)
    private String title;

    @ElementList(name="opds:price", inline = true, required = false)
    private List<Price> price;

    @Attribute(name="opds:facetGroup", required = false)
    private String facetGroup;
    @Attribute(name="opds:activeFacet", required = false)
    private Boolean activeFacet;

    @Attribute(name="thr:count", required = false)
    private Integer thrCount;
}
