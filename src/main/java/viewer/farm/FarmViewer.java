package viewer.farm;

import gui.GUI;
import gui.drawer.FencesDrawer;
import gui.drawer.HouseDrawer;
import model.*;
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