package epi.excercise.multi.thread;

public class PrintEvenOdd {
    public static class SynchronizedThreadMonitor {
        public final static boolean ODD_TURN = true;
        public final static boolean EVEN_TURN = false;
        private boolean turn = ODD_TURN;

        public synchronized void waitTurn(boolean oldTurn) {
            while (turn != oldTurn) { // wait must in a synchronized block to avoid notify happen betwenn condition and wait
                try {
                    // wait() releases the lock and allows other threads to enter. The lock is re-acquired before wait() returns.
                    // It holds the LOCK only small period of time (until method wait() is invoked, then thread enter "wait" status and release the LOCK)
                    // Method wait() releases the LOCK and allows another thread to enter the critical section
                    wait();
                } catch (InterruptedException e) {
                    System.out.println("InterruptedException in wait(): " + e);
                }
            }
        }

        public synchronized void toggleTurn(){
            turn ^= true;
            notify();
        }
    }

    public static class OddThread extends Thread {
        private final SynchronizedThreadMonitor monitor;

        public OddThread(SynchronizedThreadMonitor monitor) {
            this.monitor = monitor;
        }

        @Override
        public void run() {
            for (int i=1; i<=10; i+=2) {
                // OddThread and EvenThread call same monitor instance's same method, but there are 2 different method exe instances
                // one for OddThread and one for EvenThread, the reason we use the same monitor is to share the var "turn"
                // Notice: an instance's method can have multiple execution instances at the same time, such as recursive, each hold its own var
                monitor.waitTurn(SynchronizedThreadMonitor.ODD_TURN);
                System.out.println("i= " + i);
                monitor.toggleTurn();
            }
        }
    }

    public static class EvenThread extends Thread {
        private final SynchronizedThreadMonitor monitor;

        public EvenThread(SynchronizedThreadMonitor monitor) {
            this.monitor = monitor;
        }

        @Override
        public void run() {
            for (int i=2; i<=10; i+=2) {
                monitor.waitTurn(SynchronizedThreadMonitor.EVEN_TURN);
                System.out.println("i= " + i);
                monitor.toggleTurn();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        SynchronizedThreadMonitor monitor = new SynchronizedThreadMonitor();
        Thread t1 = new OddThread(monitor);
        Thread t2 = new EvenThread(monitor);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }
}
