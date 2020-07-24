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
public class EntryTimeComparator extends CarComparator implements Comparator<CarComparator> {
    @Override
    public int compare(CarComparator car1, CarComparator car2){
        String[] timeCar1 = car1.entryTime.split(":");
        String[] timeCar2 = car2.entryTime.split(":");
        
        int hrs1, hrs2, mins1, mins2, secs1, secs2;
        hrs1 = Integer.valueOf(timeCar1[0]);
        hrs2 = Integer.valueOf(timeCar2[0]);
        
        if(hrs1 != hrs2)
            return hrs1 - hrs2;
        
        mins1 = Integer.valueOf(timeCar1[1]);
        mins2 = Integer.valueOf(timeCar2[1]);
        
        if(mins1 != mins2)
            return mins1 - mins2;
        
        secs1 = Integer.valueOf(timeCar1[2]);
        secs2 = Integer.valueOf(timeCar2[2]);
        
        if(secs1 != secs2)
            return secs1 - secs2;
        
        return (new SlotNumberComparator()).compare(car1, car2);
    }    
}
