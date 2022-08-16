package Netology.Multithread_Functional.Multithread.VolatileTthreadlocalAtomics.Task1;

public class Player {
    private final long DELAY_TIME;
    private final int COUNT_OPENING;
    private final Toggle toggle;

    public Player(Toggle toggle, long delayTime, int countOpening) {
        this.toggle = toggle;
        DELAY_TIME = delayTime;
        COUNT_OPENING = countOpening;
    }

    public void playGame() {
        int count = 0;
        while (count < COUNT_OPENING) {
            if (toggle.switchON()) count++;
            try {
                Thread.sleep(DELAY_TIME);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
