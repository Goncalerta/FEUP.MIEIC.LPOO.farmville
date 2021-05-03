package model.farm.building.crop_field.crop;

import model.ChronologicalTime;

import java.util.List;

// TODO which is better?
//      1) public interface Crop with many classes that just return constant values
//      2) public class Crop with many public static final Crop instances?
public interface Crop {
    ChronologicalTime getGrowTime();
    List<GrowthStage> getGrowthStages();
}