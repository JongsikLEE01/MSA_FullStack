package application;
	
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Main extends Application {
	private static Scene scece;
	public static List<Board> boardList = new ArrayList<Board>();
	
	@Override
	public void start(Stage stage) {
		try {
			scece = new Scene(loadFXML("Main"));
			stage.setScene(scece);
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	// fxml로부터 레이아웃(root)가져오기
	private static Parent loadFXML(String fxml) throws IOException{
		FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(fxml+".fxml"));
		return fxmlLoader.load();
	}
	// 현재 scene의 fxml(레이아웃) 재지정하기 : 화면이동
	static void setRoot(String fxml) throws IOException{
		scece.setRoot(loadFXML(fxml));
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}