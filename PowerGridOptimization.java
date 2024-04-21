import java.util.ArrayList;

/**
 * This class accomplishes Mission POWER GRID OPTIMIZATION
 */
public class PowerGridOptimization {
    private ArrayList<Integer> amountOfEnergyDemandsArrivingPerHour;

    public PowerGridOptimization(ArrayList<Integer> amountOfEnergyDemandsArrivingPerHour){
        this.amountOfEnergyDemandsArrivingPerHour = amountOfEnergyDemandsArrivingPerHour;
    }

    public ArrayList<Integer> getAmountOfEnergyDemandsArrivingPerHour() {
        return amountOfEnergyDemandsArrivingPerHour;
    }
    /**
     *     Function to implement the given dynamic programming algorithm
     *     SOL(0) <- 0
     *     HOURS(0) <- [ ]
     *     For{j <- 1...N}
     *         SOL(j) <- max_{0<=i<j} [ (SOL(i) + min[ E(j), P(j − i) ] ]
     *         HOURS(j) <- [HOURS(i), j]
     *     EndFor
     *
     * @return OptimalPowerGridSolution
     */
    public OptimalPowerGridSolution getOptimalPowerGridSolutionDP(){
        // TODO: YOUR CODE HERE
        int n = amountOfEnergyDemandsArrivingPerHour.size(); //total how many hour item demand
        int[] SOL = new int[n + 1]; //BECAUSE 0 IS NOT REAL
        ArrayList<ArrayList<Integer>> HOURS = new ArrayList<>(n + 1); //BECAUSE 0 IS NOT REAL

        int[] Ecalc = new int[n];
        for (int i = 0; i<n; i++){
            Ecalc[i] = (i+1)*(i+1);
        }

        //SOL(0) <- 0 :DONE
        SOL[0] = 0; 
        //HOURS(0) <- [ ] :DONE
        HOURS.add(new ArrayList<>());

        //For{j <- 1...N} :DONE
        for (int j = 1; j <= n; j++) {
            int max = Integer.MIN_VALUE;
            int bestI = -1;
            //SOL(j) <- max_{0<=i<j} [ (SOL(i) + min[ D(j), E(j − i) ] ] //min part: that hours max demand or produce etc: DONE
            for (int i = 0; i < j; i++) {
                int potentialMax = SOL[i] + Math.min(amountOfEnergyDemandsArrivingPerHour.get(j - 1), Ecalc[j - 1 - i]);
                if (potentialMax > max) {    //Math.min(amountOfEnergyDemandsArrivingPerHour.get(j - 1), amountOfEnergyDemandsArrivingPerHour.get(j - 1 - i));
                    max = potentialMax;
                    bestI = i;
                }
            }
            SOL[j] = max;
            ArrayList<Integer> schedule = new ArrayList<>(HOURS.get(bestI));
            schedule.add(j);
            //HOURS(j) <- [HOURS(i), j] :DONE
            HOURS.add(schedule);
        }

        //@return OptimalPowerGridSolution :DONE
        return new OptimalPowerGridSolution(SOL[n], HOURS.get(n));
    }
}
