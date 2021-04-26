package gui;

import model.Position;

import java.io.IOException;

public interface GUI {
    GUI.ACTION getNextAction() throws IOException;

    void drawFarmer(Position position);

    void clear();

    void refresh() throws IOException;

    void close() throws IOException;

    void drawHorizontalFence(int x, int y);

    void drawVerticalFence(int x, int y);

    void drawCornerFence(int x, int y);

    enum ACTION {
        UP, RIGHT, DOWN, LEFT, QUIT, NONE;
    }

}
