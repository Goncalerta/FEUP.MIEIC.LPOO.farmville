package model.farm.building.crop_field;

import gui.Color;
import model.ChronologicalTime;
import model.Position;
import model.farm.building.Building;
import model.farm.building.crop_field.crop.GrowthStage;
import model.farm.building.crop_field.state.CropFieldState;
import model.farm.building.crop_field.state.NotPlanted;

import java.util.List;

public class CropField extends Building {
    public static final int CROP_FIELD_SIZE = 4;
    private CropFieldState state;

    public CropField(Position topLeft) {
        super(topLeft);
        this.state = new NotPlanted();
    }

    public void setState(CropFieldState state) {
        this.state = state;
    }

    public CropFieldState getState() {
        return this.state;
    }

    public GrowthStage getCropGrowthStage() {
        List<GrowthStage> growthStages = this.state.getCrop().getGrowthStages();
        ChronologicalTime remainingTime = this.state.getRemainingTime();
        for (GrowthStage stage: growthStages) {
            if (remainingTime.getMinutes() <= stage.getStageStartTime().getMinutes()) {
                return stage;
            }
        }

        return growthStages.get(growthStages.size() - 1);
    }

    @Override
    public boolean isTraversable(Position position) {
        Position invalidPosition = this.getTopLeftPosition().getRight().getDown();
        if (position.equals(invalidPosition)) return false;
        invalidPosition = invalidPosition.getRight();
        if (position.equals(invalidPosition)) return false;
        invalidPosition = invalidPosition.getDown();
        if (position.equals(invalidPosition)) return false;
        invalidPosition = invalidPosition.getLeft();
        if (position.equals(invalidPosition)) return false;
        return true;
    }

    @Override
    public boolean isInInteractiveZone(Position position) {
        int x = position.getX();
        int y = position.getY();
        int buttonLeft = this.getTopLeftPosition().getX();
        int buttonRight = buttonLeft + CROP_FIELD_SIZE - 1;
        int buttonTop = this.getTopLeftPosition().getY();
        int buttonBottom = buttonTop + CROP_FIELD_SIZE - 1;
        return (x >= buttonLeft && x <= buttonRight && y >= buttonTop && y <= buttonBottom);
    }

    public ChronologicalTime getRemainingTime() {
        return this.getState().getRemainingTime();
    }
}
