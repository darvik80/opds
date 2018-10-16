package xyz.crearts.service.bookreader;

import org.junit.Test;
import xyz.crearts.xyz.crearts.model.BookFormat;
import xyz.crearts.xyz.crearts.model.BookInfo;

import static org.junit.Assert.*;

/**
 * @author ivan.kishchenko
 */
public class PdfBookInfoReaderTest {

    @Test
    public void read() throws Exception {
        PdfBookReader reader = new PdfBookReader();
        BookInfo bookInfo = reader.read(getClass().getClassLoader().getResourceAsStream("test.pdf"));

        assertEquals(bookInfo.getTitle(), "MultiPage PDF File");
        assertEquals(1, bookInfo.getAuthors().size());
        assertEquals("Rampage Systems, Inc.", bookInfo.getAuthors().get(0));
        assertEquals(BookFormat.BF_PDF, bookInfo.getFormat());
    }
}