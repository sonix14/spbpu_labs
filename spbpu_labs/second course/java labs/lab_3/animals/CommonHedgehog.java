package animals;

public class CommonHedgehog extends Hedgehogs{

	public CommonHedgehog(String name, int age, double weight) {
		super(name, age, weight);
	}
	
	@Override
	public void print() {
		System.out.println("Animal kingdom: simple hedgehog; Name: " + 
							name + "; Age: " + age + "; Weight: " + weight);
	}
}
