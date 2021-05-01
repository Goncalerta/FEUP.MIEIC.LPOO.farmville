package model.farm.crop_field;

import model.Position;
import model.farm.InteractiveElement;
import model.farm.crop_field.state.CropFieldState;
import model.farm.crop_field.state.NotPlanted;

public class CropField extends InteractiveElement {
    public static final int CROP_FIELD_SIZE = 4;
    private CropFieldState state;

    public CropField(Position topLeft) {
        super(topLeft);
        this.state = new NotPlanted(this);
    }

    public void setState(CropFieldState state) {
        this.state = state;
    }

    public CropFieldState getState() {
        return this.state;
    }

    public Position getPosition() {
        return topLeft;
    }

    public boolean isTraversable(Position position) {
        Position invalidPosition = this.topLeft.getRight().getDown();
        if (position.equals(invalidPosition)) return false;
        invalidPosition = invalidPosition.getRight();
        if (position.equals(invalidPosition)) return false;
        invalidPosition = invalidPosition.getDown();
        if (position.equals(invalidPosition)) return false;
        invalidPosition = invalidPosition.getLeft();
        if (position.equals(invalidPosition)) return false;
        return true;
    }

    public boolean contains(Position position) {
        int x = position.getX();
        int y = position.getY();
        int buttonLeft = this.topLeft.getX();
        int buttonRight = buttonLeft + CROP_FIELD_SIZE - 1;
        int buttonTop = this.topLeft.getY();
        int buttonBottom = buttonTop + CROP_FIELD_SIZE - 1;
        return (x >= buttonLeft && x <= buttonRight && y >= buttonTop && y <= buttonBottom);
    }
}