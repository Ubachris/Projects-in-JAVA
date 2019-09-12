// Name: Uche Uba
// USC NetID: 9239603212
// CS 455 PA1
// Spring 2018



public class CoinTossSimulatorTester {

    private static int initial_run= 0;
    static CoinTossSimulator Sample = new CoinTossSimulator();

    public static void main (String [] args){

        run_sim(5);
        run_sim(10);
        run_sim(15);
        run_sim(20);
        run_sim(25);
        run_sim(30);
        run_sim(35);
        run_sim(100);
        run_sim(200);
        run_sim(250);
        run_sim(300);
        run_sim(400);
        run_sim(500);
        run_sim(550);
        run_sim(10000);
        run_sim(10000);
        run_sim(1000000);
        run_sim(10000000);
        run_sim(200000000);
        run_sim(299999999);
        run_sim(999999999);
        run_sim(1000000000);
        reset();

    }

    /**
     * Method runs the simulation on a given number of trials
     * @param n represents the number of trials
     */
    private static void run_sim(int n){

        int current_run= n;
        int total_run= initial_run+ current_run;
        Sample.run(n);
        System.out.println("After run (" + n + "):");
        System.out.println("Number of Trials [exp:" + (total_run) + "]: " + Sample.getNumTrials());
        System.out.println("Two-head tosses: " + Sample.getTwoHeads());
        System.out.println("Two-tail tosses: " + Sample.getTwoTails());
        System.out.println("One-head one-tail tosses: " + Sample.getHeadTails());
        System.out.println("Tosses add up correctly? " + Sample.isValidSum() + '\n');
        initial_run+=current_run;
    }

    /**
     * resets the the CoinTossSimulator object created
     */
    private static void reset (){
        Sample.reset();
        System.out.println("After reset: ");
        System.out.println("Number of Trials [exp:"+ 0 +"]: "+ Sample.getNumTrials());
        System.out.println("Two-head tosses: "+ Sample.getTwoHeads());
        System.out.println("Two-tail tosses: "+ Sample.getTwoTails());
        System.out.println("One-head one-tail tosses: "+ Sample.getHeadTails());
        System.out.println("Tosses add up correctly? "+ Sample.isValidSum()+'\n');
    }

}
