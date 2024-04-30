package Mission;

public class Circle {
	double radius;
	// 생성자
	public Circle(double radius) {
		this.radius = radius;
	}
	public Circle() {
	}
	// toString
	@Override
	public String toString() {
		return "Circle [radius=" + radius + "]";
	}
	// 메소드
	public double getArea() {
		return (radius * radius)*Math.PI;
	}
}
