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
public class PlateNumberComparator extends CarComparator implements Comparator<CarComparator>{
    @Override
    public int compare(CarComparator car1, CarComparator car2){
        return car1.plateNumber.compareTo(car2.plateNumber);
    }
}
