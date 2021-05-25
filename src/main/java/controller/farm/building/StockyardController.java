package controller.farm.building;

import controller.GameController;
import controller.GameControllerState;
import controller.command.*;
import controller.farm.FarmDemolishController;
import controller.farm.FarmWithFarmerController;
import controller.command.Command;
import controller.command.NoOperationCommand;
import controller.farm.AnimalController;
import model.farm.Farm;
import model.farm.animal.Animal;
import model.farm.building.Stockyard;

public class StockyardController extends BuildingController<Stockyard> {
    private final GameController controller;
    private final AnimalController animalController;
    private Farm farm;

    public StockyardController(GameController controller, Farm farm) {
        this.controller = controller;
        this.animalController = new AnimalController();
    }

    public void resetLastMovement() {
        animalController.reset();
    }

    @Override
    public Command getInteractionCommand(Stockyard stockyard) {
        // TODO
        System.out.println("Stockyard interaction not implemented yet");
        return new NoOperationCommand();
    }

    @Override
    public Command getDemolishCommand(Stockyard stockyard) {
        GameControllerState gameControllerState = this.controller.getGameControllerState();
        if (gameControllerState instanceof FarmDemolishController) {
            gameControllerState = new FarmWithFarmerController((FarmDemolishController) gameControllerState);
        }

        return new CompoundCommand()
                .addCommand(() -> this.farm.getBuildings().removeStockyard(stockyard))
                .addCommand(new SetControllerStateCommand(this.controller, gameControllerState));
    }

    public void reactTimePassed(Stockyard<? extends Animal> stockyard) {
        // TODO Make animals of stockyard move and decrease its Hunger
        animalController.reactTimePassed(stockyard);
    }
}
