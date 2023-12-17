package org.example.client;

import org.example.server.Server;

public class Client {
    private CView cview;
    private Server server;
    private String text;
    public boolean isLogin;
    public boolean wasAdded = false;
    public final String LOGFILE_PATH = "log.txt";

    public Client(CView cview, Server server){
        this.cview = cview;
        this.server = server;
    }

    public void sendMessage(String text) {
        server.processMessage(text);
    }

    public void login () {
        if (server.isServerWorking()) {
            isLogin = true;
            cview.setConnectedView();
            //readFile(client.LOGFILE_PATH);
            server.processMessage(cview.getName() + " подключился к беседе\n");
            if (!wasAdded) {
                server.addClient((ClientGUI) cview);
                wasAdded = true;
            }
        } else {
            cview.setDisconnectedView();
        }
    }

    public void send () {
        if (isLogin && server.isServerWorking()) {
            text = cview.getText();
            if (!text.isEmpty()) {
                sendMessage(cview.getName() + ": " + text + "\n");
            }
        } else {
            cview.setDisconnectedView();
            isLogin = false;
        }
    }
}
