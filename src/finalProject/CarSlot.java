package finalProject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;

class CarSlot {

    private static Slot[] slotArray = new Slot[60];

    public static Slot[] getSlotArray() {
        int slotCount = 1, count = 0;

        for (int i = 1; i <= 6; i++) {
            int dist = 20;
            if (i == 3 || i == 4) {
                dist = 0;
            }
            for (int j = 0; j < 10; j++) {
                // Slot(slotNumber, distance, carToken, position)
                // posiiton will be from 0 to 9 because there are 10 car in a row
                slotArray[count++] = new Slot(slotCount++, dist + (j * 10), 0, j);
            }

        }

        Arrays.sort(slotArray);
        return slotArray;
    }

    public static void generateInitialFile() throws Exception {

        File file = new File("lib\\SlotFile.txt");
        slotArray = getSlotArray();

        // if file with the given name does not exist, create a new file
        if (file.exists() == false) {
            // write array of objects to file

            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));

            for (int i = 0; i < 60; i++) {
                oos.writeObject(slotArray[i]);
            }

            oos.writeObject(null);          // null is inserted at the end.
            oos.close();
        }

    }

}
