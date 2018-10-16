package xyz.crearts.service.bookreader;

import xyz.crearts.xyz.crearts.model.BookFormat;
import xyz.crearts.xyz.crearts.model.BookInfo;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.zip.ZipInputStream;

/**
 * @author ivan.kishchenko
 */
public class Fb2ZipBookReader extends Fb2BookReader {
    @Override
    public BookFormat format() {
        return BookFormat.BF_FB2ZIP;
    }

    @Override
    public BookInfo read(String filePath) throws Exception {
        try (InputStream is = new FileInputStream(filePath);
             ZipInputStream zis = new ZipInputStream(is)
        ) {
            if (zis.getNextEntry() == null) {
                return read(is);
            }
        }

        return null;
    }
}
