public class Config {
    private static Config instance;
    int simulationDays = 7;
    int initialPumps = 2;
    int maxPumps = 10;
    int defaultFlowRate = 20;
    int queueThreshold = 12;
    int expansionDelay = 2;
    int initialFuel = 10000;
    int maxFuelCapacity = 15000;
    int deliveryInterval = 24;
    int deliveryVolume = 10000;
    int deliveryDuration = 30;
    int randomSeed = 1;

    private Config() {}

    public static Config getInstance() {
        if (instance == null) {
            instance = new Config();
        }
        return instance;
    }

    public int getSimulationDays() {
        return simulationDays;
    }

    public int getInitialPumps() {
        return initialPumps;
    }

    public int getMaxPumps() {
        return maxPumps;
    }

    public int getDefaultFlowRate() {
        return defaultFlowRate;
    }

    public int getQueueThreshold() {
        return queueThreshold;
    }

    public int getExpansionDelay() {
        return expansionDelay;
    }

    public int getInitialFuel() {
        return initialFuel;
    }

    public int getMaxFuelCapacity() {
        return maxFuelCapacity;
    }

    public int getDeliveryInterval() {
        return deliveryInterval;
    }

    public int getDeliveryVolume() {
        return deliveryVolume;
    }

    public int getDeliveryDuration() {
        return deliveryDuration;
    }

    public int getRandomSeed() {
        return randomSeed;
    }

}
