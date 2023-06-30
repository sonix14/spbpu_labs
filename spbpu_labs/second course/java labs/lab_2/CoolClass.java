package lab_2;


public class CoolClass {
	
	public static void publicMethod1(int value){
		System.out.println("first public method");
		System.out.println("Parameters: " + value);
	}
	
	@CoolAnnotation(2)
	public static void publicMethod2(String str){
		System.out.println("second public method");
		System.out.println("Parameters: " + str);
	}
	
	@CoolAnnotation(1)
	protected static void protectedMethod1(float value, int count){
		System.out.println("first protected method");
		System.out.println("Parameters: " + value + ", " + count);
	}
	
	@CoolAnnotation(2)
	protected static void protectedMethod2(boolean flag, int count, double value){
		System.out.println("second protected method");
		System.out.println("Parameters: " + flag + ", " + count + ", " + value);
	}
	
	private static void privateMethod1(char a, String str){
		System.out.println("first private method");
		System.out.println("Parameters:" + a + ", " + str);
	}
	
	@CoolAnnotation(3)
	private static void privateMethod2(char a, boolean flag, String str){
		System.out.println("second private method");
		System.out.println("Parameters: " + a + ", " + flag + ", " + str);
	}
	
	@CoolAnnotation(4)
	protected static void privateMethod3(int count, double value){
		System.out.println("third private method");
		System.out.println("Parameters: " + count + ", " + value);
	}
}
