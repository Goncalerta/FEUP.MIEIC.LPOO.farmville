package viewer.farm;

import gui.Color;
import gui.GUI;
import model.farm.animal.Animal;
import model.farm.building.Stockyard;

public class StockyardViewer {
    private static final Color PATH_COLOR = new Color("#be9b7b");
    private static final Color FENCE_DOOR_COLOR = PATH_COLOR;

    public void draw(Stockyard<? extends Animal> stockyard, GUI gui) {
        FencesViewer fencesViewer = new FencesViewer();
        fencesViewer.draw(stockyard.getTopLeftPosition(), stockyard.getWidth(), stockyard.getHeight(), gui);

        gui.setForegroundColor(FENCE_DOOR_COLOR);
        gui.drawChar(stockyard.getTopLeftPosition().getX(), stockyard.getTopLeftPosition().getY()+3, '|');

        gui.setBackgroundColor(PATH_COLOR);
        gui.drawChar(stockyard.getTopLeftPosition().getX() - 1, stockyard.getTopLeftPosition().getY() + 3, ' ');

        for (Animal animal: stockyard.getAnimals()) {
            AnimalViewer animalViewer = new AnimalViewer();
            animalViewer.draw(animal, gui);
        }
    }
}
