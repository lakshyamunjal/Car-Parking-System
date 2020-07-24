package finalProject;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class WebcamAccess {

    private JFrame outer;
    private JInternalFrame inner;
    private JPanel buttonPanel;
    private JButton captureButton;
    private WebcamPanel panel;
    private Webcam webcam;

    public WebcamAccess() {
        outer = new JFrame("Store Image");
        inner = new JInternalFrame("Image");

        buttonPanel = new JPanel();

        outer.setLayout(new BorderLayout());
        outer.add(inner, BorderLayout.NORTH);
        outer.add(buttonPanel, BorderLayout.SOUTH);
        outer.setResizable(false);

        buttonPanel.setLayout(new BorderLayout());

        captureButton = new JButton("Capture");
        buttonPanel.add(captureButton, BorderLayout.CENTER);
        captureButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // get image
                BufferedImage image = webcam.getImage();
                // save image to PNG file
                try {
                    ImageIO.write(image, "PNG", new File("D:\\image.png"));
                    //performOCR();       // changes image captured to text
                    outer.setVisible(false);
                        //outer.dispatchEvent(new WindowEvent(outer, WindowEvent.WINDOW_CLOSING));

                    if (webcam != null) {
                        webcam.close();
                    }
                    
                    performOCR();
                } catch (Exception ex) {

                }
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
    }

    private void performOCR() {
        Tesseract tesseract = new Tesseract();
        String dataPath = "lib\\Tess4J\\tessdata";
        try {
            tesseract.setDatapath(dataPath);

                // the path of your tess data folder 
            // inside the extracted file
            // text is plate number
            String plateNumber = tesseract.doOCR(new File("D:\\image.png"));

            // display the parking lot and current empty location for parking the car
            new ParkingCanvas(plateNumber, getCurrentTime());

            // slotNumber is the location of empty slot where vehicle will be parked.
            int slotNumber = MyCanvas.getSlotNumber();

            // store information to database
            storeToDatabase(slotNumber, plateNumber);

            // open MainFrame again
            new MainFrame();

        } catch (TesseractException e) {
            e.printStackTrace();
        }
    }

    private String getCurrentTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("hh:mm");
        Date date = new Date();
        return formatter.format(date);
    }

    private void addWebcamImage() {
        // replace 1 in get() method to access image from external webcam
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

    private void storeToDatabase(int slotNumber, String number) {
        
        Connection conn = DatabaseConnection.newInstance();
        CallableStatement cstmt;
        PreparedStatement stmt;

        // Store date and time
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
        Date systemDate = new Date();

        String currentDateTime = formatter.format(systemDate);
        
        

        // slot number is taken from serialized object
        // slot number is the position of empty slot in the parking lot.
        // store to database: slotNumber, plateNumber, entryTime
        // use callable statment
        try {
            // change database
            stmt = conn.prepareStatement("USE parking_system");
            stmt.execute();

            cstmt = conn.prepareCall("{CALL insert_record(?, ?, ?)}");
            cstmt.setInt(1, slotNumber);
            cstmt.setString(2, number);
            cstmt.setString(3, currentDateTime);


            cstmt.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
