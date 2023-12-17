package org.example.repository;

import java.io.*;

public class File implements Repository {

    @Override
    public String loadLog(String filename) {
        StringBuilder text = new StringBuilder();
        try(BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String str;
            while ((str = br.readLine()) != null) {
                text.append(str).append("\n");
            }
        }
        catch(IOException ex) {
            text.append("История сообщений не загружена!");
        }
        return text.toString();
    }

    @Override
    public boolean saveLog(String text, String filename) {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(filename, true))) {
            bw.write(text);
            return true;
        }
        catch(IOException ex) {
            return false;
        }
    }
}
