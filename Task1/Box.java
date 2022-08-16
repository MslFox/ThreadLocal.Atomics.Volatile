package Netology.Multithread_Functional.Multithread.VolatileTthreadlocalAtomics.Task1;

public class Box {
    private boolean stop;
    private final Toggle toggle;

    public Box(Toggle toggle) {
        this.toggle = toggle;
    }

    public void playGame(){
        while (!stop) {
            toggle.switchOFF();
        }
    }
    public void gameOver() {
        stop = true;
    }
}
