package com.donntu.filework;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class Log {
    private Log(){}
    private static Log instance;

    private BufferedWriter writer;
    private boolean close = true;

    public static synchronized Log getInstance(){
        if(instance == null){
            instance = new Log();
            try {
                instance.writer = new BufferedWriter(new FileWriter(Constants.LOG_FILENAME));
                instance.close = false;
                instance.log("Поток логгера открыт");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return instance;
    }

    public synchronized void log(String log) {
        if(!close) {
            try {
                writer.write(new Date().toString() + " " + Thread.currentThread().getName() + " " + log + System.lineSeparator());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("closed");
        }
    }

    public synchronized void close() throws IOException {
        log("Поток записи логгера закрыт");
        close = true;
        writer.close();
    }
}
