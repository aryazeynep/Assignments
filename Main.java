import java.io.IOException;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;


/**
 * Main class
 */
// FREE CODE HERE
/*
cd C:\Users\HP\Desktop\secondclass\bahar\BBM_LAB_JAVA\Ass\ass2\PA2_starter_code\
javac *.java
java Main demandSchedule.dat ESVMaintenance.dat
 * 
 */

public class Main {

    public static void main(String[] args) throws IOException {

       /** MISSION POWER GRID OPTIMIZATION BELOW **/

        System.out.println("##MISSION POWER GRID OPTIMIZATION##");
        // TODO: Your code goes here
        // You are expected to read the file given as the first command-line argument to read the energy demands arriving per hour. 
        // START(1)

        if (args.length < 1) {
            System.err.println("Usage: java Main <input_file>");
            System.exit(1);
        }

        String inputFile = args[0]; //we get input as first command line

        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        String line = reader.readLine(); //we get the first and only line

        int forOut1 = 0; //for getting the total demand further print 

        ArrayList<Integer> demands = new ArrayList<>();
        String[] parts = line.split(" "); //split the line according to spaces
        for (String part : parts) {
            demands.add(Integer.parseInt(part)); //we make our demand arrey
            forOut1 += Integer.parseInt(part);
        }
        //reader.close();
        // DONE(1)


        // Then, use this data to instantiate a PowerGridOptimization object. 
        // You need to call getOptimalPowerGridSolutionDP() method of your PowerGridOptimization object 
                                                                            //to get the solution, and finally print it to STDOUT.
        
        //START(2)
        //GO PAGE FOR(2)                                                                   
        PowerGridOptimization optimization = new PowerGridOptimization(demands); //Okey
        OptimalPowerGridSolution solution = optimization.getOptimalPowerGridSolutionDP(); //Okey
        //BACK PAGE FOR(2)
        //DONE(2)

        /*
         *  The total number of demanded gigawatts: 22
            Maximum number of satisfied gigawatts: 12
            Hours at which the battery bank should be discharged: 3, 5
            The number of unsatisfied gigawatts: 10
         */

        System.out.println("The total number of demanded gigawatts: " + forOut1);
        System.out.println("Maximum number of satisfied gigawatts: " + solution.getTotalGWs());
        System.out.print("Hours at which the battery bank should be discharged: "); 
        for (int k = 0; k<solution.getChargingSchedule().size(); k++){
            System.out.print(solution.getChargingSchedule().get(k));
            if (k!= solution.getChargingSchedule().size()-1){
                System.out.print(", ");
            }

        }

        System.out.println();
        System.out.println("The number of unsatisfied gigawatts: " + (forOut1-solution.getTotalGWs()));


        System.out.println("##MISSION POWER GRID OPTIMIZATION COMPLETED##");

        //---------------------------------------------------------------------------------------------------------------------------
        /** MISSION ECO-MAINTENANCE BELOW **///---------------------PART2------------------------------------------------------------
        //---------------------------------------------------------------------------------------------------------------------------

        // TODO: Your code goes here
        // You are expected to read the file given as the second command-line argument to read the number of available ESVs, 
                                    //the capacity of each available ESV, and the energy requirements of the maintenance tasks. 
        
        if (args.length < 1) {
            System.err.println("Usage: java Main <input_file>");
            System.exit(1);
        }

        //8 100
        //20 50 40 70 10 30 80 100 10
        String inputFile2 = args[1]; //we get input as second command line

        BufferedReader reader2 = new BufferedReader(new FileReader(inputFile2));
        String line_2_0 = reader2.readLine(); //we get the first line
        String line_2_1 = reader2.readLine(); //we get the second line

        int numAvailESV = 0;
        int capacity = 0;
        ArrayList<Integer> eReqList = new ArrayList<>();
        String[] parts_0 = line_2_0.split(" "); //split the line according to spaces
        String[] parts_1 = line_2_1.split(" ");

        numAvailESV = Integer.parseInt(parts_0[0]);
        capacity = Integer.parseInt(parts_0[1]);
        for (String part : parts_1) {
            eReqList.add(Integer.parseInt(part)); //we make our E req arrey
        }
        reader.close();
        reader2.close();

        // Then, use this data to instantiate an OptimalESVDeploymentGP object.
        // You need to call getMinNumESVsToDeploy(int maxNumberOfAvailableESVs, int maxESVCapacity) method
        // of your OptimalESVDeploymentGP object to get the solution, and finally print it to STDOUT.

        OptimalESVDeploymentGP ecoMaintenance = new OptimalESVDeploymentGP(eReqList);
        int minNumESVsToDeploy = ecoMaintenance.getMinNumESVsToDeploy(numAvailESV, capacity);

        //write the terminal output :)
        /*
         * The minimum number of ESVs to deploy: 5
            ESV 1 tasks: [100]
            ESV 2 tasks: [80, 20]
            ESV 3 tasks: [70, 30]
            ESV 4 tasks: [50, 40, 10]
            ESV 5 tasks: [10]
         */

        System.out.println("##MISSION ECO-MAINTENANCE##");
        if (minNumESVsToDeploy != -1){
           System.out.println("The minimum number of ESVs to deploy: " + minNumESVsToDeploy);
        ArrayList<ArrayList<Integer>> tasksAssignedToESVs = ecoMaintenance.getMaintenanceTasksAssignedToESVs();
        for (int i = 0; i < minNumESVsToDeploy; i++) {
            System.out.println("ESV " + (i + 1) + " tasks: " + tasksAssignedToESVs.get(i));
        } 
        }
        else {
            System.out.println("Warning: Mission Eco-Maintenance Failed.");
        }
        
        System.out.println("##MISSION ECO-MAINTENANCE COMPLETED##");
    }
}
