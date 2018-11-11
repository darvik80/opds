package xyz.crearts.service.bookreader;

import org.apache.pdfbox.pdmodel.PDDocument;
import xyz.crearts.service.BookReader;
import xyz.crearts.xyz.crearts.model.BookFormat;
import xyz.crearts.xyz.crearts.model.BookInfo;

import java.io.InputStream;
import java.util.Collections;

/**
 * @author ivan.kishchenko
 */
public class PdfBookReader implements BookReader {
    @Override
    public BookFormat format() {
        return BookFormat.BF_PDF;
    }

    @Override
    public BookInfo read(InputStream is) throws Exception {

        try (PDDocument doc = PDDocument.load(is)) {
            if (!doc.isEncrypted()) {
                return BookInfo.builder()
                        .authors(Collections.singletonList(doc.getDocumentInformation().getAuthor()))
                        .title(doc.getDocumentInformation().getTitle())
                        .format(format())
                        .build();
            }

            throw new Exception("Encrypted PDF");
        }
    }
}
