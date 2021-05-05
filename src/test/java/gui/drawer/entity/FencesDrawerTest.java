package gui.drawer.entity;

import gui.Color;
import gui.GUI;
import model.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class FencesDrawerTest {

    private GUI gui;
    private Color backgroundColors[][] = new Color[10][10];
    private Color foregroundColors[][] = new Color[10][10];
    private char characters[][] = new char[10][10];
    private Color currentBackgroundColor = new Color("#000000");
    private Color currentForegroundColor = new Color("#FFFFFF");

    @BeforeEach
    void setUp() {
        this.gui = Mockito.mock(GUI.class);

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                this.backgroundColors[i][j] = this.currentBackgroundColor;
                this.foregroundColors[i][j] = this.currentForegroundColor;
                this.characters[i][j] = ' ';
            }
        }

        Mockito.doAnswer(invocation -> {
            this.currentBackgroundColor = invocation.getArgument(0);
            return null;
        }).when(gui).setBackgroundColor(Mockito.any());

        Mockito.doAnswer(invocation -> {
            this.currentForegroundColor = invocation.getArgument(0);
            return null;
        }).when(gui).setForegroundColor(Mockito.any());

        Mockito.doAnswer(invocation -> {
            int x = invocation.getArgument(0);
            int y = invocation.getArgument(1);
            char c = invocation.getArgument(2);
            this.backgroundColors[y][x] = currentBackgroundColor;
            this.foregroundColors[y][x] = currentForegroundColor;
            this.characters[y][x] = c;
            return null;
        }).when(gui).drawChar(Mockito.anyInt(), Mockito.anyInt(), Mockito.anyChar());

        Mockito.doAnswer(invocation -> {
            String s = invocation.getArgument(2);
            int x = invocation.getArgument(0);
            int y = invocation.getArgument(1);
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                this.backgroundColors[y][x] = currentBackgroundColor;
                this.foregroundColors[y][x] = currentForegroundColor;
                this.characters[y][x] = c;
                x += 1;
            }
            return null;
        }).when(gui).drawString(Mockito.anyInt(), Mockito.anyInt(), Mockito.anyString());

        Mockito.doAnswer(invocation -> {
            int x = invocation.getArgument(0);
            int y = invocation.getArgument(1);
            return this.backgroundColors[y][x];
        }).when(gui).getBackgroundColor(Mockito.anyInt(), Mockito.anyInt());

        Mockito.doAnswer(invocation -> {
            int x = invocation.getArgument(0);
            int y = invocation.getArgument(1);
            return this.foregroundColors[y][x];
        }).when(gui).getForegroundColor(Mockito.anyInt(), Mockito.anyInt());
    }

    @Test
    void drawFences() {
        FencesDrawer drawer = new FencesDrawer(gui);
        drawer.draw(new Position(2, 2), 5, 5);

        Color BLACK = new Color("#000000");
        Color WHITE = new Color("#FFFFFF");
        Color FENCES_BACKGROUND = new Color("#7EC850");
        Color FENCES_COLOR = new Color("#846f46");
        char HORIZONTAL_LINE = '-';
        char VERTICAL_LINE = '|';
        char CORNER_LINE = '+';

        Color expectedBg[][] = {
                {BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK},
                {BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK},
                {BLACK, BLACK, FENCES_BACKGROUND, FENCES_BACKGROUND, FENCES_BACKGROUND, FENCES_BACKGROUND, FENCES_BACKGROUND, BLACK, BLACK, BLACK},
                {BLACK, BLACK, FENCES_BACKGROUND, BLACK, BLACK, BLACK, FENCES_BACKGROUND, BLACK, BLACK, BLACK},
                {BLACK, BLACK, FENCES_BACKGROUND, BLACK, BLACK, BLACK, FENCES_BACKGROUND, BLACK, BLACK, BLACK},
                {BLACK, BLACK, FENCES_BACKGROUND, BLACK, BLACK, BLACK, FENCES_BACKGROUND, BLACK, BLACK, BLACK},
                {BLACK, BLACK, FENCES_BACKGROUND, FENCES_BACKGROUND, FENCES_BACKGROUND, FENCES_BACKGROUND, FENCES_BACKGROUND, BLACK, BLACK, BLACK},
                {BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK},
                {BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK},
                {BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK},
        };

        Color expectedFg[][] = {
                {WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE},
                {WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE},
                {WHITE, WHITE, FENCES_COLOR, FENCES_COLOR, FENCES_COLOR, FENCES_COLOR, FENCES_COLOR, WHITE, WHITE, WHITE},
                {WHITE, WHITE, FENCES_COLOR, WHITE, WHITE, WHITE, FENCES_COLOR, WHITE, WHITE, WHITE},
                {WHITE, WHITE, FENCES_COLOR, WHITE, WHITE, WHITE, FENCES_COLOR, WHITE, WHITE, WHITE},
                {WHITE, WHITE, FENCES_COLOR, WHITE, WHITE, WHITE, FENCES_COLOR, WHITE, WHITE, WHITE},
                {WHITE, WHITE, FENCES_COLOR, FENCES_COLOR, FENCES_COLOR, FENCES_COLOR, FENCES_COLOR, WHITE, WHITE, WHITE},
                {WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE},
                {WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE},
                {WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE},
        };

        char expectedChars[][] = {
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', CORNER_LINE, HORIZONTAL_LINE, HORIZONTAL_LINE, HORIZONTAL_LINE, CORNER_LINE, ' ', ' ', ' '},
                {' ', ' ', VERTICAL_LINE, ' ', ' ', ' ', VERTICAL_LINE, ' ', ' ', ' '},
                {' ', ' ', VERTICAL_LINE, ' ', ' ', ' ', VERTICAL_LINE, ' ', ' ', ' '},
                {' ', ' ', VERTICAL_LINE, ' ', ' ', ' ', VERTICAL_LINE, ' ', ' ', ' '},
                {' ', ' ', CORNER_LINE, HORIZONTAL_LINE, HORIZONTAL_LINE, HORIZONTAL_LINE, CORNER_LINE, ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
        };

        for (int i = 0; i < 10; i++) {
            Assertions.assertArrayEquals(expectedBg[i], this.backgroundColors[i]);
            Assertions.assertArrayEquals(expectedChars[i], this.characters[i]);
            Assertions.assertArrayEquals(expectedFg[i], this.foregroundColors[i]);
        }
    }
}