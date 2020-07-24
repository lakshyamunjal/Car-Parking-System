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
public class SlotNumberComparator extends CarComparator implements Comparator<CarComparator> {
    @Override
    public int compare(CarComparator car1, CarComparator car2){
        return Integer.valueOf(car1.slotNumber)- (Integer.valueOf(car2.slotNumber));
    }    
}
