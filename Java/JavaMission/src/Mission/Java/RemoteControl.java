package Mission;

public interface RemoteControl {
	int MAX_SPEED = 30;
	int MIN_SPEED = 0;
	
	void on();
	void off();
	void setSpeed(int speed);
	void changeBattery();
}
