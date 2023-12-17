package org.example.client;

public interface ClientView {
    String getName();
    String getText();
    void printMessage(String text);
    void setConnectedView();
    void setDisconnectedView();
}
