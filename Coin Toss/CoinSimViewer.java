// Name:Uche Uba
// USC NetID:9239603212
// CS 455 PA1
// Spring 2018

import javax.swing.JFrame;
import java.util.Scanner;

public class CoinSimViewer {

    public static void main(String[] args){

        //Creates the JFrame for viewing the component
        JFrame frame= new JFrame();

        frame.setSize(800, 500);
        frame.setTitle("CoinSim");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of trials: ");
        int trials= sc.nextInt();

        // Checks to make sure the number of trials input remain positive
        while (trials<=0){
            System.out.println("ERROR number of trials must be greater than 0");
            System.out.print("Enter number of trials: ");
            trials= sc.nextInt();
        }

        CoinSimComponent sim = new CoinSimComponent(trials);
        frame.add(sim);

        frame.setVisible(true);
    }
}
