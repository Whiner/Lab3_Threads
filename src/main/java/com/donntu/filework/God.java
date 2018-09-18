package com.donntu.filework;

import com.donntu.creations.Sex;
import com.donntu.creations.interfaces.Bird;
import com.donntu.creations.interfaces.Creation;
import com.donntu.creations.interfaces.Mammal;

public class God extends Thread {

    private String[] split;
    private Class<? extends Creation> creationType;
    private Creation created;

    public God(String[] split, Class<? extends Creation> creationType){
        this.split = split;
        this.creationType = creationType;
    }

    public Creation getCreated() {
        return created;
    }

    @Override
    public void run() {

        try {
            if(creationType.newInstance() instanceof Bird){
                Log.getInstance().log("Поток " + getName() + " создал BIRD");
                created = new Bird(
                        split[1],
                        split[2],
                        Integer.valueOf(split[3]),
                        Sex.valueOf(split[4]),
                        Boolean.valueOf(split[5]),
                        split[6],
                        Boolean.valueOf(split[7]),
                        Boolean.valueOf(split[8]),
                        split[9]);
            } else if(creationType.newInstance() instanceof Mammal){
                Log.getInstance().log("Поток " + getName() + " создал MAMMAL");
                created = new Mammal(
                        split[1],
                        split[2],
                        Integer.valueOf(split[3]),
                        Sex.valueOf(split[4]),
                        Boolean.valueOf(split[5]),
                        Boolean.valueOf(split[6]),
                        Boolean.valueOf(split[7]),
                        Boolean.valueOf(split[8]),
                        Boolean.valueOf(split[9]),
                        split[10]);
            } else {
                System.out.println("хуй на");
                created = null;
            }
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
