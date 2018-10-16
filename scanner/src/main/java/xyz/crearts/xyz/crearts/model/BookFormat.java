package xyz.crearts.xyz.crearts.model;

/**
 * @author ivan.kishchenko
 */
public enum BookFormat {
    BF_EPUB(".epub"),
    BF_FB2(".fb2"),
    BF_FB2ZIP(".fb2.zip"),
    BF_PDF(".pdf");

    BookFormat(String suffix) {
        this.suffix = suffix;
    }

    private String suffix;

    public String getSuffix() {
        return suffix;
    }
}
