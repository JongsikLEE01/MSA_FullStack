package Mission;

public class Drone implements RemoteControl{
	double bettery;
	double speed;
	
	@Override
	public void on() {
		System.out.println("Drone turn On...");
	}
	@Override
	public void off() {
		// TODO Auto-generated method stub
		System.out.println("Drone turn Off...");
	}
	@Override
	public void changeBattery() {
		System.out.println("Drone bettery Change...");
	}
	@Override
	public void setSpeed(int speed) {
		if(speed > MAX_SPEED) 	this.speed = MAX_SPEED;
		else if(speed < MIN_SPEED)	 this.speed = MIN_SPEED;
		else 	this.speed = speed;
		System.out.println("speed? "+this.speed);
	}
}