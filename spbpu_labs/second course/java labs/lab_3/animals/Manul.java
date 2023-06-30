package animals;

public class Manul extends Feline{

	public Manul(String name, int age, double weight) {
		super(name, age, weight);
	}
	
	@Override
	public void print() {
		System.out.println("Animal kingdom: manul; Name: " + 
				name + "; Age: " + age + "; Weight: " + weight);
	}
}
