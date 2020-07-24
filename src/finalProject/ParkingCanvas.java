package finalProject;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

// creates a GUI showing empty slots and occupied slots in the parking lot
class MyCanvas extends java.awt.Canvas {

    private RectangleSlot[][] rectangleSlotArray = new RectangleSlot[6][10];
    private Slot[] parkingSlotInformation = new Slot[60];
    private static int slotNumber = -1;

    public static int getSlotNumber() {
        return slotNumber;
    }

    public MyCanvas() {
        setSize(1280, 1020);
        setBackground(Color.BLACK);
    }

    // set the coordinates of each location in parking lot
    // the values are going to be same everytime
    // we could SERIALIZE this into a file
    public void setCoordinates() {
        int x = 300, y = 100, slotCount = 1;

        for (int i = 0; i < 6; i++) {
            x = 300;
            for (int j = 0; j < 10; j++) {
                rectangleSlotArray[i][j] = new RectangleSlot(x, y, slotCount++);
                x += 75;
            }

            if (i % 2 == 0) {
                y += 120;
            } else {
                y += 70;
            }

        }
    }

    public int findEmptySlot() {
        ObjectInputStream ois;
        int count = 0;

        // populate array by reading objects from file
        try {
            ois = new ObjectInputStream(new FileInputStream("D:\\Parking System\\SlotFile.txt"));
            Slot s = (Slot) ois.readObject();
            while (s != null) {
                parkingSlotInformation[count++] = s;
                s = (Slot) ois.readObject();
            }

            ois.close();

            // find empty slot
            for (int i = 0; i < 60; i++) {
                if (parkingSlotInformation[i].getCarToken() == 0) {
                    // nearest empty slot found
                    parkingSlotInformation[i].setCarToken(1);
                    slotNumber = parkingSlotInformation[i].getSlotNumber();
//                   System.out.println(parkingSlotInformation[i]);

                    // write updates to existing file
                    writeUpdatesToFile(slotNumber);
                    return slotNumber;

                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return -1;
    }

    @Override
    public void paint(Graphics g) {

        initialParkingLot(g);

        if (slotNumber == -1) {
            // no empty slot found
            // or so error occured
            JOptionPane.showMessageDialog(null, "No empty slot found !");
            return;
        }

        // read parkingSlotInformation array
        // and where ever token is set to 1, paint that square
        // all previously occupied squares are red
        // current nearest slot is painted red.
        // empty slots are painted yellow.
        for (Slot s : parkingSlotInformation) {
            if (s.getCarToken() == 1) {
                int slot = s.getSlotNumber();

                for (int i = 0; i < 6; i++) {
                    for (int j = 0; j < 10; j++) {
                        if (rectangleSlotArray[i][j].getRectangleSlotNumber() == slot) {
                            if (slot == slotNumber) {
                                // current nearest slot to be painted red
                                g.setColor(Color.RED);
                                drawPath(s.getSlotNumber(), s.getPosition(), g, Color.RED);
                                g.fillRect(rectangleSlotArray[i][j].getX(), rectangleSlotArray[i][j].getY(), 50, 50);

                            } else {
                                drawPath(s.getSlotNumber(), s.getPosition(), g, Color.BLACK);
                                try {
                                    String path = "D:\\";
                                    BufferedImage image = ImageIO.read(new File(path, "car.jpg"));

                                    g.drawImage(image, rectangleSlotArray[i][j].getX(), rectangleSlotArray[i][j].getY(), 50, 50, null);
                                } catch (IOException ex) {

                                }
                            }

                            break;
                        }
                    }
                }
            }

        }
    }

    public void drawPath(int slotNumber, int position, Graphics g, Color c) {
        g.setColor(c);
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(20));

        g.drawLine(50, 380, 250, 380);

        //Slot 1-10
        if (slotNumber >= 1 && slotNumber <= 10) {
            g.drawLine(250, 380, 250, 180);
            int x = 250, y = 180, x1;

            while (position >= 0) {
                x1 = x + 75;
                g.drawLine(x, y, x1, y);
                x = x1;
                position--;
            }

            g.drawLine(x, y, x, y - 40);
        }

        //Slot 11-20
        if (slotNumber >= 11 && slotNumber <= 20) {
            g.drawLine(250, 380, 250, 180);
            int x = 250, y = 180, x1;

            while (position >= 0) {
                x1 = x + 75;
                g.drawLine(x, y, x1, y);
                x = x1;
                position--;
            }

            g.drawLine(x, y, x, y + 40);
        }

        //Slot 21-30
        if (slotNumber >= 21 && slotNumber <= 30) {
            int x = 250, y = 380, x1;

            while (position >= 0) {
                x1 = x + 75;
                g.drawLine(x, y, x1, y);
                x = x1;
                position--;
            }

            g.drawLine(x, y, x, y - 40);
        }

        //Slot 31-40
        if (slotNumber >= 31 && slotNumber <= 40) {
            int x = 250, y = 380, x1;

            while (position >= 0) {
                x1 = x + 75;
                g.drawLine(x, y, x1, y);
                x = x1;
                position--;
            }

            g.drawLine(x, y, x, y + 40);
        }

        //Slot 41-50
        if (slotNumber >= 41 && slotNumber <= 50) {
            g.drawLine(250, 380, 250, 560);
            int x = 250, y = 560, x1;

            while (position >= 0) {
                x1 = x + 75;
                g.drawLine(x, y, x1, y);
                x = x1;
                position--;
            }

            g.drawLine(x, y, x, y - 40);
        }

        //Slot 51-60
        if (slotNumber >= 51 && slotNumber <= 60) {
            g.drawLine(250, 380, 250, 560);
            int x = 250, y = 560, x1;

            while (position >= 0) {
                x1 = x + 75;
                g.drawLine(x, y, x1, y);
                x = x1;
                position--;
            }

            g.drawLine(x, y, x, y + 40);
        }
    }

    private void writeUpdatesToFile(int slotNumber) {
        if (slotNumber == -1) {
            // no empty slot found
            JOptionPane.showMessageDialog(null, "No empty slot found");
            return;
        }
        try {
            File file = new File("D:\\Parking System\\SlotFile.txt");
            // false states that file will be overwritten
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file, false));

            for (int i = 0; i < 60; i++) {
                oos.writeObject(parkingSlotInformation[i]);
            }
            oos.writeObject(null);
            oos.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initialParkingLot(Graphics g) {

        int x, y;

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 10; j++) {
                x = rectangleSlotArray[i][j].getX();
                y = rectangleSlotArray[i][j].getY();

                g.setColor(Color.WHITE);
                g.fillRect(x, y, 50, 50);
            }
        }
    }
}

