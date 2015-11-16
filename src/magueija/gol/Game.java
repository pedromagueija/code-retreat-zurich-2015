package magueija.gol;

import java.util.ArrayList;

/**
 * Represents the game of life.
 *
 * @author Pedro Magueija (pedromagueija@gmail.com)
 */
public class Game {

    /**
     * Generation number.
     */
    private static int generation = 0;

    /**
     * The world.
     */
    ArrayList<Cell> world = new ArrayList<>();

    /**
     * A seed to be used to populate the world.
     */
    private ArrayList<Cell> seed = new ArrayList<>();

    /**
     * Top (y) coordinate of the world.
     */
    private int top;

    /**
     * Bottom (y) coordinate of the world.
     */
    private int bottom;

    /**
     * Left (x) coordinate of the world.
     */
    private int left;

    /**
     * Right (x) coordinate of the world.
     */
    private int right;

    /**
     * Evolves the world to the next generation.
     */
    public void evolve() {
        this.populateWorld(seed);

        generation++;

        evolve(0, new ArrayList<Cell>());

        calculateNextGenerationSeed();
    }

    /**
     * Calculates the seed of the next generation based on the previous generation live cells.
     */
    private void calculateNextGenerationSeed() {
        this.seed = new ArrayList<>();
        for (Cell cell : world) {
            cell.addToSeed(this);
        }
    }

    /**
     * Evolves recursively each cell in the world.
     *
     * @param currentIndex Index of the current cell.
     * @param liveCells List with live cells (updated when "descending"), used when ascending.
     */
    private void evolve(int currentIndex, ArrayList<Cell> liveCells) {
        Cell current = world.get(currentIndex);
        current.reportAlive(liveCells);

        if (currentIndex < world.size() - 1)
            evolve(++currentIndex, liveCells);

        current.evolve(liveCells);
    }

    /**
     * Populates the world with the given seed.
     *
     * @param seed The seed for the world.
     */
    private void populateWorld(ArrayList<Cell> seed) {
        resetWorld();

        for (Cell cell : world) {
            cell.seed(seed);
        }
    }

    /**
     * Resets the world, according to its size, by settings all cells to dead.
     */
    private void resetWorld() {
        world = new ArrayList<>();

        for (int r = top; r <= bottom; r++) {
            for (int c = left; c <= right; c++) {
                world.add(new Cell(c, r, State.Dead));
            }
        }
    }

    /**
     * Adds a new live cell to the seed of the world.
     *
     * @param x Coordinate of the live cell.
     * @param y Coordinate of the live cell.
     */
    public void add(int x, int y) {
        calculateSizeOfWorld(x, y);
        seed.add(new Cell(x, y, State.Alive));
    }

    /**
     * Calculates the size of the world, that contemplates the live cells,
     * plus a "border" of dead cells.
     *
     * @param x Coordinate of the live cell.
     * @param y Coordinate of the live cell.
     */
    private void calculateSizeOfWorld(int x, int y) {
        if (seed.size() == 0) {
            top = y - 1;
            bottom = y + 1;
            left = x - 1;
            right = x + 1;
        }

        if (y <= top)
            top = y - 1;
        if (y >= bottom)
            bottom = y + 1;
        if (x <= left)
            left = x - 1;
        if (x >= right)
            right = x + 1;
    }

    /**
     * Prints the world on the console.
     */
    public void show() {
        System.out.println("\nGeneration: " + generation + "\n");

        for (Cell c : world)
            c.show();
    }
}
