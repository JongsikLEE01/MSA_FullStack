package Mission;

public class Triangle {
	double width, height;
	// 생성자
	public Triangle(double width, double height) {
		this.width = width;
		this.height = height;
	}
	public Triangle() {
	}
	// toString
	@Override
	public String toString() {
		return "Triangle [width=" + width + ", height=" + height + "]";
	}
	// 메소드
	public double getArea() {
		return width * height /2;
	}
}
