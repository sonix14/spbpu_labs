package lab_1;
import Strategy.MovingOnFootStr;
import Strategy.MovingStrategy;

public class Hero {

	MovingStrategy movingStrategy;
	
	public Hero(MovingStrategy movingStrategy)
	{
		this.movingStrategy = movingStrategy;
	}
	
	public Hero()
	{
		this.setMoveStr(new MovingOnFootStr());
	}
	
	public void move() 
	{
		movingStrategy.move();
	}
	
	public void setMoveStr(MovingStrategy movingStrategy)
	{
		this.movingStrategy = movingStrategy;
	}
}
