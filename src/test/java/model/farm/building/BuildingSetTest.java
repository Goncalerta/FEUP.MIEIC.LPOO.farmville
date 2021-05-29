package model.farm.building;

import model.Position;
import model.farm.building.crop_field.CropField;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BuildingSetTest {
    private BuildingSet buildingSet;

    @BeforeEach
    public void setUp() {
        buildingSet = new BuildingSet();

        buildingSet.setHousePosition(new Position(5, 5));
        buildingSet.setMarketPosition(new Position(11, 5));
        buildingSet.setWarehousePosition(new Position(17, 5));

        buildingSet.addCropField(new CropField(new Position(0, 0)));
        buildingSet.addCropField(new CropField(new Position(1 ,4)));
    }

    @Test
    public void isTraversable() {
        Assertions.assertTrue(buildingSet.isTraversable(new Position(0, 0)));
        Assertions.assertTrue(buildingSet.isTraversable(new Position(20, 12)));
        Assertions.assertTrue(buildingSet.isTraversable(new Position(1, 4)));
        Assertions.assertTrue(buildingSet.isTraversable(new Position(5, 5)));
        Assertions.assertFalse(buildingSet.isTraversable(new Position(6, 5)));
        Assertions.assertFalse(buildingSet.isTraversable(new Position(1, 1)));
        Assertions.assertFalse(buildingSet.isTraversable(new Position(3, 6)));
        Assertions.assertFalse(buildingSet.isTraversable(new Position(9, 10)));
    }
}