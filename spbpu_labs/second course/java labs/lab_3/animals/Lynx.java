package animals;

public class Lynx extends Feline{

	public Lynx(String name, int age, double weight) {
		super(name, age, weight);
	}
	
	@Override
	public void print() {
		System.out.println("Animal kingdom: lynx; Name: " + 
				name + "; Age: " + age + "; Weight: " + weight);
	}
}
