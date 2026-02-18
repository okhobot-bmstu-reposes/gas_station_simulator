public class Statistics {
    int arriived = 0, refused = 0;
    int maxQueueLen = 0, resultPumpsCount = 0, deliveryCount = 0;
    int sumFuelRemaining = 0, minFuelRemaining = 0, sumPumpsWorkload = 0;
    int startPumpsCount=0;
    long sumWaitingTime = 0, maxWaitingTime = 0, sumServiceTime = 0;

    public Statistics(int startPumpsCount)
    {
        this.startPumpsCount=startPumpsCount;
    }

    public void setArriived(int arriived) {
        this.arriived = arriived;
    }

    public void addRefused() {
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

    public void addFuelRemaining(int fuelRemaining) {
        this.sumFuelRemaining += fuelRemaining;
    }

    public void checkMinFuelRemaining(int minFuelRemaining) {
        this.minFuelRemaining = Math.max(this.minFuelRemaining, minFuelRemaining);
    }

    public void addPumpsWorkload(int pumpsWorkload) {
        this.sumPumpsWorkload += pumpsWorkload;
    }

    public void addWaitingTime(long waitingTime) {
        this.sumWaitingTime += waitingTime;
    }

    public void checkMaxWaitingTime(long maxWaitingTime) {
        this.maxWaitingTime = Math.max(this.maxWaitingTime, maxWaitingTime);
    }

    public void addServiceTime(long serviceTime) {
        this.sumServiceTime = serviceTime;
    }

    public String getString()
    {
        String res="";
        res+="Всего прибыло автомобилей: "+arriived+";\n"; 
        res+="Обслужено автомобилей: "+(arriived-refused)+";\n"; 
        res+="Отказано (нехватка топлива): "+refused+";\n"; 
        res+="Среднее время ожидания в очереди: "+sumWaitingTime/(arriived-refused)+";\n"; 
        res+="Максимальное время ожидания: "+maxWaitingTime+";\n"; 
        res+="Среднее время обслуживания: "+sumServiceTime/(arriived-refused)+";\n"; 
        res+="Максимальная длина очереди: "+maxQueueLen+";\n"; 
        res+="Итоговое количество колонок: "+resultPumpsCount+";\n"; 
        res+="Количество открытых доп. колонок: "+(resultPumpsCount-startPumpsCount)+";\n"; 
        res+="Количество доставок топлива: "+deliveryCount+";\n"; 
        res+="Средний остаток топлива: "+sumFuelRemaining/deliveryCount+";\n"; 
        res+="Минимальный остаток топлива: "+minFuelRemaining+";\n"; 
        res+="Загруженность колонок (%): "+sumPumpsWorkload/resultPumpsCount+";\n"; 
        
        return res;
    }

}
