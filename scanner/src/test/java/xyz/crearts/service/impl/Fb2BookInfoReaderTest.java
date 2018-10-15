package xyz.crearts.service.impl;

import org.junit.Test;
import xyz.crearts.xyz.crearts.model.BookFormat;
import xyz.crearts.xyz.crearts.model.BookInfo;
import xyz.crearts.xyz.crearts.model.fb.Fb2Author;
import xyz.crearts.xyz.crearts.model.fb.Fb2Binary;
import xyz.crearts.xyz.crearts.model.fb.Fb2BookInfo;
import xyz.crearts.xyz.crearts.model.fb.Fb2CoverPage;

import java.util.List;
import java.util.zip.ZipInputStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * @author ivan.kishchenko
 */
public class Fb2BookInfoReaderTest {

    @Test
    public void readFb2() throws Exception {
        Fb2BookInfoReader reader = new Fb2BookInfoReader();

        try (ZipInputStream zis = new ZipInputStream(getClass().getClassLoader().getResourceAsStream("test.fb2.zip"))) {
            if (zis.getNextEntry() != null) {
                Fb2BookInfo bookInfo = reader.readFb2(zis);
                assertEquals(bookInfo.getDescription().getTitleInfo().getBookTitle(), "Аромат невинности. Дыхание жизни");

                List<Fb2Author> authors = bookInfo.getDescription().getTitleInfo().getAuthor();
                assertEquals(1, authors.size());
                assertEquals("Франциска", authors.get(0).getFirstName());
                assertNull(authors.get(0).getMiddleName());
                assertEquals("Вудворт", authors.get(0).getLastName());
                Fb2CoverPage coverPage = bookInfo.getDescription().getTitleInfo().getCoverPage();
                assertEquals(1, coverPage.getImage().size());
                assertEquals("#cover.jpg", coverPage.getImage().get(0).getHref());

                List<Fb2Binary> binaries = bookInfo.getBinary();
                assertEquals(1, binaries.size());
                assertEquals("cover.jpg", binaries.get(0).getId());
            }
        }
    }

    @Test
    public void read() throws Exception {
        Fb2BookInfoReader reader = new Fb2BookInfoReader();

        try (ZipInputStream zis = new ZipInputStream(getClass().getClassLoader().getResourceAsStream("test.fb2.zip"))) {
            if (zis.getNextEntry() != null) {
                BookInfo bookInfo = reader.read(zis);
                assertEquals(bookInfo.getTitle(), "Аромат невинности. Дыхание жизни");
                assertEquals(1, bookInfo.getAuthors().size());
                assertEquals("Франциска Вудворт", bookInfo.getAuthors().get(0));
                assertEquals(BookFormat.BF_FB2, bookInfo.getFormat());
            }
        }
    }
}