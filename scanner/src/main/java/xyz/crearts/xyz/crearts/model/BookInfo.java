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
    String title;
    List<String> authors;
    String image;
}
