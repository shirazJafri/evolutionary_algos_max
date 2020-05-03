public class Individual {
    int x;
    int y;
    int fitness_value;
    public Individual(int x_coordinate, int y_coordinate) {
        x = x_coordinate;
        y = y_coordinate;
        fitness_value = (int) (Math.pow(x, 2) + Math.pow(y, 2));
    }

    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
