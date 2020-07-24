package finalProject;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import javax.swing.JFrame;

class DisplayParkingCanvas extends java.awt.Canvas
{
    private static ObjectInputStream ois =  null;
    private RectangleSlot[][] rectangleSlotArray = new RectangleSlot[6][10];
 
   public DisplayParkingCanvas()
    {
        setCoordinates();
        setSize(1280,1020);
        setBackground(Color.BLACK);  
    }
   
   // set the coordinates of each location in parking lot
   // the values are going to be same everytime
   // we could SERIALIZE this into a file
   public void setCoordinates()
   {
       int x=300,y=100,slotCount=1;
      
       for(int i=0;i<6;i++)
       {
           x=300;
           for(int j=0;j<10;j++)
           {
               rectangleSlotArray[i][j] = new RectangleSlot(x,y,slotCount++);
               x+=75;
           }
           
           if(i%2==0)
               y+=120;
           else
               y+=70; 
       }
   }
   
   @Override
   public void paint(Graphics g)
   {
       // read parkingSlotInformation array              
       // and where ever token is set to 1, paint that square
       // all previously occupied squares are yellow
       // and empty slots are painted white.

        try
        {
            ois = new ObjectInputStream(new FileInputStream("D:\\Parking System\\SlotFile.txt"));
            
            Slot s = (Slot)ois.readObject();
            
            while(s!=null)
            {                
                if(s.getCarToken() == 0)
                   g.setColor(Color.WHITE);
                else
                    g.setColor(Color.YELLOW);
                
                for(int i=0;i<6;i++)
                {
                    for(int j=0;j<10;j++)
                    {
                        if(rectangleSlotArray[i][j].getRectangleSlotNumber() == s.getSlotNumber())
                        {
                            g.fillRect(rectangleSlotArray[i][j].getX(),rectangleSlotArray[i][j].getY(),50,50);
                        }
                    }
                }  
                s = (Slot)ois.readObject();
            }       
            ois.close(); 
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}

public class DisplayParkingLot extends JFrame
{
    public DisplayParkingLot()
    {
        super("Parked vehicle");
        setSize(this.getMaximumSize());
        setResizable(false);
        setVisible(true);
        add(new DisplayParkingCanvas());
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);        
        this.addWindowListener(new WindowAdapter(){
            public void windowClosed(){
                new MainFrame();
            }
        });
    }
    
    
}