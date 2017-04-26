package ru.spbau.mit;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SecondPartTasksTest {

    @Test
    public void testFindQuotes() {
        ArrayList<String> list = new ArrayList<>();
        list.add(System.getProperty("user.dir") + "/src/test/resources/Hello");
        list.add(System.getProperty("user.dir") + "/src/test/resources/Hello1");
        assertEquals(5, SecondPartTasks.findQuotes(list, "He").size());
        assertEquals(3, SecondPartTasks.findQuotes(list, "sd").size());
    }

    @Test
    public void testPiDividedBy4() {
        double accuracy = 0.001;
        double res = SecondPartTasks.piDividedBy4();
        assertTrue(Math.abs(Math.PI / 4 - SecondPartTasks.piDividedBy4()) < accuracy);
    }

    @Test
    public void testFindPrinter() {
        Map<String, List<String>> authors = new HashMap<>();
        ArrayList<String> comps = new ArrayList<>();
        comps.add("Anna Karenina");
        comps.add("Voscresenie");
        comps.add("Voina i mir");
        authors.put("Tolstoy", comps);
        ArrayList<String> comps1 = new ArrayList<>(Arrays.asList("Bratiya Karamazovy", "Idiot"));
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
    }
}