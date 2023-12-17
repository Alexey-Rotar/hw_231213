package org.example.server;

import org.example.client.ClientView;
import org.example.repository.File;
import org.example.repository.Repository;

import java.util.ArrayList;

public class Server {
    private boolean isServerWorking;
    private final ServerView serverView;
    private final Repository repository;
    private final ArrayList<ClientView> clientList = new ArrayList<>();
    private final String LOGFILE_PATH = "log.txt";

    public Server() {
        serverView = new ServerGUI(this);
        repository = new File();
    }

    public void Start() {
        isServerWorking = true;
    }

    public void Stop() {
        isServerWorking = false;
    }

    public boolean isServerWorking() {
        return isServerWorking;
    }

    public void processMessage(String text) {
        serverView.printToLog(text);
        if (!repository.saveLog(text, LOGFILE_PATH))
            serverView.printToLog("Ошибка записи в лог!");
        for (ClientView clientView: clientList) {
            clientView.printMessage(text);
        }
    }

    public void addClient(ClientView clientView) {
        clientList.add(clientView);
    }

    public String loadLogHistory() {
        return repository.loadLog(LOGFILE_PATH);
    }

}