package study;

public class ThreadDemo {
    public static void main(String[] args) throws InterruptedException {
        /*
         * 继承 Runnable
         */
        RunThread a = new RunThread();
        a.run(); // 阻塞
        System.out.println("run end");

        /*
         * 继承 Thread
         */
        TThread b = new TThread();
        // 设置守护进程（默认不是daemon，称为工作线程）
        // 当main线程执行完后，工作线程因为一直在运行，所以jvm进程是不会推出的
        // 而daemon线程，在main线程执行完后，会跟着jvm一起推出
        //b.setDaemon(true);
        b.start(); // 未阻塞

        // 默认main与新线程并发执行
        // join: 先执行完新开启的线程，再继续main线程的处理
        b.join();

        System.out.println(" end ");
    }

    public static class RunThread implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                System.out.println("RunThread exec... " + i);
                try {
                    Thread.sleep(200L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static class TThread extends Thread {

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                System.out.println("TThread exec... " + i);
                try {
                    Thread.sleep(200L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
