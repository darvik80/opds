package xyz.crearts.service.impl;

import nl.siegmann.epublib.domain.Book;
import nl.siegmann.epublib.epub.EpubReader;
import org.apache.commons.lang3.StringUtils;
import xyz.crearts.service.BookInfoReader;
import xyz.crearts.xyz.crearts.model.BookFormat;
import xyz.crearts.xyz.crearts.model.BookInfo;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.stream.Collectors;

/**
 * @author ivan.kishchenko
 */
public class EpubBookIndoReader implements BookInfoReader {

    public Book readEpub(InputStream is) throws Exception {
        // read epub file
        EpubReader epubReader = new EpubReader();
        return epubReader.readEpub(is);
    }

    private BookInfo convertEpubToBookInfo(Book book) throws IOException {
        return BookInfo.builder()
            .title(book.getTitle())
            .authors(book.getMetadata()
                .getAuthors()
                .stream()
                .map(item -> {
                    StringBuilder builder = new StringBuilder();
                    if (!StringUtils.isEmpty(item.getFirstname())) {
                        builder.append(item.getFirstname());
                        builder.append(" ");
                    }
                    if (!StringUtils.isEmpty(item.getLastname())) {
                        builder.append(item.getLastname());
                        builder.append(" ");
                    }

                    return builder.toString().trim();
                }).collect(Collectors.toList())
            )
            .image(Base64.getEncoder().encodeToString(book.getCoverImage().getData()))
            .format(BookFormat.BF_EPUB)
            .build();
    }

    @Override
    public BookInfo read(InputStream is) throws Exception {
        return convertEpubToBookInfo(this.readEpub(is));
    }

    @Override
    public BookInfo read(String filePath) throws Exception {
        return read(new FileInputStream(filePath));
    }
}
