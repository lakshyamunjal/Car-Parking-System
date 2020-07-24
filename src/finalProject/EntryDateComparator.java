/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package finalProject;

import java.util.Comparator;

/**
 *
 * @author HP
 */
public class EntryDateComparator extends CarComparator implements Comparator<CarComparator> {
    @Override
    public int compare(CarComparator car1, CarComparator car2){
        
        String[] entryDateCar1 = car1.entryDate.split("-");
        String[] entryDateCar2 = car2.entryDate.split("-");
        
        int date1, date2, mon1, mon2, year1, year2;
        
        year1 = Integer.valueOf(entryDateCar1[2]);
        year2 = Integer.valueOf(entryDateCar2[2]);
        
        if(year1 != year2)
            return year1 - year2;
        
        mon1 = Integer.valueOf(entryDateCar1[1]);
        mon2 = Integer.valueOf(entryDateCar2[1]);
        
        if(mon1 != mon2)
            return mon1 - mon2;
        
        date1 = Integer.valueOf(entryDateCar1[0]);
        date2 = Integer.valueOf(entryDateCar2[0]);
        
        if(date1 != date2)
            return date1 - date2;
        
        
        // if date is same, sort by slot number
        return (new SlotNumberComparator()).compare(car1, car2);
    }    
}
    
