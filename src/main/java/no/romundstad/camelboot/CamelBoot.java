package no.romundstad.camelboot;

import org.apache.camel.spring.boot.FatJarRouter;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main application class
 */
@SpringBootApplication
public class CamelBoot extends FatJarRouter {

    @Override
    public void configure() throws Exception {
        from("netty-http:http://localhost:18080")
                .setHeader("test", simple("test"))
                .to("http4://hotell.difi.no/api/json/vegvesen/bomstasjoner?bridgeEndpoint=true");
    }

}
