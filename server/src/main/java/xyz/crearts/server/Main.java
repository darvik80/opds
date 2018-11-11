/*
 * Copyright (c) 2018 Oracle and/or its affiliates. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package xyz.crearts.server;

import java.io.IOException;
import java.security.Security;
import java.util.logging.LogManager;

import io.helidon.config.Config;
import io.helidon.webserver.Routing;
import io.helidon.webserver.ServerConfiguration;
import io.helidon.webserver.WebServer;
import io.helidon.webserver.json.JsonSupport;
import lombok.extern.slf4j.Slf4j;
import xyz.crearts.server.services.GreetService;
import xyz.crearts.server.services.OpdsService;
import xyz.crearts.server.services.ScannerService;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
/**
 * Simple Hello World rest application.
 */
@ApplicationScoped
@ApplicationPath("/")
@Slf4j
public final class Main extends Application {

    /**
     * Cannot be instantiated.
     */
    private Main() { }

    /**
     * Application main entry point.
     * @param args command line arguments.
     * @throws IOException if there are problems reading logging properties
     */
    public static void main(final String[] args) throws IOException {
        startServer();
    }

    /**
     * Start the server.
     * @return the created {@link WebServer} instance
     * @throws IOException if there are problems reading logging properties
     */
    protected static WebServer startServer() throws IOException {

        // load logging configuration
        LogManager.getLogManager().readConfiguration(
                Main.class.getResourceAsStream("/logging.properties"));

        // By default this will pick up application.yaml from the classpath
        Config config = Config.create();

        Config ds = config.get("datasource");
        log.info(ds.toString());

        // Get webserver config from the "server" section of application.yaml
        ServerConfiguration serverConfig =
                ServerConfiguration.fromConfig(config.get("server"));

        Routing routing = Routing.builder()
                .register(JsonSupport.get())
                .register("/greet", new GreetService())
                .register("/opds", new OpdsService())
                .register("/scanner", new ScannerService(config.get("storage")))
                .build();


        WebServer server = WebServer.create(serverConfig, routing);

        // Start the server and print some info.
        server.start().thenAccept(ws -> {
            log.info("WEB server is up! http://localhost:" + ws.port());
        });

        // Server threads are not demon. NO need to block. Just react.
        server.whenShutdown().thenRun(() -> {
            log.info("WEB server is DOWN. Good bye!");
        });

        return server;
    }
}
