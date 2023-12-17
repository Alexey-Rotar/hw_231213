package org.example.server;

import org.example.client.ClientGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Server extends JFrame {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 300;
    private final JTextArea log = new JTextArea();
    private final JPanel panelBottom = new JPanel(new GridLayout(1, 2));
    private final JButton btnStart = new JButton("Start");
    private final JButton btnStop = new JButton("Stop");
    private boolean isServerWorking;
    private ArrayList<ClientGUI> clientList = new ArrayList<>();
    private final String LOGFILE_PATH = "log.txt";

    public boolean isServerWorking() {
        return isServerWorking;
    }

    public Server() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(WIDTH, HEIGHT);
        setTitle("Server");

        panelBottom.add(btnStart, BorderLayout.EAST);
        panelBottom.add(btnStop, BorderLayout.WEST);
        add(panelBottom, BorderLayout.SOUTH);

        log.setEditable(false);
        JScrollPane scrollLog = new JScrollPane(log);
        add(scrollLog);
        setVisible(true);

        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isServerWorking = true;
                log.append("Сервер запущен!\n");
            }
        });

        btnStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isServerWorking = false;
                log.append("Сервер остановлен!\n");
            }
        });
    }

    public void processMessage(String text) {
        log.append(text);
        writeFile(text, LOGFILE_PATH);
        for (ClientGUI clientGUI: clientList) {
            clientGUI.printMessage(text);
        }
    }

    public void addClient(ClientGUI client) {
        clientList.add(client);
    }

    private void writeFile(String text, String filename) {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(filename, true))) {
            bw.write(text);
        }
        catch(IOException ex) {
            log.append("Ошибка записи в лог!");
        }
    }

}
