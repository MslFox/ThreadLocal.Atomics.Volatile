package Netology.Multithread_Functional.Multithread.VolatileTthreadlocalAtomics.Task2;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicLong;

public class Shop implements Callable<Long> {

    private final int[] dailyCash;
    private long begin;

    public Shop(int[] dailyCash) {
        this.dailyCash = dailyCash;
    }

    public long dailyCalculate() {
        long result = 0;
        for (Integer item: dailyCash) {
            result+=item;
        }
        System.out.println("\n" + Thread.currentThread().getName() + " Начало  расчета,мс: " + begin +
                "\n" + Thread.currentThread().getName() + " Длительность расчета,мс: " + (System.currentTimeMillis() - begin) +
                "\n" + Thread.currentThread().getName() + " Дневная выручка: " + result);
        return result;
    }

    @Override
    public Long call() throws Exception {
        begin = System.currentTimeMillis();
        return dailyCalculate();
    }
}
