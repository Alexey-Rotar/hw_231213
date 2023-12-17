package org.example.client;

import org.example.server.Server;

public class Client {
    private final ClientView clientView;
    private final Server server;
    private boolean isLogin;
    private boolean wasAdded = false;

    public Client(ClientView clientView, Server server){
        this.clientView = clientView;
        this.server = server;
    }

    public void sendMessage(String text) {
        server.processMessage(text);
    }

    public boolean isLogin() {
        return isLogin;
    }

    public void login() {
        if (server.isServerWorking()) {
            isLogin = true;
            clientView.setConnectedView();
            clientView.printMessage(server.loadLogHistory());
            server.processMessage(">>> " + clientView.getName() + " подключился к беседе\n");
            if (!wasAdded) {
                server.addClient(clientView);
                wasAdded = true;
            }
        } else {
            clientView.setDisconnectedView();
        }
    }

    public void send() {
        if (isLogin && server.isServerWorking()) {
            String text = clientView.getText();
            if (!text.isEmpty()) {
                sendMessage(clientView.getName() + ": " + text + "\n");
            }
        } else {
            clientView.setDisconnectedView();
            isLogin = false;
        }
    }
}
