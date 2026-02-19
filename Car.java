import java.util.Random;

public class Car {
    int id;
    double tankCapacity, fuelLevel, desiredFuel;
    long arrivalTime, startServiceTime, endServiceTime;
    public Car(int id, long arrivalTime, Random random)
    {
        this.id=id;
        this.arrivalTime=arrivalTime;
        tankCapacity=10*(4+random.nextInt(5));
        fuelLevel=(10+random.nextInt(91))/100.0*tankCapacity;
        desiredFuel=4+random.nextFloat()*(tankCapacity - fuelLevel-4);
        //System.out.println(desiredFuel);
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
