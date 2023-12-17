package org.example.server;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ServerGUI extends JFrame implements ServerView {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 300;
    private final JTextArea log = new JTextArea();
    private final JPanel panelBottom = new JPanel(new GridLayout(1, 2));
    private final JButton btnStart = new JButton("Start");
    private final JButton btnStop = new JButton("Stop");

    public ServerGUI(Server server) {
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
                server.Start();
                log.append("Сервер запущен!\n");
            }
        });

        btnStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                server.Stop();
                log.append("Сервер остановлен!\n");
            }
        });
    }

    @Override
    public void printToLog(String text) {
        log.append(text);
    }
}
