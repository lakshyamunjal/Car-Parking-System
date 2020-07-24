package finalProject;

import java.awt.event.WindowEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainFrame extends javax.swing.JFrame {

    public MainFrame() {
        initComponents();
        this.setVisible(true);
        this.setLocation(300, 100);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        carEntryButton = new javax.swing.JButton();
        exitCarButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        displayParkingLotButton = new javax.swing.JButton();
        quitButton = new javax.swing.JButton();
        displayCarStatus = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Parking System");

        carEntryButton.setText("Car Entry");
        carEntryButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                carEntryButtonActionPerformed(evt);
            }
        });

        exitCarButton.setText("Exit Car");
        exitCarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitCarButtonActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Parking System");

        displayParkingLotButton.setText("Display Parking lot");
        displayParkingLotButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                displayParkingLotButtonActionPerformed(evt);
            }
        });

        quitButton.setText("Quit");
        quitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quitButtonActionPerformed(evt);
            }
        });

        displayCarStatus.setText("Display Car Status");
        displayCarStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                displayCarStatusActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(376, Short.MAX_VALUE)
                .addComponent(quitButton)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(carEntryButton, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(displayParkingLotButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(exitCarButton, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(displayCarStatus))
                .addGap(55, 55, 55))
            .addGroup(layout.createSequentialGroup()
                .addGap(158, 158, 158)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(jLabel1)
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(carEntryButton)
                    .addComponent(exitCarButton))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(displayParkingLotButton)
                    .addComponent(displayCarStatus))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 103, Short.MAX_VALUE)
                .addComponent(quitButton)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void carEntryButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_carEntryButtonActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        new WebcamAccess();
    }//GEN-LAST:event_carEntryButtonActionPerformed

    private void exitCarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitCarButtonActionPerformed
        this.setVisible(false);
        this.dispose();
        new ExitForm();
    }//GEN-LAST:event_exitCarButtonActionPerformed

    private void quitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quitButtonActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }//GEN-LAST:event_quitButtonActionPerformed

    private void displayParkingLotButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_displayParkingLotButtonActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        this.dispose();
        
        try {
            // call to create SlotFile if it does not exist
            CarSlot.generateInitialFile();
        } catch (Exception ex) {
            
        }
        
        
        new DisplayParkingLot();
    }//GEN-LAST:event_displayParkingLotButtonActionPerformed

    private void displayCarStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_displayCarStatusActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        this.dispose();
        new DisplayCarStatus();
    }//GEN-LAST:event_displayCarStatusActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton carEntryButton;
    private javax.swing.JButton displayCarStatus;
    private javax.swing.JButton displayParkingLotButton;
    private javax.swing.JButton exitCarButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton quitButton;
    // End of variables declaration//GEN-END:variables
}
