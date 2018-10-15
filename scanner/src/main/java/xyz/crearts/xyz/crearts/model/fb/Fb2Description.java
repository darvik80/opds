package xyz.crearts.xyz.crearts.model.fb;

import lombok.Data;
import org.simpleframework.xml.Element;

/**
 * @author ivan.kishchenko
 */
@Data
public class Fb2Description {
    @Element(name="title-info", required = false)
    private Fb2TitleInfo titleInfo;
    @Element(name="document-info", required = false)
    private Fb2DocumentInfo documentInfo;
    @Element(name="publish-info", required = false)
    private Fb2PublishInfo publishInfo;
}
