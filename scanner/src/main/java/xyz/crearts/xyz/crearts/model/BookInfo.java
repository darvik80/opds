package xyz.crearts.xyz.crearts.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @author ivan.kishchenko
 */
@Data
@Builder
public class BookInfo {
    BookFormat format;
    String title;
    List<String> authors;
    String image;
}
