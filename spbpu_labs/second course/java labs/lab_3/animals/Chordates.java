package animals;

public abstract class Chordates {

	protected String name;
	protected int age;
	protected double weight;
	
	public Chordates(String name, int age, double weight) {
		this.name = name;
		this.age = age;
		this.weight = weight;
	}
	
	public String getName() {
        return name;
    }
	
	public int getAge() {
		return age;
	}
	
	public double getWeight() {
		return weight;
	}
	
	public abstract void print();
}
