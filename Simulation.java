import java.io.Console;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;

public class Simulation {
    GasStation station;
    Queue<Event> events;
    Statistics stats;
    Random rand;

    Simulation(long simulationTime) {
        stats = new Statistics(Config.getInstance().getInitialPumps(), simulationTime);

        station = new GasStation(stats);
        events = new PriorityQueue<>((e1, e2) -> (int) (e1.getTime() - e2.getTime()));
        rand = new Random(Config.getInstance().getRandomSeed());

        CarGenerator carGenerator = new CarGenerator(new Random(1));
        stats.setArriived(carGenerator.generate(events, simulationTime));

        int deliveryCount = 0;
        for (long time = 0; time < simulationTime; time += 60 * 60) {
            events.add(new Event(EventType.DELIVERY, time + 30 * 60));
            deliveryCount++;
        }
        stats.setDeliveryCount(deliveryCount);

    }

    public void run() {
        while (events.size() > 0) {
            step();
        }
    }

    public void print_result() {
        System.out.println(stats.getString(station.getPumps()));
    }

    public void step() {
        Event e = events.poll();
        switch (e.getType()) {
            case ARRIVAL:
                station.addCar(e.time, rand);
                break;
            case SERVICE_END: {
                station.checkIfPumpsFree(e.getTime());
            }
                break;
            case DELIVERY: {
                station.deliveryFuel();
            }
                break;
            case PUMP_OPEN: {
                station.addPump((int) e.getTime() / 60 / 60 / 24, 20.0);
            }
                break;
            default:
                break;
        }
        station.processCars(events, e.getTime());
    }
}
