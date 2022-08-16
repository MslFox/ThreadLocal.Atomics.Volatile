package Netology.Multithread_Functional.Multithread.VolatileTthreadlocalAtomics;

import Netology.Multithread_Functional.Multithread.VolatileTthreadlocalAtomics.Task1.*;
import Netology.Multithread_Functional.Multithread.VolatileTthreadlocalAtomics.Task2.*;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        task1();
        task2(6);
    }

    public static void task1() {
        System.out.println("\nИГРА ТУМБЛЕР\n");
        Toggle toggle = new Toggle();
        Box box = new Box(toggle);
        Thread boxThread = new Thread(null, box::playGame, "Коробка");

        Player player1 = new Player(toggle, 100, 5);
        Player player2 = new Player(toggle, 100, 3);
        Player player3 = new Player(toggle, 100, 6);

        Thread playerThread1 = new Thread(null, player1::playGame, "Игрок 1");
        Thread playerThread2 = new Thread(null, player2::playGame, "Игрок 2");
        Thread playerThread3 = new Thread(null, player3::playGame, "Игрок 3");

        boxThread.start();

        playerThread1.start();
        playerThread2.start();
        playerThread3.start();

        try {
            playerThread1.join();
            playerThread2.join();
            playerThread3.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        box.gameOver();
    }

    public static void task2(int shopsValue) {
        System.out.println("\nПОДСЧЕТ ДНЕВНОЙ ВЫРУЧКИ В МАГАЗИНАХ");
        Random random = new Random();
        int[] dailyCash = new int[2_140_000_000];
        for (int i = 0; i < dailyCash.length; i++) {
            dailyCash[i] = random.nextInt();
        }

        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        LongAdder longAdder = new LongAdder();

        List<Future<Long>> futureList = IntStream.
                range(0, shopsValue).
                mapToObj(x -> executorService.submit(new Shop(dailyCash))).toList();

        futureList.forEach(x -> {
            try {
                longAdder.add(x.get());
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        });

        System.out.println("\nДневная выручка всех магазинов : " + longAdder.sum());

        executorService.shutdown();
    }
}

