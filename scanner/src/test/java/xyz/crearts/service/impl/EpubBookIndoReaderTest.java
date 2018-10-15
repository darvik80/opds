package xyz.crearts.service.impl;

import nl.siegmann.epublib.domain.Book;
import org.junit.Test;
import xyz.crearts.xyz.crearts.model.BookFormat;
import xyz.crearts.xyz.crearts.model.BookInfo;
import xyz.crearts.xyz.crearts.model.fb.Fb2Author;
import xyz.crearts.xyz.crearts.model.fb.Fb2Binary;
import xyz.crearts.xyz.crearts.model.fb.Fb2BookInfo;
import xyz.crearts.xyz.crearts.model.fb.Fb2CoverPage;

import java.util.List;
import java.util.zip.ZipInputStream;

import static org.junit.Assert.*;

/**
 * @author ivan.kishchenko
 */
public class EpubBookIndoReaderTest {

    @Test
    public void read() throws Exception {
        EpubBookIndoReader reader = new EpubBookIndoReader();

        BookInfo bookInfo = reader.read(getClass().getClassLoader().getResourceAsStream("test.epub"));

        List<String> authors = bookInfo.getAuthors();
        assertEquals(1, authors.size());
        assertEquals("Франциска Вудворт", authors.get(0));
        assertEquals("Аромат невинности. Дыхание жизни", bookInfo.getTitle());
        assertNotNull(bookInfo.getImage());
        assertEquals(BookFormat.BF_EPUB, bookInfo.getFormat());

    }
}