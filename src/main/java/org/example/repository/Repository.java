package org.example.repository;

public interface Repository {
    String loadLog(String filename);
    boolean saveLog(String text, String filename);
}
