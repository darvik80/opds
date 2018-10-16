package xyz.crearts.service.bookreader;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import xyz.crearts.service.BookReader;
import xyz.crearts.xyz.crearts.model.BookFormat;
import xyz.crearts.xyz.crearts.model.BookInfo;
import xyz.crearts.xyz.crearts.model.fb.Fb2Binary;
import xyz.crearts.xyz.crearts.model.fb.Fb2BookInfo;

import java.io.InputStream;
import java.util.stream.Collectors;

/**
 * @author ivan.kishchenko
 */
public class Fb2BookReader implements BookReader {
    public Fb2BookInfo readFb2(InputStream is) throws Exception {
        Serializer serializer = new Persister();

        return serializer.read(Fb2BookInfo.class, is, false);
    }

    @Override
    public BookFormat format() {
        return BookFormat.BF_FB2;
    }

    @Override
    public BookInfo read(InputStream is) throws Exception {
        return convertFb2ToBookInfo(this.readFb2(is));
    }

    private BookInfo convertFb2ToBookInfo(Fb2BookInfo fb2BookInfo) {
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
            .format(format())
            .build();

    }
}
