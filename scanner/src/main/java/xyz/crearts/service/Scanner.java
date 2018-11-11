package xyz.crearts.service;

import xyz.crearts.xyz.crearts.model.BookInfo;

import javax.inject.Inject;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

/**
 * @author ivan.kishchenko
 */
public class Scanner {
    private List<BookReader> readers;

    @Inject
    public Scanner(BookReader ...readers) {
        this.readers = Arrays.asList(readers);
    }

    public void scan(String dir, Consumer<BookInfo> consumer) throws IOException {
        Files.walk(Paths.get(dir))
            .filter(path -> !Files.isDirectory(path))
            .forEach(path -> {
                for (BookReader reader : readers) {
                    if (path.toString().endsWith(reader.format().getSuffix())) {
                        try {
                            BookInfo info = reader.read(path);
                            if (info != null) {
                                consumer.accept(info);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    }
                }
            });
    }
}
