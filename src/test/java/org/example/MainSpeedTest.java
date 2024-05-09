package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Random;

public class MainSpeedTest {
    List<Integer> testList;
    @Test
    public void speedTest(){
        File file = new File("testExample.txt");
        try {
            Random rand = new Random();
            for (int i = 1; i <= 1000000; i *= 10){
                FileWriter fw = new FileWriter(file);
                for (int j = 0; j < i; j++) fw.write(rand.nextInt(100) + " ");
                fw.flush();
                long start = System.currentTimeMillis();

                Assertions.assertTimeout(Duration.ofMillis(1500), () -> {
                    testList = Main.readFile("testExample.txt");
                    long sum = Main._max(testList);
                });

                long end = System.currentTimeMillis();
                fw.close();
                System.out.println("Testcase with " + i + " nums used " + (end - start) / (double) 1000 + " seconds");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
