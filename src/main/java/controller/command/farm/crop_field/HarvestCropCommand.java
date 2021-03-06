package controller.command.farm.crop_field;

import controller.command.Command;
import model.farm.Inventory;
import model.farm.building.crop_field.CropField;
import model.farm.building.crop_field.state.NotPlanted;
import model.farm.building.crop_field.state.ReadyToHarvest;

public class HarvestCropCommand implements Command {
    private final CropField cropField;
    private final Inventory inventory;

    public HarvestCropCommand(Inventory inventory, CropField cropField) {
        this.inventory = inventory;
        this.cropField = cropField;
    }

    @Override
    public void execute() {
        if (this.cropField.getState() instanceof ReadyToHarvest) {
            ReadyToHarvest cropState = (ReadyToHarvest) this.cropField.getState();
            this.inventory.storeItem(cropState.getCrop(), cropState.getHarvestAmount());
            this.cropField.setState(new NotPlanted());
        }
    }
}
