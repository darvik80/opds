package xyz.crearts.service;

import xyz.crearts.xyz.crearts.model.BookInfo;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.zip.ZipInputStream;

/**
 * @author ivan.kishchenko
 */
public interface BookInfoReader {
    BookInfo read(InputStream is) throws Exception;
    BookInfo read(String filePath) throws Exception;

}
