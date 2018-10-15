package xyz.crearts.service.impl;

import nl.siegmann.epublib.domain.Book;
import nl.siegmann.epublib.epub.EpubReader;
import xyz.crearts.service.BookInfoReader;
import xyz.crearts.xyz.crearts.model.BookInfo;

import java.io.FileInputStream;
import java.io.InputStream;

/**
 * @author ivan.kishchenko
 */
public class EpubBookIndoReader implements BookInfoReader {

    public Book readEpub(InputStream is) throws Exception {
        // read epub file
        EpubReader epubReader = new EpubReader();
        return epubReader.readEpub(is);
    }

    @Override
    public BookInfo read(InputStream is) throws Exception {
        Book book = this.readEpub(is);

        return null;
    }

    @Override
    public BookInfo read(String filePath) throws Exception {
        Book book = this.readEpub(new FileInputStream(filePath));

        return null;
    }
}
