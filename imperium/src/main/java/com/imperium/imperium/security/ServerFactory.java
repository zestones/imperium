package com.imperium.imperium.security;

import org.apache.catalina.connector.Connector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.stereotype.Component;

@Component
public class ServerFactory implements WebServerFactoryCustomizer<TomcatServletWebServerFactory> {

    private final int httpPort;
    private final int httpsPort;

    // Retrieve port value from application properties file
    @Autowired
    ServerFactory(
            @Value("${server.http.port}") int httpPort,
            @Value("${server.https.port}") int httpsPort) {
        this.httpPort = httpPort;
        this.httpsPort = httpsPort;
    }

    @Override
    public void customize(TomcatServletWebServerFactory factory) {
        factory.addAdditionalTomcatConnectors(redirectConnector());
    }

    private Connector redirectConnector() {
        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
        connector.setScheme("http");

        // Set http Port
        connector.setPort(httpPort);
        connector.setSecure(false);

        // Set the https Port
        connector.setRedirectPort(httpsPort);
        return connector;
    }

}