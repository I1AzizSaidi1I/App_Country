package isetb.tp6;

public class Country {
    private int id;
    private String name;
    private double population;

    public Country() {}

    public Country(String name, double population) {
        this.name = name;
        this.population = population;
    }

    public Country(int id, String name, double population) {
        this.id = id;
        this.name = name;
        this.population = population;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPopulation() {
        return population;
    }

    public void setPopulation(double population) {
        this.population = population;
    }
}