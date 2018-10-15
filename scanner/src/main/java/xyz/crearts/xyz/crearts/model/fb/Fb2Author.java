package xyz.crearts.xyz.crearts.model.fb;

import lombok.Data;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * @author ivan.kishchenko
 */
@Data
@Root(name="author")
public class Fb2Author {
    @Element(name="first-name", required = false)
    private String firstName;
    @Element(name="middle-name", required = false)
    private String middleName;
    @Element(name="last-name", required = false)
    private String lastName;
    @Element(required = false)
    private String nickname;
    @Element(required = false)
    private String email;

}
