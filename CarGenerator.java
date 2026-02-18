import java.util.*;

public class CarGenerator {
    Random random;

    double getLambda(long time) {
        double l;
        long mins = time / 60;
        mins = mins % (24 * 60) / 60;
        if (mins < 6)
            l = 0.03;
        else if (mins < 10)
            l = 0.25;
        else if (mins < 16)
            l = 0.12;
        else if (mins < 20)
            l = 0.3;
        else
            l = 0.08;
        return l;
    }

    long getDeltaTime(long time) {
        return -Math.round(Math.log(random.nextDouble()) / getLambda(time));
    }

    public CarGenerator(Random random)
    {
        this.random=random;
    }

    public int generate(Queue<Event> events, long simulationTime) {
        int count=0;
        for (long time=0;time < simulationTime;time+=getDeltaTime(time)) {
            events.add(new Event(EventType.ARRIVAL, time));
            count++;
        }
        return count;

    }

}
