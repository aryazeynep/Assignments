import java.util.ArrayList;

/**
 * A class that represents the optimal solution for the Power Grid optimization scenario (Dynamic Programming)
 */

 //old version

public class OptimalPowerGridSolution {
    private int maxNumberOfSatisfiedDemands;
    private ArrayList<Integer> hoursToDischargeBatteriesForMaxEfficiency;

    public OptimalPowerGridSolution(int maxNumberOfSatisfiedDemands, ArrayList<Integer> hoursToDischargeBatteriesForMaxEfficiency) {
        this.maxNumberOfSatisfiedDemands = maxNumberOfSatisfiedDemands;
        this.hoursToDischargeBatteriesForMaxEfficiency = hoursToDischargeBatteriesForMaxEfficiency;
    }

    public OptimalPowerGridSolution() {
        this.maxNumberOfSatisfiedDemands = 0; //we add
        this.hoursToDischargeBatteriesForMaxEfficiency = new ArrayList<>(); //we add

    }

    public int getmaxNumberOfSatisfiedDemands() {
        return maxNumberOfSatisfiedDemands;
    }

    public ArrayList<Integer> getHoursToDischargeBatteriesForMaxEfficiency() {
        return hoursToDischargeBatteriesForMaxEfficiency;
    }

    public void setMaxNumberOfSatisfiedDemands(int maxNumberOfSatisfiedDemands) {
        this.maxNumberOfSatisfiedDemands = maxNumberOfSatisfiedDemands;
    }

    public void setHoursToDischargeBatteriesForMaxEfficiency(ArrayList<Integer> hoursToDischargeBatteriesForMaxEfficiency) {
        this.hoursToDischargeBatteriesForMaxEfficiency = hoursToDischargeBatteriesForMaxEfficiency;
    }

    // We add
    public int getTotalGWs() {
        return getmaxNumberOfSatisfiedDemands();
    }

    public int setTotalGWs(int newValue) {
        maxNumberOfSatisfiedDemands = newValue;
        return getmaxNumberOfSatisfiedDemands();
    }



    public ArrayList<Integer> getChargingSchedule() {
        return getHoursToDischargeBatteriesForMaxEfficiency();
    }

}
