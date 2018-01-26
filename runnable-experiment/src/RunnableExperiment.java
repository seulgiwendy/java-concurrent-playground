import java.util.stream.IntStream;

public class RunnableExperiment {

    public static void main(String[] args) {
        IntStream.range(1, 6).forEach(i -> new Bomee().run());
        IntStream.range(1, 6).forEach(i -> new Runner().runRunnable(() -> System.out.println("Gaeul EE!")));
    }

    static class Runner {
        public void runRunnable(Runnable runnable) {
            runnable.run();
        }

    }
}
