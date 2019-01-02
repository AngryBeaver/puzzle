package com.angrybeaver.puzzle.oop;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


public class Game implements Runnable {
    List<Card> deck;
    private long startTime;
    private ExecutorService executor;

    Game(List<Card> deck){
        this.deck = deck;
        startTime = System.currentTimeMillis();
        executor = Executors.newFixedThreadPool(10);
    }

    public void run(){
        try {
            new Algorithm(this).run();
        } catch (ExpandYException e) {
            e.printStackTrace();
        } catch (ExpandXException e) {
            e.printStackTrace();
        } catch (NoMatchException e) {
            e.printStackTrace();
        }
    }

    public synchronized void addThread(Algorithm algorithm){
        executor.submit(algorithm);
    }

    public String getTimeString(){
        return "Time:"+(startTime-System.currentTimeMillis())+"\n";
    }




}
