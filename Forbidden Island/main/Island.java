import java.util.ArrayList;
import tester.*;
import javalib.impworld.*;
import javalib.worldimages.*;
import java.awt.Color;

// Represents a single square of the game area
class Cell {
	// represents absolute height of this cell, in feet
	double height;
	// In logical coordinates, with the origin at the top-left corner of the screen
	int x, y;
	// the four adjacent cells to this one
	Cell left, top, right, bottom;
	// reports whether this cell is flooded or not
	boolean isFlooded;

	// Draw an image representing this Cell
	public WorldImage draw(int size, int waterHeight) {
		// TEMPLATE: Same as for this class
		// The max height Cell is the size of the Board divided by 2
		int maxHeight = size / 2;

		// The rest of this method is for determining the color and nothing else.
		// There is some extra work going on because I am picky about colors.
		int red;
		int green;
		int blue;

		int step = 184 / maxHeight;

		if(this.height >= waterHeight && !this.isFlooded) {
			red = 124 - (maxHeight - (int) this.height) * 2 - waterHeight ;
			green = 255 - (maxHeight - (int) this.height) * 2 - waterHeight ;
			blue = 130 - (maxHeight - (int) this.height) * 2 - waterHeight ;
			// If you want colors matching the assignment, use these:
			/*
			int heightAboveWater = (int)this.height - waterHeight;
			red = 255 - (maxHeight - heightAboveWater) * 2 * step;
			green = 255 - (maxHeight - heightAboveWater) * step;
			blue = 255 - (maxHeight - heightAboveWater) * 2 * step;
			*/
		}
		else if(waterHeight > this.height && !this.isFlooded) {
			red = 55 + (waterHeight - (int)this.height) * step;
			green = 200 - (waterHeight - (int)this.height) * step;
			blue = 0;
		}
		else {
			red = 73;
			green = 87;
			blue = 184 - (waterHeight - (int)this.height) * step;
		}
		if(red < 0) {
			red = 0;
		}
		else if(red > 255) {
			red = 255;
		}
		if(green < 0) {
			green = 0;
		}
		else if(green > 255) {
			green = 255;
		}
		if(blue < 0) {
			blue = 0;
		}
		else if(blue > 255) {
			blue = 255;
		}

		Color color = new Color(red, green, blue);

		return new RectangleImage(10, 10, OutlineMode.SOLID, color);
	}
}

class OceanCell extends Cell {
	// Draw this OceanCell
	public WorldImage draw(int size, int waterHeight) {
		// TEMPLATE: Same as for this class
		return new RectangleImage(10, 10, OutlineMode.SOLID, new Color(73, 87, 184));
	}
}

class ForbiddenIslandWorld extends World {
	static final int ISLAND_SIZE = 64;
	// All the cells of the game, including the ocean
	IList<Cell> board;
	// the current height of the ocean
	int waterHeight;

	public ForbiddenIslandWorld() {
		IList<Cell> list = new MtList<Cell>();
		for(int r = 0; r < ISLAND_SIZE; r++) {
			for(int c = 0; c < ISLAND_SIZE; c++) {
				list = new ConsList<Cell>(new Cell(), list);
			}
		}

		this.board = list;
	}

	// Render an image of this World
	public WorldScene makeScene() {
		// TEMPLATE: Same as for this class
		WorldScene scene = new WorldScene(10 * ISLAND_SIZE, 10 * ISLAND_SIZE);

		// Loop over each Cell in the board and render it in place
		for(Cell cell : this.board) {
			int placeX = 10 * cell.x + 5;
			int placeY = 10 * cell.y + 5;
			scene.placeImageXY(cell.draw(ISLAND_SIZE, this.waterHeight), placeX, placeY);
		}

		return scene;
	}
}

class Island {
	public void testGame() {
		ForbiddenIslandWorld f = new ForbiddenIslandWorld();
		f.bigBang(ForbiddenIslandWorld.ISLAND_SIZE, ForbiddenIslandWorld.ISLAND_SIZE, 0.1);
	}
}
