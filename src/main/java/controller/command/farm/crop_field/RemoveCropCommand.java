package controller.command.farm.crop_field;

import controller.command.Command;
import model.farm.building.crop_field.CropField;
import model.farm.building.crop_field.state.NotPlanted;

public class RemoveCropCommand implements Command {
    private final CropField cropField;

    public RemoveCropCommand(CropField cropField) {
        this.cropField = cropField;
    }

    @Override
    public void execute() {
        this.cropField.setState(new NotPlanted());
    }
}
