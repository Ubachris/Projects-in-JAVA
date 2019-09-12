package src;

import java.awt.*;
import javax.swing.JComponent;

public class RectangleComponent extends JComponent
{
    public void paintComponent(Graphics g)
    {
       Graphics2D g2= (Graphics2D) g;

       Rectangle small = new Rectangle (20, 20, 50,70);
       g2.setColor(Color.green);
       g2.fill(small);
    }
}
