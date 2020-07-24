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
public class HoursPassedComparator extends CarComparator implements Comparator<CarComparator> {
    @Override
    public int compare(CarComparator car1, CarComparator car2){
        int hoursCar1 = Integer.valueOf(car1.hoursPassed);
        int hoursCar2 = Integer.valueOf(car2.hoursPassed);
        
        if(hoursCar1 == hoursCar2)
            return (new SlotNumberComparator()).compare(car1, car2);
        
        return hoursCar1 - hoursCar2;
    }    
}
