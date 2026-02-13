public class Car {
    int id;
    double tankCapacity, fuelLevel, desiredFuel;
    long arrivalTime, startServiceTime, endServiceTime;
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
