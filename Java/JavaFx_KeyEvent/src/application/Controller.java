package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;

public class Controller {
	@FXML
	private AnchorPane scenePane;
	
	@FXML
	private Circle circle;	// main의 fx:id="circle"인 요소의 객체를 가져오
	@FXML
	private Label label;
	private double x;
	private double y;	
	
	public void up(ActionEvent e) {
		System.out.println("up");
		label.setText("시작위치 x : "+ circle.getCenterX() + ", y : "+circle.getCenterY());
		circle.setCenterY(y-=5);
	}
	public void down(ActionEvent e) {
		System.out.println("down");
		label.setText("시작위치 x : "+ circle.getCenterX() + ", y : "+circle.getCenterY());
		circle.setCenterY(y+=5);
	}
	public void left(ActionEvent e) {
		System.out.println("left");
		label.setText("시작위치 x : "+ circle.getCenterX() + ", y : "+circle.getCenterY());
		circle.setCenterX(x-=5);
	}
	public void right(ActionEvent e) {
		System.out.println("rigth");
		label.setText("시작위치 x : "+ circle.getCenterX() + ", y : "+circle.getCenterY());
		circle.setCenterX(x+=5);
	}
}