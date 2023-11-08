package ex;

public class Philosopher implements Runnable {

    private final Object leftFork;
    private final Object rightFork;

    public Philosopher(Object leftFork, Object rightFork) {
        this.leftFork = leftFork;
        this.rightFork = rightFork;
    }

    @Override
    public void run() {
        try {
            while (true) {
                // Размыление
                doAction("Размышляет");
                synchronized (leftFork) {
                    doAction("Взял левую вилку");
                    synchronized (rightFork) {
                        // Трапезничать
                        doAction("Взял правую вилку - ест");
                        doAction("Отложите правую вилку");
                    }
                    // Вернутся к размышлениям
                    doAction(": Отложите левую вилку. Вернемся к размышлениям");
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void doAction(String action) throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " - " + action);
        Thread.sleep(((int) (Math.random() * 100)));
    }
}
