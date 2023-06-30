package lab_1;
import java.util.Scanner;
import Strategy.*;

public class Main {
	public static void main(String[] args) {

		Hero hero1 = new Hero(new MovingOnFootStr());
		int n = 1;
		Scanner in = new Scanner(System.in);
		
		while (n != 0)
		{
			n = in.nextInt();
			if (n == 1) hero1.setMoveStr(new MovingOnFootStr());
			else if (n == 2) hero1.setMoveStr(new MovingByHorseStr());
			else if (n == 3) hero1.setMoveStr(new MovingByCarStr());
			else if (n == 4) hero1.setMoveStr(new MovingThroughAirStr());
			else break;
			hero1.move();
		}
		in.close();
	}
}
