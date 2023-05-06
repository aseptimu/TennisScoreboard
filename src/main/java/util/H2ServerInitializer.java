package util;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import org.h2.tools.Server;

public class H2ServerInitializer implements ServletContextListener {
    private Server server;

    @Override
    public void contextInitialized(ServletContextEvent event) {
        try {
            server = Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", "9092").start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        if (server != null) {
            server.stop();
        }
    }
}
