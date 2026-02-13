import java.math.*;

public class FuelPump {
    int id, openedAtDay;
    double flowRate;
    boolean busy;
    Car currentCar;

    public FuelPump(int id, int openedAtDay, double flowRate)
    {
        this.id=id;
        this.openedAtDay=openedAtDay;
        this.flowRate=flowRate;
        busy=false;
        currentCar=null;
    }

    public boolean isBusy() {
        return busy;
    }
    public void setBusy(boolean busy) {
        this.busy = busy;
    }
    public void setCurrentCar(Car currentCar) {
        this.currentCar = currentCar;
    }
    public long getServiceTime()
    {
        return (long)Math.ceil(currentCar.getDesiredFuel()/flowRate);
    }
    public boolean isServiceEnded(long time)
    {
        return !busy||time>=Math.ceil(currentCar.getDesiredFuel()/flowRate);
    }
}
