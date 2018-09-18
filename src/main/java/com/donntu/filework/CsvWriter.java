package com.donntu.filework;

import com.donntu.creations.interfaces.Bird;
import com.donntu.creations.interfaces.Creation;
import com.donntu.creations.interfaces.Mammal;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CsvWriter {
    public static void writeAll(List<Creation> creations) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(Constants.CSV_FILENAME))) {
            for (Creation creation : creations) {
                if (creation instanceof Bird) {
                    writeBird((Bird) creation, writer);
                } else {
                    writeMammal((Mammal) creation, writer);
                }
                writer.write('\n');
                Log.getInstance().log("Объект записан в файл CSV (" + creation + ")");
            }
        } catch (IOException e) {
            Log.getInstance().log("Ошибка записи в файл: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void writeBird(Bird bird, BufferedWriter writer) throws IOException {
        writer.write("BIRD;"
                + bird.getArial() + ";"
                + bird.getSound() + ";"
                + bird.getLimbCount() + ";"
                + bird.getSex() + ";"
                + bird.isPet() + ";"
                + bird.getColor() + ";"
                + bird.isManual() + ";"
                + bird.isSpeaking() + ";"
                + bird.getName() + ";");

    }

    private static void writeMammal(Mammal mammal, BufferedWriter writer) throws IOException {
        writer.write("MAMMAL;"
                + mammal.getArial() + ";"
                + mammal.getSound() + ";"
                + mammal.getLimbCount() + ";"
                + mammal.getSex() + ";"
                + mammal.isPet() + ";"
                + mammal.isHooves() + ";"
                + mammal.isHorns() + ";"
                + mammal.isScales() + ";"
                + mammal.isWool() + ";"
                + mammal.getName() + ";");

    }


}
