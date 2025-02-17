import com.sun.net.httpserver.HttpServer;

import jpa.TestDataHandler;

import org.glassfish.jersey.jdkhttp.JdkHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.net.URI;

public class WebServiceStarter {

    public static void main(String[] args) throws Exception {
        ResourceConfig rc = new ResourceConfig().packages("rest");
        HttpServer server = JdkHttpServerFactory.createHttpServer(URI.create("http://localhost:8080/"), rc);
        System.out.println("Creating Test Data");
        TestDataHandler tdh = new TestDataHandler();
        tdh.insertTestData();
        System.out.println("Hit enter to stop HTTP server.");
        System.in.read();
        server.stop(0);
    }
}
