package com.donntu;

import com.donntu.creations.interfaces.Creation;
import com.donntu.filework.Constants;
import com.donntu.filework.Log;
import com.donntu.filework.ThreadController;

import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {
//        List<Creation> creationsList = new ArrayList<>();
//
//        Cat cat = new Cat();
//        cat.build();
//        Dog dog = new Dog();
//        dog.build();
//        Cow cow = new Cow();
//        cow.build();
//
//        Collibri collibri = new Collibri();
//        collibri.build();
//        Crow crow = new Crow();
//        crow.build();
//        Dodo dodo = new Dodo();
//        dodo.build();
//
//        Collections.addAll(creationsList, cat, dog, cow);
//        Collections.addAll(creationsList, crow, dodo, collibri);
//
//        CsvWriter.writeAll(creationsList);

        ThreadController threadController = new ThreadController();
        threadController.addFile(Constants.CSV_FILENAME);
        threadController.addFile("file1.csv");
        threadController.addFile("file2.csv");
        threadController.addFile("file3.csv");
        threadController.addFile("file4.csv");
        threadController.addFile("file5.csv");

        threadController.startReading();
        List<Creation> creations = threadController.getCreationList();
        try {
            for (Creation creation : creations) {
                System.out.println(creation.getSound());
            }
        } catch (NullPointerException e){
            e.printStackTrace();
        }
//        assert creations != null;
//        Serializer.serialize(creations);
//        List<Creation> deserialize = Serializer.deserialize();
//        assert deserialize != null;
//        for (Creation creation: deserialize) {
//            System.out.println(creation.getSound());
//        }

        Log.getInstance().close();
    }
}
