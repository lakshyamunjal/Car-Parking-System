package finalProject;

// Information about every parking slot
public class RectangleSlot 
{
    // x and y is the coordinate position of the canvas
    private int x,y,rectangleSlotNumber;

    public RectangleSlot(int x, int y, int rectangleSlotNumber) {
        this.x = x;
        this.y = y;
        this.rectangleSlotNumber = rectangleSlotNumber;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
    public int getRectangleSlotNumber() {
        return rectangleSlotNumber;
    }
}

