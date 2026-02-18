import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;

public class GasStation {
    Statistics stats;
    List<FuelPump> pumps;
    Queue<Car> queue;
    double fuelReserve, maxFuelCapacity, deliveryVolume;
    Integer findFreePumpIndx() {
        for (int i = 0; i < pumps.size(); i++)
            if (!pumps.get(i).isBusy())
                return i;
        return null;
    }

    Event serviceCar(Car car, Integer pumpIndx, long time) {
        if (car != null && fuelReserve >= car.getDesiredFuel() && pumpIndx != null) {
            FuelPump pump = pumps.get(pumpIndx);
            pump.setBusy(true);
            pump.setCurrentCar(car);
            car.setStartServiceTime(time);
            long serviceTime=pump.getServiceTime();
            car.setEndServiceTime(time+serviceTime);
            fuelReserve -= car.getDesiredFuel();

            pump.addWorkTime(serviceTime);

            stats.addServiced();
            stats.addWaitingTime(time-car.getArrivalTime());
            stats.checkMaxWaitingTime(time-car.getArrivalTime());
            stats.addServiceTime(serviceTime);

            return new Event(EventType.SERVICE_END, car.getEndServiceTime());
        }
        return null;
    }

    public GasStation(Statistics stats)
    {
        fuelReserve=Config.getInstance().getInitialFuel();
        maxFuelCapacity=Config.getInstance().getMaxFuelCapacity();
        deliveryVolume=Config.getInstance().getDeliveryVolume();
        pumps=new ArrayList<>();
        for(int i=0;i<Config.getInstance().getInitialPumps();i++)
            pumps.add(new FuelPump(i, 0, Config.getInstance().getDefaultFlowRate()/60.0));
        queue=new PriorityQueue<>((c1,c2) -> (int)(c1.getArrivalTime() - c2.getArrivalTime()));
        this.stats=stats;
    }

    public void processCars(Queue<Event> events, long time) {
        stats.checkMaxQueueLen(queue.size());

        Integer pumpIndx = findFreePumpIndx();
                                                        //System.out.println(pumpIndx+" "+queue.size());

        while (queue.size() > 0 && pumpIndx != null) {
            Car car = queue.poll();
            Event result = serviceCar(car, pumpIndx, time);
            if (result != null)
                events.add(result);
            pumpIndx = findFreePumpIndx();

            if (car.getStartServiceTime() - car.getArrivalTime() > Config.getInstance().getQueueThreshold() * 60
                    && !events.stream().anyMatch(e -> e.getType() == EventType.PUMP_OPEN))
                events.add(new Event(EventType.PUMP_OPEN, time + Config.getInstance().getExpansionDelay() * 24 * 60 * 60));
        }
    }

    public void addCar(long time, Random rand) {
        queue.add(new Car(queue.size(), time, rand));
    }

    public void deliveryFuel() {
        stats.addFuelRemaining(fuelReserve);
        stats.checkMinFuelRemaining(fuelReserve);

        fuelReserve = Math.max(Config.getInstance().getMaxFuelCapacity(), Math.min(fuelReserve + deliveryVolume, maxFuelCapacity));
    }

    public void addPump(int openedAtDay, double flowRate) {
        pumps.add(new FuelPump(pumps.size(), openedAtDay, flowRate/60.0));
        stats.setResultPumpsCount(pumps.size());
    }

    public void checkIfPumpsFree(long time) {
        for (int i = 0; i < pumps.size(); i++)
            if (pumps.get(i).isServiceEnded(time)) {
                pumps.get(i).setBusy(false);
            }
    }
    public List<FuelPump> getPumps()
    {
        return pumps;
    }
}
