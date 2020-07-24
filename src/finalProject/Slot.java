package finalProject;

public class Slot implements java.io.Serializable, Comparable<Slot>
{
    private int slotNumber;     // slot number of location where to park car
    private int distance;       // distance from entry point
    private int carToken;       
    private int position;       // position of slot in a particular row
    
    public Slot(int slotName,int distance,int carToken,int position)
    {
        this.slotNumber = slotName;
        this.distance = distance;
        this.carToken = carToken;
        this.position = position;
    }

    public void setCarToken(int carToken) {
        this.carToken = carToken;
    }
    
    public int getSlotNumber() {
        return slotNumber;
    }

    public int getDistance() {
        return distance;
    }

    public int getCarToken() {
        return carToken;
    }

    public int getPosition() {
        return position;
    }
    
    @Override
    public int compareTo(Slot slot)
    {
        return this.distance - slot.distance;
    }
    
    public String toString()
    {
        return slotNumber + "," + distance + "," + carToken + "," + position ;
    }
    
}