package org.example.client;

public interface CView {
    public String getName();
    public String getText();
    public void printMessage(String text);
    public void setConnectedView();
    public void setDisconnectedView();
}
