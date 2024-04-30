package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Controller {
	// 레이아웃
	@FXML
	private AnchorPane scenePane;
	
	Stage stage;
	
	public void logout(ActionEvent event) {
		System.out.println("click logout...");

		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("logout");
		alert.setHeaderText("로그아웃 진행...");
		alert.setContentText("종료전에 저장?");
		
		if(alert.showAndWait().get() ==  ButtonType.OK) {
			// 레이아웃 -> scene ->window 순서로 접근
			stage = (Stage) scenePane.getScene().getWindow();
			System.out.println("프로그램 종료...");
			stage.close();
		}
	}
}