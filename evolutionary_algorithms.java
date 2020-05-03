import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class evolutionary_algorithms {
    ArrayList<Individual> population = new ArrayList<>();

    public void initial_population() {
        population.add(new Individual(1, 2));
        population.add(new Individual(-2, 3));
        population.add(new Individual(4, -1));
        population.add(new Individual(5, 2));
        population.add(new Individual(-3, 3));
    }

    /*public int compute_fitness(int x, int y) {
        return x ^ 2 + y ^ 2;
    }*/

    public ArrayList<Individual> binary_tournament() {
        ArrayList<Individual> parents = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            //Generating random indices for parent selection
            int random_1 = (int) (Math.random() * (population.size()));
            int random_2 = (int) (Math.random() * (population.size()));

            //Making sure both indices are not the same
            while(random_1 == random_2) {
                random_1 = (int) (Math.random() * (population.size()));
                random_2 = (int) (Math.random() * (population.size()));
            }

            //Selecting the individual with the better fitness value
            if(population.get(random_1).fitness_value > population.get(random_2).fitness_value)
                parents.add(population.get(random_1));
            else
                parents.add(population.get(random_2));
        }
        return parents;
    }

    public ArrayList<Individual> crossover() {
        ArrayList<Individual> parent = binary_tournament();
        ArrayList<Individual> offsprings = new ArrayList<Individual>();
        for(int i = 0; i < 4; i += 2) {
            Individual parent_1 = parent.get(i);
            Individual parent_2 = parent.get(i + 1);

            offsprings.add(new Individual(parent_1.x, parent_2.y));
            offsprings.add(new Individual(parent_2.x, parent_1.y));
        }
        return offsprings;
    }

    public void mutation() {
        ArrayList<Individual> offsprings = crossover();
        for(int i = 0; i < offsprings.size(); i++) {
            if(Math.random() <= 0.5) {
                int temp = offsprings.get(i).x;
                offsprings.get(i).x = offsprings.get(i).y;
                offsprings.get(i).y = temp;
            }
            population.add(offsprings.get(i));
        }
    }

    public void truncation() {
        while(population.size() > 5) {
            int index = 0;
            int min = Integer.MAX_VALUE;
            for(int i = 0; i < population.size(); i++) {
                if(population.get(i).fitness_value < min) {
                    min = population.get(i).fitness_value;
                    index = i;
                }
            }
            population.remove(index);
        }
    }

    public int maximum_fitness() {
        int max = Integer.MIN_VALUE;
        for(int i = 0; i < population.size(); i++) {
            if(population.get(i).fitness_value > max)
                max = population.get(i).fitness_value;
        }
        return max;
    }

    public void generate_population() {
        initial_population();
        System.out.println("Initial Population: " + population.toString());
        System.out.println("Initial best fitness: " + maximum_fitness());
        int generations = 20;
        for(int i = 0; i < generations; i++) {
            binary_tournament();
            crossover();
            mutation();
            truncation();
            System.out.println(population.toString());
            System.out.println("Maximum fitness value on " + (i + 1) + " iteration: " + maximum_fitness() + ".");
        }
    }

    public static void main(String[] args) {
        evolutionary_algorithms test = new evolutionary_algorithms();
        test.generate_population();
    }
}
