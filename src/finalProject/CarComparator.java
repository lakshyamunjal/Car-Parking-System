/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package finalProject;

/**
 *
 * @author HP
 */
public class CarComparator {
    protected String slotNumber;
    protected String plateNumber;
    protected String entryDate;
    protected String entryTime;
    protected String hoursPassed;
    
    public CarComparator(){}
    
    public CarComparator(String slotNumber,String plateNumber, String entryDate, String entryTime, String hoursPassed){
        this.slotNumber = slotNumber;
        this.plateNumber = plateNumber;
        this.entryDate = entryDate;
        this.entryTime = entryTime;
        this.hoursPassed = hoursPassed;
    }

    public String getSlotNumber() {
        return slotNumber;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public String getEntryDate() {
        return entryDate;
    }

    public String getEntryTime() {
        return entryTime;
    }

    public String getHoursPassed() {
        return hoursPassed;
    }
    
    
}

