package Mission;

public class Trapezoid {
	double topwidth, undertopwidth, height;
	// 생성자
	public Trapezoid(double topwidth, double undertopwidth, double height) {
		this.topwidth = topwidth;
		this.undertopwidth = undertopwidth;
		this.height = height;
	}
	public Trapezoid() {
	}
	// toString
	@Override
	public String toString() {
		return "Trapezoid [topwidth=" + topwidth + ", undertopwidth=" + undertopwidth + ", height=" + height + "]";
	}
	// 메소드
	public double getArea() {
		return ((topwidth+undertopwidth)*height)/2;
	}
}
