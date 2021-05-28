package model.farm.building.edifice;

import model.Position;
import model.farm.building.Building;
import model.region.EdificeUntraversableRegion;
import model.region.PositionRegion;
import model.region.Region;

public class Warehouse extends Edifice {
    public Warehouse(Position topLeft) {
        super(topLeft);
    }

    @Override
    public String getName() {
        return "WAREHOUSE";
    }
}