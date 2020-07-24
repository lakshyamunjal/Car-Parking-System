package finalProject;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class ExitForm {

    private JFrame outer;
    private JInternalFrame inner;
    private JPanel buttonPanel;
    private JButton captureButton, backButton;
    private WebcamPanel panel;
    private Webcam webcam;

    public ExitForm() {
        outer = new JFrame("Store Image");
        inner = new JInternalFrame("Image");

        buttonPanel = new JPanel();

        outer.setLayout(new BorderLayout());
        outer.add(inner, BorderLayout.NORTH);
        outer.add(buttonPanel, BorderLayout.SOUTH);
        outer.setResizable(false);

        buttonPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        captureButton = new JButton("Capture");
        buttonPanel.add(captureButton, gbc);

        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.gridx = 1;
        gbc.gridy = 0;
        backButton = new JButton("Go Back");
        buttonPanel.add(backButton, gbc);

        captureButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // get image
                BufferedImage image = webcam.getImage();
                // save image to PNG file
                try {
                    ImageIO.write(image, "PNG", new File("D:\\image.png"));
                    //    performOCR();       // changes image captured to text
                    outer.setVisible(false);
                    outer.dispose();

                    if (webcam != null) {
                        webcam.close();
                    }
                } catch (Exception ex) {

                }
            }
        });
        
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                outer.setVisible(false);
                outer.dispatchEvent(new WindowEvent(outer, WindowEvent.WINDOW_CLOSING));
                new MainFrame();
            }
        });

        outer.add(buttonPanel);

        outer.setVisible(true);
        inner.setVisible(true);
        buttonPanel.setVisible(true);

        addWebcamImage();

        outer.setSize(800, 600);
        outer.setLocation(300, 80);

        outer.setBackground(Color.white);
        outer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        outer.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {

                new MainFrame();
            }

        });
    }

    private void performOCR() {
        Tesseract tesseract = new Tesseract();
        String dataPath = "D:\\Parking System\\JAR Files\\Tess4J\\tessdata";
        try {
            tesseract.setDatapath(dataPath);

            // the path of your tess data folder 
            // inside the extracted file
            // text is plate number
            String text = tesseract.doOCR(new File("D:\\image.jpg"));

            // slotNumber is the location of car in parking lot.
            int slotNumber = getSlotFromDatabase(text);

            // empty that slot in parking lot
            emptyParkingSlot(slotNumber);

            // open MainFrame again
            new MainFrame();

        } catch (TesseractException e) {
            e.printStackTrace();
        }

    }

    private void addWebcamImage() {
        webcam = Webcam.getWebcams().get(0);
        webcam.setViewSize(WebcamResolution.VGA.getSize());

        panel = new WebcamPanel(webcam);
        panel.setFPSDisplayed(true);
        panel.setDisplayDebugInfo(true);
        panel.setImageSizeDisplayed(true);
        panel.setMirrored(true);

        inner.add(panel);
        inner.setResizable(true);
        inner.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        inner.pack();
        inner.setVisible(true);
        inner.setSize(400, 400);
    }

    private int getSlotFromDatabase(String plateNumber) {
        int slot = -1;

        try {
            Connection conn = DatabaseConnection.newInstance();

            // change database which is being used.
            PreparedStatement stmt = conn.prepareStatement("USE parking_system");
            stmt.execute();

            stmt = conn.prepareCall("{CALL get_slot(?)}");
            stmt.setString(1, plateNumber);

            ResultSet results = stmt.executeQuery();

            while (results.next()) {
                slot = results.getInt(1);
            }

            String query = "{CALL delete_record(?)}";

            CallableStatement cStmt = conn.prepareCall(query);
            cStmt.setString(1, plateNumber);

            cStmt.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return slot;
    }

    private void emptyParkingSlot(int slotNumber) {

        ObjectInputStream in = null;
        ObjectOutputStream out = null;
        Slot[] slotArray = new Slot[60];

        try {
            in = new ObjectInputStream(new FileInputStream("D:\\Parking System\\SlotFile.txt"));

            for (int i = 0; i < 60; i++) {
                slotArray[i] = (Slot) in.readObject();

                if (slotArray[i].getSlotNumber() == slotNumber) {
                    slotArray[i].setCarToken(0);
                }
            }
            in.close();

            out = new ObjectOutputStream(new FileOutputStream("D:\\Parking System\\SlotFile.txt"));

            for (Slot s : slotArray) {
                out.writeObject(s);
            }

            out.writeObject(null);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new ExitForm();
    }
}
