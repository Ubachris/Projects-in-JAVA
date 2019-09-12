package src;

import javax.swing.*;
import java.util.*;
import java.awt.*;

public class RectangleTester {
    public static void main (String [] args)
    {
        JFrame frame= new JFrame ();
        frame.setSize(150,200);
        frame.setTitle("A rectangle display");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        RectangleComponent Component = new RectangleComponent();
        frame.add(Component);

        frame.setVisible(true);
    }
}
