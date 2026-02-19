
public class Main {  
    public static void main(String[] args) {  
        Simulation sim=new Simulation(60*60*24 * Config.getInstance().getSimulationDays());
        sim.run();
        sim.print_result();
    }  
}  
