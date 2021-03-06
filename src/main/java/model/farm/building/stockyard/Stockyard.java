package model.farm.building.stockyard;

import gui.Color;
import model.InGameTime;
import model.Position;
import model.farm.Currency;
import model.farm.building.Buildable;
import model.farm.data.Livestock;
import model.farm.building.stockyard.state.NotProducing;
import model.farm.building.stockyard.state.StockyardState;
import model.region.PositionRegion;
import model.region.RectangleRegion;
import model.region.Region;

import java.util.Objects;

public class Stockyard extends Buildable {
    public static final Position ANIMALS_REGION_OFFSET = new Position(2, 1);
    private final Livestock livestockType;
    private final StockyardAnimals animals;
    private StockyardState state;

    public Stockyard(Position topLeft, Livestock livestockType) {
        super(topLeft);
        this.livestockType = livestockType;
        RectangleRegion animalsRegion = new RectangleRegion(
                topLeft.getTranslated(ANIMALS_REGION_OFFSET), this.getWidth() - 3, this.getHeight() - 2
        );
        this.animals = new StockyardAnimals(animalsRegion, this.livestockType.getMaxNumAnimals());
        this.state = new NotProducing();
    }

    @Override
    public Currency getBuildPrice() {
        return this.livestockType.getBuildPrice();
    }

    @Override
    public int getWidth() {
        return this.livestockType.getStockyardWidth();
    }

    @Override
    public int getHeight() {
        return this.livestockType.getStockyardHeight();
    }

    @Override
    public Region getUntraversableRegion() {
        return new RectangleRegion(
                this.getTopLeftPosition().getRight(),
                this.getWidth() - 1,
                this.getHeight());
    }

    @Override
    public Region getInteractiveRegion() {
        return new PositionRegion(this.getTopLeftPosition().getTranslated(new Position(0, 3)));
    }

    @Override
    public String getName() {
        return this.livestockType.getAnimalName() + " S.Y.";
    }

    @Override
    public void setTopLeftPosition(Position topLeft) {
        super.setTopLeftPosition(topLeft);
        this.animals.setAnimalsRegionPosition(topLeft.getTranslated(ANIMALS_REGION_OFFSET));
    }

    public StockyardAnimals getAnimals() {
        return this.animals;
    }

    public Livestock getLivestockType() {
        return this.livestockType;
    }

    public int getRequiredFood() {
        return this.livestockType.getRequiredFood() * this.animals.getSize();
    }

    public int getBaseProducedAmount() {
        return this.livestockType.getProducedItem().getBaseProducedAmount() * this.animals.getSize();
    }

    public void setState(StockyardState state) {
        this.state = state;
    }

    public StockyardState getState() {
        return this.state;
    }

    public InGameTime getRemainingTime() {
        return this.state.getRemainingTime();
    }

    public void setRemainingTime(InGameTime time) {
        this.state.setRemainingTime(time);
    }

    public int getCollectAmount() {
        return this.state.getCollectAmount();
    }

    public void changeCollectAmount(double quantity) {
        this.state.changeCollectAmount(quantity);
    }

    public char getAnimalChar() {
        return this.getLivestockType().getAnimalChar();
    }

    public Color getAnimalColor() {
        return this.state.getAnimalColor();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        Stockyard stockyard = (Stockyard) o;
        return Objects.equals(this.getTopLeftPosition(), stockyard.getTopLeftPosition());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getTopLeftPosition());
    }
}
