package xyz.crearts.xyz.crearts.model.fb;

import lombok.Data;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;

import java.util.List;

/**
 * @author ivan.kishchenko
 */
@Data
public class Fb2TitleInfo {
    @ElementList(inline = true, required = false)
    private List<Fb2Genre> genre;

    @ElementList(inline = true, required = false)
    private List<Fb2Author> author;

    @Element(name="book-title", required = false)
    private String bookTitle;

    @ElementList(inline = true, required = false)
    private List<Fb2Annotation> annotation;

    @Element(required = false)
    private Fb2Date date;

    @Element(name="coverpage", required = false)
    private Fb2CoverPage coverPage;


    @Element(required = false)
    private String lang;

    @Element(name="src-lang", required = false)
    private String srcLang;


    @Element(required = false)
    private Fb2Sequence sequence;
}
