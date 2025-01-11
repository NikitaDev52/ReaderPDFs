package ru.netology;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class ServerImpl implements Server{
    private SearchEngine searchEngine;
    public ServerImpl (SearchEngine searchEngine) {
        this.searchEngine = searchEngine;
    }
    @Override
    public void start(int port) {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (true) {
                try (
                        Socket socket = serverSocket.accept();
                        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        PrintWriter out = new PrintWriter(socket.getOutputStream());
                ) {
                    String inputWord = in.readLine();
                    List<PageEntry> results = searchEngine.search(inputWord);
                    StringBuilder sb = new StringBuilder();
                    Gson gson = new Gson().newBuilder().setPrettyPrinting().create();
                    for (PageEntry pageEntry : results) {
                        sb.append(gson.toJson(pageEntry));
                    }
                    out.println(sb);
                }
            }
        } catch (IOException e) {
            System.out.println("Не могу стартовать сервер");
            e.printStackTrace();
        }
    }
}
