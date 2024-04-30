package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.BorderPane;

public class Main extends Application {
	@Override
	public void start(Stage stage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("Scene.fxml"));
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
					
			// 프로그램 종료 시, 처리 작업
			stage.setOnCloseRequest(event -> {
				event.consume();	// 기존 이벤트(프로그램 종료) 제거
				// 메소드 호츌
				logout(stage);		// 메소드 호출
			});
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void logout(Stage stage) {
		System.out.println("click logout...");
		
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("logout");
		alert.setHeaderText("로그아웃 진행...");
		alert.setContentText("종료전에 저장?");
		
		if(alert.showAndWait().get() ==  ButtonType.OK) {
			System.out.println("프로그램 종료...");
			stage.close();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}