public class ParkingCanvas {

    MyCanvas canvas = null;
    JFrame parkingLot;
    
    public ParkingCanvas(String plateNumber, String currentTime) {
        parkingLot = new JFrame("Pakring Lot");
        //super("Parking lot");

        canvas = new MyCanvas();
        parkingLot.setSize(parkingLot.getMaximumSize());
        parkingLot.setResizable(false);
        canvas.setCoordinates();
        canvas.findEmptySlot();
        displayParkingInformation(MyCanvas.getSlotNumber(), plateNumber, currentTime);

    }

    private void displayParkingInformation(int slotNumber, String plateNumber, String currentTime) {
        JFrame parkingSlip = new JFrame("Parking Slip");
        JTextArea area = new JTextArea("Car number: " + plateNumber + "\nSlot number: " + slotNumber + "\nEntry Time: " + currentTime);

        area.setEditable(false);
        parkingSlip.setLayout(new BorderLayout());
        parkingSlip.add(area, BorderLayout.CENTER);
        parkingSlip.setSize(300, 300);
        parkingSlip.setVisible(true);
        parkingSlip.setLocation(500, 300);
        
        parkingSlip.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                parkingLot.add(canvas);
                parkingLot.setVisible(true);
                parkingLot.setLocation(0,0);
                parkingLot.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
        });

        
    }

//    private Point getSystemResolution() {
//        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
//        dim.width /= 2;
//        dim.height /= 2;
//        return new Point(dim.width, dim.height);
//    }

    public static void main(String[] ar) {
        new ParkingCanvas("RIYA 420", "8:00");
    }

}
