package xyz.crearts.service;

import lombok.Cleanup;
import org.apache.commons.lang3.StringUtils;
import xyz.crearts.xyz.crearts.model.BookFormat;
import xyz.crearts.xyz.crearts.model.BookInfo;

import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.ZipInputStream;

/**
 * @author ivan.kishchenko
 */
public interface BookReader {

    BookFormat format();

    BookInfo read(InputStream is) throws Exception;

    /**
     *
     * @param filePath
     * @return
     * @throws Exception
     */
    default BookInfo read(String filePath) throws Exception {
        return read(Paths.get(filePath));
    }

    /**
     *
     * @param filePath
     * @return
     * @throws Exception
     */
    default BookInfo read(Path filePath) throws Exception {
        try (InputStream is = new FileInputStream(filePath.toFile())) {
            BookInfo bookInfo = read(is);
            if (StringUtils.isEmpty(bookInfo.getTitle())) {
                String fileName = filePath.getFileName().toString();
                bookInfo.setTitle(
                    StringUtils.left(fileName, fileName.length() - format().getSuffix().length())
                );
            }

            return bookInfo;
        }
    }

}
