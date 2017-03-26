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
}

class OceanCell extends Cell {

}

class ForbiddenIslandWorld extends World {
	// All the cells of the game, including the ocean
	IList<Cell> board;
	// the current height of the ocean
	int waterHeight;

	public WorldScene makeScene() {
		return null;
	}
}

class Island {
	public static void main(String[] args) {

	}
}