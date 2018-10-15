package xyz.crearts.service.impl;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import xyz.crearts.service.BookInfoReader;
import xyz.crearts.xyz.crearts.model.BookFormat;
import xyz.crearts.xyz.crearts.model.BookInfo;
import xyz.crearts.xyz.crearts.model.fb.Fb2Binary;
import xyz.crearts.xyz.crearts.model.fb.Fb2BookInfo;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.stream.Collectors;
import java.util.zip.ZipInputStream;

/**
 * @author ivan.kishchenko
 */
public class Fb2BookInfoReader implements BookInfoReader {
    private static final String SUFFIX_FB2_ZIP = ".fb2.zip";
    private static final String SUFFIX_FB2 = ".fb2";


    public Fb2BookInfo readFb2(InputStream is) throws Exception {
        Serializer serializer = new Persister();

        return serializer.read(Fb2BookInfo.class, is, false);
    }

    @Override
    public BookInfo read(InputStream is) throws Exception {
        return convertFb2ToBookInfo(this.readFb2(is), BookFormat.BF_FB2);
    }

    @Override
    public BookInfo read(String filePath) throws Exception {
        InputStream is = new FileInputStream(filePath);

        if (filePath.endsWith(SUFFIX_FB2_ZIP)) {
            try (ZipInputStream zis = new ZipInputStream(is)) {
                if (zis.getNextEntry() == null) {
                    return convertFb2ToBookInfo(this.readFb2(is), BookFormat.BF_FB2ZIP);
                }
            }

            return null;
        }

        return convertFb2ToBookInfo(this.readFb2(is), BookFormat.BF_FB2);
    }

    private BookInfo convertFb2ToBookInfo(Fb2BookInfo fb2BookInfo, BookFormat format) {
        String image = null;
        if (fb2BookInfo.getDescription().getTitleInfo().getCoverPage().getImage().size() > 0) {
            image = fb2BookInfo.getDescription().getTitleInfo().getCoverPage().getImage().get(0).getHref();
            if (image.startsWith("#")) {
                image = image.substring(1);
            }
            for (Fb2Binary binary :fb2BookInfo.getBinary()) {
                if (binary.getId().equals(image)) {
                    image = binary.getData();
                    break;
                }
            }
        }

        return BookInfo.builder()
            .title(fb2BookInfo.getDescription().getTitleInfo().getBookTitle())
            .authors(
                fb2BookInfo
                    .getDescription()
                    .getTitleInfo()
                    .getAuthor()
                    .stream()
                    .map(item -> {
                        StringBuilder builder = new StringBuilder();
                        if (item.getFirstName() != null) {
                            builder.append(item.getFirstName());
                            builder.append(" ");
                        }
                        if (item.getMiddleName() != null) {
                            builder.append(item.getMiddleName());
                            builder.append(" ");
                        }
                        if (item.getLastName() != null) {
                            builder.append(item.getLastName());
                            builder.append(" ");
                        }
                        if (item.getNickname() != null) {
                            builder.append(item.getNickname());
                            builder.append(" ");
                        }
                        if (item.getEmail() != null) {
                            builder.append(",");
                            builder.append(item.getEmail());
                        }

                        return builder.toString().trim();
                    }).collect(Collectors.toList())
            )
            .image(image)
            .format(format)
            .build();

    }
}
