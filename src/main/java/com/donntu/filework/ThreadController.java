package com.donntu.filework;

import com.donntu.creations.interfaces.Creation;

import java.io.IOException;
import java.util.*;

public class ThreadController {
    private Set<String> filenames = new HashSet<>();
    private List<Creation> creationList = new ArrayList<>();

    public void addFile(String filename) {
        boolean add = filenames.add(filename);
        if(add) {
            Log.getInstance().log("Файл " + filename + " добавлен в очередь");
        } else {
            Log.getInstance().log("Файл " + filename + " уже существует в очереди");
        }
    }

    public void startReading() throws InterruptedException {
        List<Thread> threads = new ArrayList<>();
        for (String filename : filenames) {
            Thread thread = new CsvReader(filename);
            threads.add(thread);
        }

        for (Thread thread : threads){
            thread.start();
            Log.getInstance().log("Поток " + thread.getName() + " начал свою работу");
        }

        for (Thread thread : threads){
            thread.join();
        }

        for (Thread thread : threads) {
            if(thread instanceof CsvReader) {
                CsvReader csvReader = (CsvReader) thread;
                creationList.addAll(csvReader.getCreations());
            }
        }
    }

    public List<Creation> getCreationList() {
        return creationList;
    }
}
