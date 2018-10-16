package xyz.crearts.service.bookreader;

import org.junit.Test;
import xyz.crearts.xyz.crearts.model.BookFormat;
import xyz.crearts.xyz.crearts.model.BookInfo;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author ivan.kishchenko
 */
public class EpubBookIndoReaderTest {

    @Test
    public void read() throws Exception {
        EpubBookReader reader = new EpubBookReader();

        BookInfo bookInfo = reader.read(getClass().getClassLoader().getResourceAsStream("test.epub"));

        List<String> authors = bookInfo.getAuthors();
        assertEquals(1, authors.size());
        assertEquals("Франциска Вудворт", authors.get(0));
        assertEquals("Аромат невинности. Дыхание жизни", bookInfo.getTitle());
        assertNotNull(bookInfo.getImage());
        assertEquals(BookFormat.BF_EPUB, bookInfo.getFormat());

    }
}