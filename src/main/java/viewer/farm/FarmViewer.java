package viewer.farm;

import gui.GUI;
import gui.drawer.entity.FencesDrawer;
import model.*;
import model.farm.crop_field.CropField;
import model.farm.Farm;
import model.farm.Farmer;
import model.farm.House;
import viewer.GameViewerState;

public class FarmViewer implements GameViewerState {
    private void drawFarmer(Farmer farmer, FarmerViewer farmerViewer, GUI gui) {
        farmerViewer.draw(farmer, gui);
    }

    private void drawFences(Farm farm, GUI gui) {
        FencesDrawer fencesDrawer = new FencesDrawer(gui);
        fencesDrawer.draw(new Position(0, 0), farm.getWidth(), farm.getHeight());
    }

    private void drawHouse(House house, HouseViewer houseViewer, GUI gui) {
        houseViewer.draw(house, gui);
    }

    @Override
    public void draw(GameModel model, GUI gui) {
        drawCropField(model.getFarm().getCropField(), new CropFieldViewer(), gui);
        drawHouse(model.getFarm().getHouse(), new HouseViewer(), gui);
        drawFences(model.getFarm(), gui);

        drawFarmer(model.getFarm().getFarmer(), new FarmerViewer(), gui);
    }

    private void drawCropField(CropField cropField, CropFieldViewer cropFieldViewer, GUI gui) {
        cropFieldViewer.draw(cropField, gui);
    }
}
