package xyz.crearts.service.impl;

import org.junit.Test;
import xyz.crearts.service.BookInfoReader;
import xyz.crearts.xyz.crearts.model.BookInfo;
import xyz.crearts.xyz.crearts.model.fb.Fb2Author;
import xyz.crearts.xyz.crearts.model.fb.Fb2Binary;
import xyz.crearts.xyz.crearts.model.fb.Fb2BookInfo;
import xyz.crearts.xyz.crearts.model.fb.Fb2CoverPage;

import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static org.junit.Assert.*;

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
                assertEquals(authors.size(), 1);
                assertEquals(authors.get(0).getFirstName(), "Франциска");
                assertNull(authors.get(0).getMiddleName());
                assertEquals(authors.get(0).getLastName(), "Вудворт");
                Fb2CoverPage coverPage = bookInfo.getDescription().getTitleInfo().getCoverPage();
                assertEquals(coverPage.getImage().size(), 1);
                assertEquals(coverPage.getImage().get(0).getHref(), "#cover.jpg");

                List<Fb2Binary> binaries = bookInfo.getBinary();
                assertEquals(binaries.size(), 1);
                assertEquals(binaries.get(0).getId(), "cover.jpg");
            }
        }
    }

    /*
    @Test
    public void read() throws Exception {
        Fb2BookInfoReader reader = new Fb2BookInfoReader();

        try (ZipInputStream zis = new ZipInputStream(getClass().getClassLoader().getResourceAsStream("test.fb2.zip"))) {
            if (zis.getNextEntry() != null) {
                BookInfo bookInfo = reader.read(zis);
                assertEquals(bookInfo.getTitle(), "Аромат невинности. Дыхание жизни");
                assertEquals(bookInfo.getAuthors().size(), 1);
                assertEquals(bookInfo.getAuthors().get(0), "Франциска Вудворт");
            }
        }
    }
    */
}