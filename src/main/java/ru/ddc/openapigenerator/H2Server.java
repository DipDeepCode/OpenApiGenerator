package ru.ddc.openapigenerator;

import org.h2.tools.Server;

import java.sql.SQLException;

public class H2Server {
    private Server server;

    public void start() {
        try {
            server = Server.createTcpServer("-tcpPort", "9092", "-tcpAllowOthers", "-ifNotExists").start();
            System.out.println("H2 server started and listening on port 9092.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        if (server != null) {
            server.stop();
            System.out.println("H2 server stopped.");
        }
    }

    public static void main(String[] args) {
        H2Server h2Server = new H2Server();
        h2Server.start();
        Runtime.getRuntime().addShutdownHook(new Thread(h2Server::stop));
    }
}
