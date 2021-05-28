package controller.farm;

import controller.farm.element.entity.NewBuildingController;
import controller.menu.MenuController;
import controller.menu.builder.MenuControllerBuilder;
import controller.menu.builder.info.AlertMenuControllerBuilder;
import gui.GUI;
import model.Position;
import model.farm.building.Buildable;
import model.farm.building.crop_field.CropField;
import model.farm.building.stockyard.Stockyard;
import viewer.GameViewer;
import viewer.farm.FarmNewBuildingViewer;

public class FarmNewBuildingController extends FarmController {
    private Buildable newBuilding;

    public FarmNewBuildingController(FarmController farmController, Buildable newBuilding) {
        super(farmController);
        this.newBuilding = newBuilding;
    }

    @Override
    public void reactKeyboard(GUI.KEYBOARD_ACTION action) {
        if (action == GUI.KEYBOARD_ACTION.BACK) returnToFarmerController();
        if (action == GUI.KEYBOARD_ACTION.INTERACT) reactInteraction();
        NewBuildingController newBuildingController = new NewBuildingController(this.farm, this.newBuilding);
        newBuildingController.doAction(action);
    }

    @Override
    public void reactMouseMovement(Position position) {}

    @Override
    public void reactMouseClick(Position position) {}

    private void returnToFarmerController() {
        this.controller.setGameControllerState(new FarmWithFarmerController(this));
    }

    private void reactInteraction() {
        if (farm.getBuildings().isOccupied(this.newBuilding.getOccupiedRegion())) {
            MenuControllerBuilder popupControllerBuilder = new AlertMenuControllerBuilder(this.controller,
                    "CHOSEN PLACE IS ALREADY OCCUPIED");

            MenuController popupController = popupControllerBuilder.buildMenuCentered(
                    controller.getWindowWidth(), controller.getWindowHeight());

            this.controller.setGameControllerState(popupController);
        } else {
            if (this.newBuilding instanceof CropField) {
                this.farm.getBuildings().addCropField((CropField) this.newBuilding);
            } else if (this.newBuilding instanceof Stockyard) {
                this.farm.getBuildings().addStockyard((Stockyard) this.newBuilding);
            } else {
                // This should never happen
                throw new RuntimeException(
                        "LOGIC ERROR: Unhandled new building type: " + this.newBuilding.getClass().toString());
            }

            this.farm.getWallet().spend(this.newBuilding.getBuildPrice());

            returnToFarmerController();
        }
    }

    @Override
    public GameViewer getViewer() {
        return new FarmNewBuildingViewer(this.farm, this.newBuilding);
    }
}
