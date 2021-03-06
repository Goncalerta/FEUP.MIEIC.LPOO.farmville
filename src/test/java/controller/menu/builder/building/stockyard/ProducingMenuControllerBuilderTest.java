package controller.menu.builder.building.stockyard;

import controller.GameController;
import controller.GameControllerState;
import controller.menu.MenuController;
import controller.menu.PopupMenuController;
import controller.menu.builder.MenuControllerBuilder;
import gui.Color;
import model.InGameTime;
import model.Position;
import model.farm.Farm;
import model.farm.building.stockyard.Stockyard;
import model.farm.building.stockyard.state.ReadyToCollect;
import model.farm.building.stockyard.state.StockyardState;
import model.farm.data.Livestock;
import model.farm.data.item.AnimalProduct;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

public class ProducingMenuControllerBuilderTest {
    private MenuControllerBuilder builder;
    private Farm farm;
    private GameController gameController;
    private Stockyard stockyard;

    @BeforeEach
    public void setUp() {
        this.gameController = Mockito.mock(GameController.class);
        Mockito.when(this.gameController.getGameControllerState()).thenReturn(Mockito.mock(GameControllerState.class));
        this.farm = Mockito.mock(Farm.class);
        Livestock livestock = Mockito.mock(Livestock.class);
        Mockito.when(livestock.getStockyardWidth()).thenReturn(4);
        Mockito.when(livestock.getStockyardHeight()).thenReturn(4);
        Mockito.when(livestock.getProducedItem()).thenReturn(Mockito.mock(AnimalProduct.class));
        Mockito.when(livestock.getProducedItem().getName()).thenReturn("p1");
        this.stockyard = new Stockyard(new Position(0, 0), livestock);
        this.stockyard.setState(new StockyardState() {
            @Override
            public InGameTime getRemainingTime() {
                return new InGameTime(7);
            }

            @Override
            public void setRemainingTime(InGameTime time) { }

            @Override
            public int getCollectAmount() {
                return 3;
            }

            @Override
            public void changeCollectAmount(double collectAmount) { }

            @Override
            public Color getAnimalColor() {
                return null;
            }
        });

        this.builder = new ProducingMenuControllerBuilder(this.gameController, this.farm, this.stockyard);
    }

    @Test
    public void buildMenu() {
        MenuController menuController = this.builder.buildMenu();

        Assertions.assertTrue(menuController.getMenu().getButtons().stream().anyMatch(button ->
                button.getTitle().equals("STOP PRODUCING")));

        Assertions.assertTrue(menuController.getMenu().getLabels().stream().anyMatch(label ->
                label.getString().equals("PRODUCT: p1")));
        Assertions.assertTrue(menuController.getMenu().getLabels().stream().anyMatch(label ->
                label.getString().equals("REMAINING TIME: 00:07")));
        Assertions.assertTrue(menuController.getMenu().getLabels().stream().anyMatch(label ->
                label.getString().equals("QUANTITY: 3")));
    }

    @Test
    public void menuChangesWhenReady() {
        MenuController menuController = this.builder.buildMenu();

        menuController.reactTimePassed(5);
        Mockito.verify(this.gameController, Mockito.never()).setGameControllerState(Mockito.any());
        this.stockyard.setState(Mockito.mock(ReadyToCollect.class));
        menuController.reactTimePassed(5);

        ArgumentCaptor< GameControllerState > captor = ArgumentCaptor.forClass(GameControllerState.class);
        Mockito.verify(this.gameController, Mockito.times(1)).setGameControllerState(captor.capture());
        Assertions.assertTrue(captor.getValue() instanceof PopupMenuController);
    }
}
