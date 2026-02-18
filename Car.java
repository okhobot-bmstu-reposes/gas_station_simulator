import java.util.Random;

public class Car {
    int id;
    double tankCapacity, fuelLevel, desiredFuel;
    long arrivalTime, startServiceTime, endServiceTime;
    public Car(int id, long arrivalTime, Random random)
    {
        this.id=id;
        tankCapacity=10*(4+random.nextInt(5));
        fuelLevel=(0.1+random.nextDouble()*0.8)*tankCapacity;
        desiredFuel=Math.min(5+random.nextDouble()*(tankCapacity - fuelLevel),tankCapacity-fuelLevel);
    }
    public double getDesiredFuel() {
        return desiredFuel;
    }
    public long getArrivalTime() {
        return arrivalTime;
    }
    
    public long getStartServiceTime() {
        return startServiceTime;
    }
    public long getEndServiceTime() {
        return endServiceTime;
    }
    public void setStartServiceTime(long startServiceTime) {
        this.startServiceTime = startServiceTime;
    }
    public void setEndServiceTime(long endServiceTime) {
        this.endServiceTime = endServiceTime;
    }

}
