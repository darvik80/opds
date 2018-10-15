package xyz.crearts.xyz.crearts.model.fb;

import lombok.Data;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ivan.kishchenko
 */
@Data
@Root(name="coverpage")
public class Fb2CoverPage {
    @ElementList(inline = true, required = false)
    private List<Fb2Image> image = new ArrayList<>();
}
