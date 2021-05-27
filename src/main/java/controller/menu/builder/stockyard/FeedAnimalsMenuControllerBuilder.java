package controller.menu.builder.stockyard;

import controller.GameController;
import controller.command.Command;
import controller.command.CompoundCommand;
import controller.command.FeedAnimalsCommand;
import controller.command.OpenPopupMenuCommand;
import controller.menu.ButtonController;
import controller.menu.builder.PopupMenuControllerBuilder;
import controller.menu.builder.info.AlertMenuControllerBuilder;
import model.Position;
import model.farm.Inventory;
import model.farm.building.Stockyard;
import model.farm.item.Crop;
import model.menu.Button;
import model.menu.label.Label;

import java.util.List;

public class FeedAnimalsMenuControllerBuilder extends PopupMenuControllerBuilder {
    private Inventory inventory;
    private Stockyard stockyard;
    private Crop crop;

    public FeedAnimalsMenuControllerBuilder(GameController gameController, Inventory inventory, Stockyard stockyard, Crop crop) {
        super(gameController);
        this.inventory = inventory;
        this.stockyard = stockyard;
        this.crop = crop;
    }

    @Override
    protected List<ButtonController> getButtons() {
        List<ButtonController> buttons = super.getButtons();
        int x = 1;
        int y = 5;

        String title = "FEED " + stockyard.getLivestockType().getFoodCrop().getName() + " x" +
                stockyard.getFeedQuantity();

        Button feedAnimalsButton = new Button(new Position(x, y), title);

        Command feedAnimalsCommand;

        if (this.inventory.getAmount(this.stockyard.getLivestockType().getFoodCrop()) >=
            this.stockyard.getFeedQuantity()) {

            feedAnimalsCommand = new CompoundCommand()
                    .addCommand(new FeedAnimalsCommand(this.stockyard, inventory, crop))
                    .addCommand(super.getClosePopupMenuCommand());

        } else {
            feedAnimalsCommand = new OpenPopupMenuCommand(this.controller,
                    new AlertMenuControllerBuilder(this.controller, "NOT ENOUGH " +
                            this.stockyard.getLivestockType().getFoodCrop().getName()));
        }

        buttons.add(new ButtonController(feedAnimalsButton, feedAnimalsCommand));

        return buttons;
    }

    @Override
    protected List<Label> getLabels() {
        List<Label> labels = super.getLabels();

        labels.add(new Label(
                new Position(1, 3),
                () -> stockyard.getLivestockType().getFoodCrop().getName() + ": " +
                        inventory.getAmount(stockyard.getLivestockType().getFoodCrop())
        ));

        return labels;
    }

    @Override
    protected int getHeight() {
        return 10;
    }

    @Override
    protected int getWidth() {
        return 20;
    }

    @Override
    protected String getTitle() {
        return "STOCKYARD";
    }
}
