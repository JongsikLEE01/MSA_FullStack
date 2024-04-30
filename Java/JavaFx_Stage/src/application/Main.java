package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCombination;

public class Main extends Application {
	// 화면 시작 메소드
	@Override
	public void start(Stage stage) {
		// 단일 stage 객체부터 화면이 시작
		Group root = new Group();		// Scene 지정을 위한 그룹 객체
		
		Scene scene = new Scene(root);	// Scene 생성 -> new Scene(레이아웃);
		
		stage.setTitle("프로그램 이름");	//Stage 타이틀 성정
		
		Image icon = new Image("icon.png");	
		stage.getIcons().add(icon);		// Stage프로그램 아이콘 지정
		
		stage.setWidth(500);			// stage의 윈도우 크기 지정
		stage.setHeight(400);
		stage.setResizable(false);		// 크기조절 비활성화
		stage.setX(50);					// 위치 설정
		stage.setY(50);
		stage.setFullScreen(true);		// 전체화면 설정
		stage.setFullScreenExitHint("전체화면 종료(Q)");		// 전체화면 종료 힌트 설정 
		stage.setFullScreenExitKeyCombination(KeyCombination.valueOf("Q"));	// 전체화면 취소 단축키
		stage.setScene(scene);			// Scene를 stage에 지정
		
		stage.show();					// stage 출력
	}
	
	// 프로그램 시작
	public static void main(String[] args) {
		launch(args); 	// 프로그램 시작 준비 작업 후 -> start() 호출
	}
}