package Mission;
// Mission 17
public class Driver {
	public static void main(String[] args) {
		// RcCar
		System.out.println("------- RcCar -------");
		RemoteControl rcCar = new RcCar();
		rcCar.on();
		rcCar.setSpeed(-20);	// 속도 제한 확인
		rcCar.setSpeed(10);
		rcCar.changeBattery();
		rcCar.setSpeed(50);
		rcCar.off();
		System.out.println();
		
		System.out.println("------- Drone -------");
		RemoteControl drone = new Drone();
		drone.on();
		drone.setSpeed(-20);
		drone.setSpeed(10);
		drone.changeBattery();
		drone.setSpeed(50);
		drone.off();
	}
}