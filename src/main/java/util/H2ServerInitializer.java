package util;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import org.h2.tools.Server;

public class H2ServerInitializer implements ServletContextListener {
    private Server server;

    /**
     * Метод создает TCP-сервер для подключения к in-memory H2 базе данных.
     */
    @Override
    public void contextInitialized(ServletContextEvent event) {
        try {
            server = Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", "9092").start();
        } catch (Exception ignored) {
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        if (server != null) {
            server.stop();
        }
    }
}
