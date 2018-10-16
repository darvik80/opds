package xyz.crearts.server.services;

import com.google.common.collect.Lists;
import io.helidon.webserver.Routing;
import io.helidon.webserver.ServerRequest;
import io.helidon.webserver.ServerResponse;
import io.helidon.webserver.Service;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import org.simpleframework.xml.stream.Format;
import xyz.crearts.server.consts.atom.Atom;
import xyz.crearts.server.models.atom.Content;
import xyz.crearts.server.models.atom.Entry;
import xyz.crearts.server.models.atom.Feed;
import xyz.crearts.server.models.atom.Link;
import xyz.crearts.service.BookReader;
import xyz.crearts.service.Scanner;
import xyz.crearts.service.bookreader.EpubBookReader;
import xyz.crearts.service.bookreader.Fb2BookReader;
import xyz.crearts.service.bookreader.Fb2ZipBookReader;
import xyz.crearts.service.bookreader.PdfBookReader;

import javax.inject.Inject;
import java.io.ByteArrayOutputStream;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author ivan.kishchenko
 */
public class OpdsService implements Service {
    @Inject
    private Scanner scanner;

    public OpdsService() {
        List<BookReader> readers = new ArrayList<>();
        readers.add(new Fb2BookReader());
        readers.add(new Fb2ZipBookReader());
        readers.add(new EpubBookReader());
        readers.add(new PdfBookReader());

        this.scanner = new Scanner(readers);
    }

    @Override
    public void update(Routing.Rules rules) {
        rules
            .get("/", this::getDefaultMessage);
    }

    /**
     * Return a default message
     * @param request the server request
     * @param response the server response
     */
    private void getDefaultMessage(final ServerRequest request,
                                   final ServerResponse response) {
        try {
            Serializer serializer = new Persister(new Format("<?xml version=\"1.0\" encoding= \"UTF-8\" ?>"));

            ByteArrayOutputStream os = new ByteArrayOutputStream();

            List<Link> links = new ArrayList<>();
            links.add(Link.builder().href("http://ngs.ru").type(Atom.CONTENT_TYPE_ATOM_XML).build());
            links.add(Link.builder().href("http://utro.ru").type(Atom.CONTENT_TYPE_ATOM_XML).build());

            List<Entry> entries = new ArrayList<>();
            entries.add(Entry.builder()
                .title("Global catalog")
                .contents(Lists.newArrayList(
                    Content.builder().content("Hello World").type(Atom.ATOM_TYPE_TEXT).build()
                ))
                .build());

            serializer.write(
                Feed.builder()
                    .id("tag:root")
                    .xmlns("http://www.w3.org/2005/Atom")
                    .xmlnsThr("http://purl.org/syndication/thread/1.0")
                    .xmlnsOpds("http://opds-spec.org/2010/catalog")
                    .title("Root")
                    .updated(Date.from(Instant.now()))
                    .links(links)
                    .entries(entries)
                    .build(),
                os
            );
            response.headers().add("Content-Type", Atom.CONTENT_TYPE_ATOM_XML);
            response.send(os.toByteArray());
        } catch (Exception ex) {
            response.send(ex.getMessage());
        }
    }}
