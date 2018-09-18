package com.donntu.filework;

import com.donntu.creations.interfaces.Bird;
import com.donntu.creations.interfaces.Creation;
import com.donntu.creations.interfaces.Mammal;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

public class CsvReader extends Thread {
    private List<Creation> creations = new ArrayList<>();
    private String filename;

    private void readCreations() {
        if (filename == null || !filename.toLowerCase().contains(".csv")) {
            throw new InvalidParameterException("Файл должен быть расширения .csv");
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            Log.getInstance().log("Файл (" + filename + ") открыт");
            String s;
            List<String> lines = new ArrayList<>();
            while ((s = reader.readLine()) != null) {
                lines.add(s);
            }
            createCreations(lines);

        } catch (IOException|InterruptedException e) {
            Log.getInstance().log("Ошибка чтения из файла (" + filename + "). Текст ошибки:" + e.getMessage());
            e.printStackTrace();
        }
        Log.getInstance().log("Файл (" + filename + ") закрыт");
    }

    private void createCreations(List<String> lines) throws InterruptedException {
        List<God> gods = new ArrayList<>();
        for (String line : lines) {
            String[] split = line.split(";");
            if (split.length != 11 && split.length != 10) {
                Log.getInstance().log("Ошибка на строке: " + line);
            } else {
                switch (split[0]) {
                    case "BIRD":
                        Log.getInstance().log("Получен объект типа Bird");
                        gods.add(new God(split, Bird.class));
                        break;
                    case "MAMMAL":
                        Log.getInstance().log("Получен объект типа Mammal");
                        gods.add(new God(split, Mammal.class));
                        break;
                    default:
                        Log.getInstance().log("Тип объекта неопределен. (" + split[0] + ")");
                }
            }
        }

        for (God god : gods) {
            god.start();
            Log.getInstance().log("Поток " + god.getName() + " начал свою работу");
        }
        for (God god : gods) {
            god.join();
        }

        for (God god : gods) {
            creations.add(god.getCreated());
        }

    }

    @Override
    public void run() {
        readCreations();
        Log.getInstance().log("Поток " + Thread.currentThread().getName() + " окончил свою работу");
    }

    public List<Creation> getCreations() {
        return creations;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public CsvReader() {
    }

    public CsvReader(String filename) {
        this.filename = filename;
    }
}
