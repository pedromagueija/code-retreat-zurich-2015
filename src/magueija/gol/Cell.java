package magueija.gol;

import java.util.ArrayList;

/**
 * Represents a cell in the world.
 *
 * @author Pedro Magueija (pedromagueija@gmail.com)
 */
public class Cell {

    /**
     * Current cell state.
     */
    private State state;

    /**
     * Coordinate of cell.
     */
    private int x;

    /**
     * Coordinate of cell.
     */
    private int y;

    /**
     * Number of live neighbours of the cell.
     */
    private int liveNeighbours = 0;

    /**
     * Creates a new cell in the given position and state.
     *
     * @param x Coordinate of cell.
     * @param y Coordinate of cell.
     * @param state State of cell.
     */
    public Cell(int x, int y, State state) {
        this.x = x;
        this.y = y;
        this.state = state;
    }

    /**
     * Add the current cell to the given collection when cell is alive.
     *
     * @param liveCells The collection of live cells.
     */
    public void reportAlive(ArrayList<Cell> liveCells) {
        if (this.state == State.Alive)
            liveCells.add(this);
    }

    /**
     * Shows the current cell on the console.
     */
    public void show() {
        System.out.println(this.toString());
    }

    /**
     * Evolves the current cell based on the number of live neighbours of the current cell that are in the live
     * cell collection (live neighbours).
     *
     * @param liveCells The collection of live cells.
     */
    public void evolve(ArrayList<Cell> liveCells) {
        this.liveNeighbours = 0;

        for (Cell cell : liveCells) {
            incrementLiveNeighbours(cell);
        }

        applyRules();
    }

    /**
     * Applies the rules of life to the current cell.
     */
    private void applyRules() {
        if (this.state == State.Alive && liveNeighbours < 2)
            this.state = State.Dead;

        if (this.state == State.Alive && (liveNeighbours == 2 || liveNeighbours == 3))
            this.state = State.Alive;

        if (this.state == State.Alive && liveNeighbours > 3)
            this.state = State.Dead;

        if (this.state == State.Dead && liveNeighbours == 3)
            this.state = State.Alive;
    }

    /**
     * Increments the number of live neighbours of current cell when the given cell is a neighbour.
     *
     * @param neighbour A live cell.
     */
    private void incrementLiveNeighbours(Cell neighbour) {
        final boolean touchesX = neighbour.x >= this.x - 1 && neighbour.x <= this.x + 1;
        final boolean touchesY = neighbour.y >= this.y - 1 && neighbour.y <= this.y + 1;
        final boolean isNeighbourCell = touchesX && touchesY;
        final boolean isNotMe = neighbour.x != this.x || neighbour.y != this.y;

        if (isNeighbourCell && isNotMe)
            liveNeighbours++;
    }

    @Override
    public String toString() {
        return "Cell(" + this.x + ", " + this.y + ") is " + (this.state == State.Alive ? "alive" : "dead");
        //return this.state == alive ? "O" : "X";
    }

    /**
     * Seeds the current cell based on a collection of seeds. Current cell becomes alive if it is in the seed.
     *
     * @param seed The collection of seeds.
     */
    public void seed(ArrayList<Cell> seed) {
        for(Cell cell: seed) {
            if(cell.x == this.x && cell.y == this.y)
                this.state = State.Alive;
        }
    }

    /**
     * Adds the current cell to the next seed, when the current cell is alive.
     *
     * @param game The game that needs the seed.
     */
    public void addToSeed(Game game) {
        if(state== State.Alive)
            game.add(this.x, this.y);
    }
}
