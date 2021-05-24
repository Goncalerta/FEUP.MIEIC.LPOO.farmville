package model.farm.building;

import model.Position;
import model.region.RectangleRegion;
import model.region.Region;

public abstract class Building {
    private Position topLeft;

    public Building(Position topLeft) {
        this.topLeft = topLeft;
    }

    public Position getTopLeftPosition() {
        return this.topLeft;
    }

    public abstract int getWidth();
    public abstract int getHeight();

    public void setTopLeftPosition(Position topLeft) {
        this.topLeft = topLeft;
    }
    
    public abstract Region getUntraversableRegion();
    public abstract Region getInteractiveRegion();

    public RectangleRegion getOccupiedRegion() {
        return new RectangleRegion(this.topLeft, this.getWidth(), this.getHeight());
    }
}
