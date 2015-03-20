package no.romundstad.camelboot;

import org.apache.camel.component.cxf.CxfEndpoint;
import org.apache.camel.spring.boot.FatJarRouter;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * Main application class
 */
@SpringBootApplication
public class CamelBoot extends FatJarRouter {

    @Override
    public void configure() throws Exception {
        from("netty-http:http://localhost:18080/bom")
                .to("http4://hotell.difi.no/api/json/vegvesen/bomstasjoner?bridgeEndpoint=true");
        from("netty-http:http://localhost:18080/parkering")
                .to("http4://hotell.difi.no/api/json/ssb/regioner/landkoder?bridgeEndpoint=true");
        from("netty-http:http://localhost:18080/svarut")
                .to("cxf:bean:svarUtEndpoint");
    }

    @Bean
    public CxfEndpoint svarUtEndpoint() {
        CxfEndpoint cxfEndpoint = new CxfEndpoint();
        cxfEndpoint.setAddress("https://svarut.ks.no/tjenester/forsendelseservice/ForsendelsesServiceV3");
        return cxfEndpoint;
    }

}
