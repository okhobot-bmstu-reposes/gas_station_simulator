import java.util.List;

public class Statistics {
    double sumFuelRemaining = 0, minFuelRemaining;
    int arriived = 0, serviced = 0, refused=0;
    int maxQueueLen = 0, resultPumpsCount = 0, deliveryCount = 0;
    int startPumpsCount=0;
    long sumWaitingTime = 0, maxWaitingTime = 0, sumServiceTime = 0;

    public Statistics(int startPumpsCount)
    {
        this.startPumpsCount=startPumpsCount;
        this.minFuelRemaining=Config.getInstance().getMaxFuelCapacity();
    }

    public void setArriived(int arriived) {
        this.arriived = arriived;
    }

    public void addServiced() {
        this.serviced++;
    }

    public void addRefused()
    {
        this.refused++;
    }

    public void checkMaxQueueLen(int maxQueueLen) {
        this.maxQueueLen = Math.max(this.maxQueueLen, maxQueueLen);
    }

    public void setResultPumpsCount(int resultPumpsCount) {
        this.resultPumpsCount = resultPumpsCount;
    }

    public void setDeliveryCount(int deliveryCount) {
        this.deliveryCount = deliveryCount;
    }

    public void addFuelRemaining(double fuelRemaining) {
        this.sumFuelRemaining += fuelRemaining;
    }

    public void checkMinFuelRemaining(double minFuelRemaining) {
        this.minFuelRemaining = Math.min(this.minFuelRemaining, minFuelRemaining);
    }

    public void addWaitingTime(long waitingTime) {
        this.sumWaitingTime += waitingTime;
    }

    public void checkMaxWaitingTime(long maxWaitingTime) {
        this.maxWaitingTime = Math.max(this.maxWaitingTime, maxWaitingTime);
    }

    public void addServiceTime(long serviceTime) {
        this.sumServiceTime += serviceTime;
    }

    public String getString(List<FuelPump> pumps, long simulationTime)
    {
        String res="";
        res+="Всего прибыло автомобилей: "+arriived+";\n"; 
        res+="Обслужено автомобилей: "+serviced+";\n"; 
        res+="Отказано (нехватка топлива): "+refused+";\n"; 
        res+="Среднее время ожидания в очереди: "+Math.round(sumWaitingTime/serviced /60.0 *10)/10.0+" мин;\n"; 
        res+="Максимальное время ожидания: "+Math.round(maxWaitingTime /60.0 *10)/10.0+" мин;\n"; 
        res+="Среднее время обслуживания: "+Math.round(sumServiceTime/serviced /60.0 *10)/10.0+" мин;\n"; 
        res+="Максимальная длина очереди: "+maxQueueLen+";\n"; 
        res+="Итоговое количество колонок: "+resultPumpsCount+";\n"; 
        res+="Количество открытых доп. колонок: "+(resultPumpsCount-startPumpsCount)+";\n"; 
        res+="Количество доставок топлива: "+deliveryCount+";\n"; 
        res+="Средний остаток топлива: "+Math.round(sumFuelRemaining/deliveryCount)+" л;\n"; 
        res+="Минимальный остаток топлива: "+Math.round(minFuelRemaining)+" л;\n"; 
        res+="Загруженность колонок (%):\n";
        for(int i=0;i<pumps.size();i++)
            res+=i+": "+pumps.get(i).getWorkTime()*100/simulationTime+'\n';
        
        return res;
    }

}
