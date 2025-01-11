package ru.netology;

import ru.netology.SearchEngine.BooleanSearchEngine;
import ru.netology.Server.Server;
import ru.netology.Server.ServerConfig;
import ru.netology.Server.ServerImpl;

import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        String pdfsDir = "pdfs";
        Server server = new ServerImpl(new BooleanSearchEngine(new File(pdfsDir)));
        server.start(ServerConfig.port);
    }
}