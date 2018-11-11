package xyz.crearts.server.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.helidon.config.Config;
import io.helidon.webserver.*;
import xyz.crearts.server.dto.Response;
import xyz.crearts.service.Scanner;
import xyz.crearts.service.bookreader.EpubBookReader;
import xyz.crearts.service.bookreader.Fb2BookReader;
import xyz.crearts.service.bookreader.Fb2ZipBookReader;
import xyz.crearts.service.bookreader.PdfBookReader;
import xyz.crearts.xyz.crearts.model.BookInfo;

import java.io.IOException;

public class ScannerService implements Service {
    private final static String CONFIG_STORAGE_PATH = "path";

    private Scanner scanner;
    private Config config;
    ObjectMapper mapper;


    public ScannerService(Config config) {
        this.scanner = new Scanner(
                new Fb2BookReader(),
                new Fb2ZipBookReader(),
                new EpubBookReader(),
                new PdfBookReader()
        );
        this.config = config;
    }


    @Override
    public void update(Routing.Rules rules) {
        rules
                .post("/scan", this::scan);
    }

    /**
     * Return a greeting message using the name that was provided.
     * @param request the server request
     * @param response the server response
     */
    private void scan(final ServerRequest request, final ServerResponse response) {
        try {
            scanner.scan(config.get(CONFIG_STORAGE_PATH).asString(), (BookInfo info) -> {
                System.out.println(info.getTitle());
                //System.out.println(info.getAuthors().toString());
            });
            response.send(mapper.writeValueAsString(Response.success("ok")));
        } catch (IOException e) {
            response.send(e.toString());
        }
    }
}