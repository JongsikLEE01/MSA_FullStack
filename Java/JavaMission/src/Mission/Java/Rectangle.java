package Mission;

public class Rectangle {
	double width, height;
	// 생성자
	public Rectangle(double width, double height) {
		this.width = width;
		this.height = height;
	}
	public Rectangle() {
	}
	// toString
	@Override
	public String toString() {
		return "Rectangle [width=" + width + ", height=" + height + "]";
	}
	// 메소드
	public double getArea() {
		return width * height;
	}
}
