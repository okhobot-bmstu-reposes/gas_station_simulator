import java.util.List;

public class Statistics {
    double sumFuelRemaining = 0, minFuelRemaining = 0;
    int arriived = 0, serviced = 0;
    int maxQueueLen = 0, resultPumpsCount = 0, deliveryCount = 0;
    int startPumpsCount=0;
    long sumWaitingTime = 0, maxWaitingTime = 0, sumServiceTime = 0, simulationTime;

    public Statistics(int startPumpsCount, long simulationTime)
    {
        this.startPumpsCount=startPumpsCount;
        this.simulationTime=simulationTime;
    }

    public void setArriived(int arriived) {
        this.arriived = arriived;
    }

    public void addServiced() {
        this.serviced++;
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

    public String getString(List<FuelPump> pumps)
    {
        String res="";
        res+="Всего прибыло автомобилей: "+arriived+";\n"; 
        res+="Обслужено автомобилей: "+serviced+";\n"; 
        res+="Отказано (нехватка топлива): "+(arriived-serviced)+";\n"; 
        res+="Среднее время ожидания в очереди: "+sumWaitingTime/serviced+";\n"; 
        res+="Максимальное время ожидания: "+maxWaitingTime+";\n"; 
        res+="Среднее время обслуживания: "+sumServiceTime/serviced+";\n"; 
        res+="Максимальная длина очереди: "+maxQueueLen+";\n"; 
        res+="Итоговое количество колонок: "+resultPumpsCount+";\n"; 
        res+="Количество открытых доп. колонок: "+(resultPumpsCount-startPumpsCount)+";\n"; 
        res+="Количество доставок топлива: "+deliveryCount+";\n"; 
        res+="Средний остаток топлива: "+sumFuelRemaining/deliveryCount+";\n"; 
        res+="Минимальный остаток топлива: "+minFuelRemaining+";\n"; 
        res+="Загруженность колонок (%):\n";
        for(int i=0;i<pumps.size();i++)
            res+=i+": "+pumps.get(i).getWorkTime()*100/simulationTime+'\n';
        
        return res;
    }

}
