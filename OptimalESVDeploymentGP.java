import java.util.ArrayList;
import java.util.Collections;

/**
 * This class accomplishes Mission Eco-Maintenance
 */
public class OptimalESVDeploymentGP
{
    private ArrayList<Integer> maintenanceTaskEnergyDemands;

    /*
     * Should include tasks assigned to ESVs.
     * For the sample input:
     * 8 100
     * 20 50 40 70 10 30 80 100 10
     * 
     * The list should look like this:
     * [[100], [80, 20], [70, 30], [50, 40, 10], [10]]
     * 
     * It is expected to be filled after getMinNumESVsToDeploy() is called.
     */
    private ArrayList<ArrayList<Integer>> maintenanceTasksAssignedToESVs = new ArrayList<>();

    ArrayList<ArrayList<Integer>> getMaintenanceTasksAssignedToESVs() {
        return maintenanceTasksAssignedToESVs;
    }

    public OptimalESVDeploymentGP(ArrayList<Integer> maintenanceTaskEnergyDemands) {
        this.maintenanceTaskEnergyDemands = maintenanceTaskEnergyDemands;
    }

    public ArrayList<Integer> getMaintenanceTaskEnergyDemands() {
        return maintenanceTaskEnergyDemands;
    }

    /**
     *
     * @param maxNumberOfAvailableESVs the maximum number of available ESVs to be deployed
     * @param maxESVCapacity the maximum capacity of ESVs
     * @return the minimum number of ESVs required using first fit approach over reversely sorted items.
     * Must return -1 if all tasks can't be satisfied by the available ESVs
     */

     /*
      * procedure getMinNumESVsToDeployGP(maxNumAvailableESVs: int, ESV_CAPACITY: int)
            Sort all maintenance tasks by their energy requirements in decreasing order . So that we can consider the largest demands first.
            Store the remaining energy capacities in all available ESVs
            for i← 1, . . . , numTasks do
                Find the first ESV that can accommodate task i
                Get a new ESV only if the task does not fit in any of the already used ESVs (no code for this, by itself?)
            end for
            return minNumESVsToDeploy or -1 if all tasks could not be accommodated.
        end procedure
      */

    public int getMinNumESVsToDeploy(int maxNumberOfAvailableESVs, int maxESVCapacity)
    {
        // TODO: Your code goes here
        // Sort maintenance tasks in decreasing order of energy requirements :DONE
        Collections.sort(maintenanceTaskEnergyDemands, Collections.reverseOrder());

        // Initialize an array to store remaining energy capacities of ESVs :DONE
        int[] remainingCapacities = new int[maxNumberOfAvailableESVs];
        for (int i = 0; i < maxNumberOfAvailableESVs; i++) {
            remainingCapacities[i] = maxESVCapacity; //firstly we full all esv
        }

        // Initialize variable to store the minimum number of ESVs needed
        int minNumESVsToDeploy = 0;

        for (int i = 0; i < maxNumberOfAvailableESVs; i++) {
            maintenanceTasksAssignedToESVs.add(new ArrayList<>());
        }
    
        // Loop through each maintenance task
        for (int i = 0; i < maintenanceTaskEnergyDemands.size(); i++) {
            boolean controlForMinus = true;
            int taskEnergyDemand = maintenanceTaskEnergyDemands.get(i); //task that we observe now

            // Find the first ESV that can accommodate the task
            for (int j = 0; j < maxNumberOfAvailableESVs; j++) {
                if (remainingCapacities[j] >= taskEnergyDemand) {
                    // Assign the task to this ESV
                    maintenanceTasksAssignedToESVs.get(j).add(taskEnergyDemand);
                    remainingCapacities[j] -= taskEnergyDemand;
                    controlForMinus = false;
                    break;
                }
            }

            if (controlForMinus){
                return -1;
            }
        }

        for (int k = 0; k<maintenanceTasksAssignedToESVs.size(); k++){
            if (!maintenanceTasksAssignedToESVs.get(k).isEmpty()){
                minNumESVsToDeploy++;
            }
        }
        return minNumESVsToDeploy;
    }
}
