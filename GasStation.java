import java.util.List;
import java.util.Queue;

public class GasStation {
    List<FuelPump> pumps;
    Queue<Car> queue;
    double fuelReserve, maxFuelCapacity, lastDeliveryTime, deliveryInterval, deliveryVolume;

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
            car.setEndServiceTime(pump.getServiceTime());
            fuelReserve -= car.getDesiredFuel();
            return new Event(EventType.SERVICE_END, car.getEndServiceTime());
        }
        return null;
    }

    void processCars(Queue<Event> events, long time) {
        Integer pumpIndx = findFreePumpIndx();
        while (queue.size() > 0 && pumpIndx != null) {
            Car car = queue.poll();
            Event result = serviceCar(car, pumpIndx, time);
            if (result != null)
                events.add(result);
            pumpIndx = findFreePumpIndx();

            if (car.getStartServiceTime() - car.getArrivalTime() > 12 * 60
                    && !events.stream().anyMatch(e -> e.getType() == EventType.PUMP_OPEN))
                events.add(new Event(EventType.PUMP_OPEN, time + 2 * 24 * 60 * 60));
        }
    }

    public void addCar(Car car) {
        queue.add(car);
    }

    public void simulationStep(Queue<Event> events) {
        Event e = events.poll();
        switch (e.getType()) {
            case ARRIVAL:
                break;
            case SERVICE_END: {
                for (int i = 0; i < pumps.size(); i++)
                    if (pumps.get(i).isServiceEnded(e.getTime())) {
                        pumps.get(i).setBusy(false);
                    }
            }
                break;
            case DELIVERY: {
                fuelReserve = Math.min(fuelReserve + deliveryVolume, maxFuelCapacity);
            }
                break;
            case PUMP_OPEN: {
                pumps.add(new FuelPump(pumps.size(), (int) e.getTime() / 60 / 60 / 24, 20.0));
            }
                break;
            default:
                break;
        }
        processCars(events, e.getTime());
    }

}
