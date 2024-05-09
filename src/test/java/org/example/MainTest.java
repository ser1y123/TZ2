package org.example;

import org.junit.jupiter.api.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Random;

public class MainTest {
    private List<Integer> testList;
    long startTime;
    long endTime;
    @BeforeEach
    public void startRecord(){
        startTime = System.currentTimeMillis();
    }
    @AfterEach
    public void endRecord(){
        endTime = System.currentTimeMillis();
        System.out.println("Last test used " + (endTime - startTime) / (double) 1000 + " seconds");
    }
    @Test @BeforeEach
    public void _readTest(){
        File file = new File("testExample.txt");
        try {
            FileWriter fw = new FileWriter(file);
            fw.write("1 20 -1 511 2 23 8 -11 0 8");
            fw.flush();
            testList = Main.readFile("testExample.txt");
            Assertions.assertFalse(testList.isEmpty());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    public void _sumTest(){
        long sum = Main._sum(testList);
        Assertions.assertEquals(561, sum);
    }
    @Test
    public void _multTest_withZero(){
        Assertions.assertEquals(0, Main._mult(testList));
    }
    @Test
    public void _multTest_withoutZero(){
        testList.remove(8);
        Assertions.assertEquals(330964480, Main._mult(testList));
    }
    @Test
    public void _minTest(){
        Assertions.assertEquals(-11, Main._min(testList));
    }
    @Test
    public void _maxTest(){
        Assertions.assertEquals(511, Main._max(testList));
    }
    @Test
    public void largeFileTest(){
        File file = new File("largeTestExample.txt");
        try {
            FileWriter fw = new FileWriter(file);
            Random rand = new Random();
            for (int i = 0; i < 1000000; i++) fw.write(rand.nextInt(100) + " ");
            Assertions.assertTimeout(Duration.ofSeconds(2), () -> {
                testList = Main.readFile("largeTestExample.txt");
                long sum = Main._sum(testList);
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
