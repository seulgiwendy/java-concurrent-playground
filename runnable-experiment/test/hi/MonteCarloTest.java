package hi;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

public class MonteCarloTest {


    private int trial = 100000000;
    private int radius = 10000;
    List<Double> doubles = new ArrayList<>();
    private static final Random random = new Random();
    private Runnable runnable;

    @Before
    public void setUp() {
        this.runnable = () -> {
            long startTime = System.currentTimeMillis();
            int inPosition = 0;
            for(int i = trial; i --> 0;) {
                int xCoord = random.nextInt(radius);
                int yCoord = random.nextInt(radius);
                if (Math.sqrt(Math.pow(xCoord, 2) + Math.pow(yCoord, 2)) <= radius) {
                    inPosition++;
                }
            }
            doubles.add(inPosition / (double)trial);
            long endTime = System.currentTimeMillis();

            System.out.println("elapsed time : " + (endTime - startTime) + "ms");
        };
    }

    @Test
    public void 멀티스레드_원주율계산() {
        new Thread(this.runnable).run();
        new Thread(this.runnable).run();
        new Thread(this.runnable).run();
        new Thread(this.runnable).run();

        System.out.println(this.doubles.stream().mapToDouble(d -> d.doubleValue()).sum());
    }


}