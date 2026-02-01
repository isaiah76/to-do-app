package org.example;

public class Main {
    public static void main(String[] args) throws Exception {
        TaskManager tm = new TaskManager();

        try (var conn = DataSource.getConnection()) {
            System.out.println("Connected to db: " + conn.getCatalog());
        }

    }
}
