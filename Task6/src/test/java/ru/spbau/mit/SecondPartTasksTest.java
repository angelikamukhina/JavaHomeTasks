package ru.spbau.mit;

import org.junit.Test;

import java.io.File;
import java.util.*;

import static org.junit.Assert.assertEquals;

public class SecondPartTasksTest {

    @Test
    public void testFindQuotes() {
        List<String> list;
        ClassLoader classLoader = this.getClass().getClassLoader();
        File testFile1 = new File(classLoader.getResource("Hello").getFile());
        File testFile2 = new File(classLoader.getResource("Hello1").getFile());
        list = Arrays.asList(testFile1.getPath(), testFile2.getPath());
        List<String> res1 = SecondPartTasks.findQuotes(list, "He");
        List<String> res2 = SecondPartTasks.findQuotes(list, "sd");
        List<String> correctAnswer1 = Arrays.asList("Hello", "Hel", "WorHeld",
                "He", "Heeeeeeee", "Helllllllooooo");
        List<String> correctAnswer2 = Arrays.asList("sdfghjk", "sdfghj", "sdfghjk");
        assertEquals(correctAnswer1, res1);
        assertEquals(correctAnswer2, res2);
    }

    @Test
    public void testPiDividedBy4() {
        double accuracy = 0.001;
        assertEquals(Math.PI / 4, SecondPartTasks.piDividedBy4(), accuracy);
    }

    @Test
    public void testFindPrinter() {
        Map<String, List<String>> authors = new HashMap<>();
        List<String> comps = Arrays.asList("Anna Karenina", "Voscresenie", "Voina i mir");
        authors.put("Tolstoy", comps);
        List<String> comps1 = Arrays.asList("Bratiya Karamazovy", "Idiot");
        authors.put("Dostoevsky", comps1);
        assertEquals("Tolstoy", SecondPartTasks.findPrinter(authors));
    }

    @Test
    public void testCalculateGlobalOrder() {
        List<Map<String, Integer>> orders = new ArrayList<>();
        Map<String, Integer> order = new HashMap<>();
        order.put("Apples", 100);
        order.put("Oranges", 600);
        order.put("Watermelons", 500);
        order.put("Potato", 400);
        orders.add(order);
        Map<String, Integer> order1 = new HashMap<>();
        order1.put("Chocolate", 600);
        order1.put("Apples", 500);
        order1.put("Oranges", 400);
        orders.add(order1);
        assertEquals(600, SecondPartTasks.calculateGlobalOrder(orders).get("Apples").intValue());
        assertEquals(1000, SecondPartTasks.calculateGlobalOrder(orders).get("Oranges").intValue());
        assertEquals(500, SecondPartTasks.calculateGlobalOrder(orders).get("Watermelons").intValue());
        assertEquals(400, SecondPartTasks.calculateGlobalOrder(orders).get("Potato").intValue());
        assertEquals(600, SecondPartTasks.calculateGlobalOrder(orders).get("Chocolate").intValue());
    }
}
