package grawzycie;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;


public class GraWZycie implements Runnable
{
    Gui gui = new Gui();
    JButton[][] cells;
    int size = 15;
    int rows = 40;
    int cols = 40;
    
    boolean[][] mainArray;
    boolean[][] tempArray;
    boolean run = false;
    

   void copy(boolean array[][], boolean arrayB[][])
   {
        for(int i = 1; i < rows-1; i++)
            System.arraycopy(arrayB[i], 1, array[i], 1, cols-1 - 1);
    }
    
    void Init()
    {
        mainArray = new boolean[rows][cols];
        tempArray =  new boolean[rows][cols];
        
        for(int i = 0; i < rows; i++)
        {
            for(int j = 0; j < cols; j++)
            {
                mainArray[i][j] = false;
                tempArray[i][j] = false;
            }
        }
        
        cells = new JButton[rows][cols];
        
        for(int i = 0; i < rows; i++)
        {
            for(int j = 0; j < cols; j++)
            {
                cells[i][j] = new JButton();
                cells[i][j].setSize(new Dimension(size, size));
                gui.center_panel.add(cells[i][j]);
                cells[i][j].setBounds(j*size, i*size, size, size);
                
            
                if(i==0 || i==rows-1 || j==0 || j==cols-1)
                {
                    cells[i][j].setBorderPainted(false);
                    cells[i][j].setEnabled(false);
                }
                else
                {
                  cells[i][j].setBackground(Color.white);
                }
            }
        }
        
    }
     void cellsPaint()
     {
        for(int i = 1; i < rows-1; i++)
        {
            for(int j = 1; j < cols-1; j++)
            {
                if(mainArray[i][j] == true)
                    cells[i][j].setBackground(Color.black);
                else
                    cells[i][j].setBackground(Color.white);
            }
        }
    }
     void periodic()
     {
        for(int j = 1; j < cols-1; j++)
            mainArray[0][j] = mainArray[rows-2][j];
        
        for(int j = 1; j < cols-1; j++)
            mainArray[rows-1][j] = mainArray[1][j];
        
        for(int i = 1; i < rows-1; i++)
            mainArray[i][0] = mainArray[i][cols-2];
        
        for(int i = 1; i < rows-1; i++)
            mainArray[i][cols-1] = mainArray[i][1];
        
        mainArray[0][0] = mainArray[rows-2][cols-2];
        mainArray[rows-1][cols-1] = mainArray[1][1];
        mainArray[rows-1][0] = mainArray[1][cols-2];
        mainArray[0][cols-1] = mainArray[rows-2][1];   
        
    }
     
     
    void game() throws InterruptedException
    {
        while(run)
        {
            periodic();
            for(int i = 1; i < rows-1; i++)
                for(int j = 1; j < cols-1; j++)
                {
                    int count=0;
                    if(mainArray[i+1][j] == true)
                    {
                        count++;
                    }
                    if(mainArray[i+1][j+1] == true)
                    {
                        count++;
                    }
                    if(mainArray[i][j+1] == true)
                    {
                        count++;
                    }
                    if(mainArray[i-1][j+1] == true)
                    {
                        count++;
                    }
                    if(mainArray[i-1][j-1] == true)
                    {
                        count++;
                    }
                    if(mainArray[i-1][j] == true)
                    {
                        count++;
                    }
                    if(mainArray[i][j-1] == true)
                    {
                        count++;
                    }
                    if(mainArray[i+1][j-1] == true)
                    {
                        count++;
                    }
                    if(mainArray[i][j] == true)
                    {
                        if(count == 2 || count == 3)
                        {
                            tempArray[i][j] = true;
                        }
                        else
                        {
                            tempArray[i][j] = false;
                        }
                    }
                    else
                    {
                        if(count == 3)
                        {
                            tempArray[i][j] = true;
                        }
                    }
                }
            copy(mainArray, tempArray);
            cellsPaint();
            Thread.sleep(50);
        }
    }
    
   void GridInit()
   {
     for(int i = 0; i < rows; i++)
       for(int j = 0; j < cols; j++)
       {
            int k = i;
            int l = j;   
            cells[i][j].addActionListener(new ActionListener() 
            {

            @Override
            public void actionPerformed(ActionEvent ae) 
            {
                if(cells[k][l].getBackground() == Color.black)
                {
                    cells[k][l].setBackground(Color.white);
                    mainArray[k][l] = false;
                }
                else
                {
                    cells[k][l].setBackground(Color.black);
                    mainArray[k][l] = true;
                }                
                
            }
        });
    }
            
        
   }
   
   void startStopReset()
   {
      gui.start.addActionListener(new ActionListener() 
      {

            @Override
            public void actionPerformed(ActionEvent ae) 
            {
                run = true;
                gameThread();
            }
        });
         gui.stop.addActionListener(new ActionListener() 
         {

            @Override
            public void actionPerformed(ActionEvent ae) 
            {
                if(run)
                {
                    run = false;
                }
                else
                {
                    run = true;
                }
            }
        });
         gui.reset.addActionListener(new ActionListener() 
       {

            @Override
            public void actionPerformed(ActionEvent ae) 
            {
                run = false;
                for(int i = 0; i < rows; i++)
                {
                        for(int j = 0; j < cols; j++)
                        {
                            if(cells[i][j].getBackground() == Color.black)
                            {
                                cells[i][j].setBackground(Color.white);
                                mainArray[i][j] = false;
                                tempArray[i][j] = false;
                            }
                            
                        }
                }
            }
       });
   }
   
   void gameThread()
   {
        Thread thread = new Thread(this);
        thread.start();
   }
   
    @Override
    public void run() 
    {
        try 
        {
            game();
        } catch (InterruptedException ex) 
        {
            Logger.getLogger(GraWZycie.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
  
    public static void main(String[] args) 
    {
        
        GraWZycie temp = new GraWZycie();
        temp.Init();
        temp.GridInit();
        temp.startStopReset();
   
   }
}
