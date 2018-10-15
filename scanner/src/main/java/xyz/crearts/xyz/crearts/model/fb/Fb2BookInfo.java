package xyz.crearts.xyz.crearts.model.fb;

import lombok.Data;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ivan.kishchenko
 */
@Data
@Root(name="FictionBook")
public class Fb2BookInfo {
    @Element(required = false)
    private Fb2Description description;

    @ElementList(inline = true, required = false)
    private List<Fb2Binary> binary = new ArrayList<>();
}
