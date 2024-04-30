package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.shape.Circle;

public class Main extends Application {
	@Override
	public void start(Stage stage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
			Scene scene = new Scene(root);
			
			// 키 이벤트 등록
			setEvent(scene, root);
			
			stage.setScene(scene);
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setEvent(Scene scene, Parent root) {
		Circle circle = (Circle) root.lookup("#circle");
		if(circle != null) {
			//원이 존재하는 경우
			System.out.println("반지름? "+circle.getRadius());
		}else {
			//원이 존재하지 않는 경우
			System.out.println("원을 찾을 수 없음...");
			return;
		}
		Label label = (Label) root.lookup("#label");
		if(label != null) {
			System.out.println("존재");
		}
		
		// 키 입력 이벤트 등록
		scene.setOnKeyPressed(e -> {
			double x = circle.getCenterX();	// 원 중심 x좌표
			double y = circle.getCenterY();	// 원 중심 y좌표
			switch (e.getCode()) {			// getCode() : 입력한 키의 코드값
			case UP:
				System.out.println("UP");
				label.setText("시작위치 x : "+ circle.getCenterX() + ", y : "+circle.getCenterY());
				circle.setCenterY(y-=5);
				break;
			case DOWN:
				label.setText("시작위치 x : "+ circle.getCenterX() + ", y : "+circle.getCenterY());
				System.out.println("DOWN");
				circle.setCenterY(y+=5);
				break;
			case LEFT:
				label.setText("시작위치 x : "+ circle.getCenterX() + ", y : "+circle.getCenterY());
				System.out.println("LEFT");
				circle.setCenterX(x-=5);
				break;
			case RIGHT:
				label.setText("시작위치 x : "+ circle.getCenterX() + ", y : "+circle.getCenterY());
				System.out.println("RIGHT");
				circle.setCenterX(x+=5);
				break;
			}
		});
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
