package grawzycie;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class Gui {
    JFrame mainWindow;
    JPanel up_panel;
    JPanel center_panel; 
    JPanel sim_panel;
    JButton start;
    JButton stop;
    JButton reset;
    Border colorline;
    
     Gui(){
        mainWindow = new JFrame();
        up_panel = new JPanel();
        center_panel = new JPanel();
        colorline = BorderFactory.createLineBorder(Color.lightGray);

        sim_panel = new JPanel();
        center_panel.setLayout(null);
        
        start = new JButton();
        start.setText("Start");
        start.setPreferredSize(new Dimension(80, 25));
        
        stop = new JButton();
        stop.setText("Stop");
        stop.setPreferredSize(new Dimension(80, 25));
        
        reset = new JButton();
        reset.setText("Reset");
        reset.setPreferredSize(new Dimension(80,25));
        
        sim_panel.add(start);
        sim_panel.add(stop);
        sim_panel.add(reset);
        
        mainWindow.setSize(new Dimension(615,700));
        mainWindow.setVisible(true);
        
        mainWindow.setLayout(new BorderLayout());
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        mainWindow.add(up_panel, BorderLayout.PAGE_END);
        mainWindow.add(center_panel, BorderLayout.CENTER);
        mainWindow.setResizable(true);
        up_panel.add(sim_panel);

    }
}
