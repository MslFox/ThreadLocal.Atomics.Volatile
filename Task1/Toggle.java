package Netology.Multithread_Functional.Multithread.VolatileTthreadlocalAtomics.Task1;

import java.util.concurrent.atomic.AtomicBoolean;

public class Toggle {
    private final AtomicBoolean isReady = new AtomicBoolean(true);
    private boolean isOn;

    public boolean switchON() {
        if (isReady.compareAndSet(true, false)) {
            if (!isOn) {
                System.out.println(Thread.currentThread().getName() + " Тумблер \"ON\"");
                isOn = true;
                isReady.set(true);
                return true;
            }
            isReady.set(true);
        }
        return false;
    }

    public void switchOFF() {
        if (isReady.compareAndSet(true, false)) {
            if (isOn) {
                System.out.println(Thread.currentThread().getName() + " Тумблер \"OFF\"");
                isOn = false;
            }
            isReady.set(true);
        }
    }
}