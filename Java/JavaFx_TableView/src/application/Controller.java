package application;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

// 테이블뷰, 리스트뷰 등의 데이터 초기화 Initializable 인터페이스 구현
public class Controller implements Initializable{
	@FXML
	public TableView<Board> boardTableView;
	@FXML
	public TableColumn<Board, Integer> colNo;
	@FXML
	public TableColumn<Board, String> colTitle;
	@FXML
	public TableColumn<Board, String> colWriter;
	@FXML
	public TableColumn<Board, String> colRegDate;
	@FXML
	public TableColumn<Board, String> colView;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Board board1 = new Board("제목1","작성자1","내용1");
		Board board2 = new Board("제목2","작성자2","내용2");
		Board board3 = new Board("제목3","작성자3","내용3");
		if(Main.boardList.size()==0) {
			Main.boardList.add(board1);
			Main.boardList.add(board2);
			Main.boardList.add(board3);			
		}
		
//		board1.setNo(1);		// 번호
//		board2.setNo(2);
//		board3.setNo(3);
//		
//		board1.setView(17);		// 조회수
//		board2.setView(11);
//		board3.setView(31);	
//		// 날짜 지정
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd - HH:mm:ss");
//		String reg1 = sdf.format(board1.getRegDate());
//		String reg2 = sdf.format(board2.getRegDate());
//		String reg3 = sdf.format(board3.getRegDate());
//		try { 
//			Date date1 = sdf.parse(reg1);
//			Date date2 = sdf.parse(reg2);
//			Date date3 = sdf.parse(reg3);
//			board1.setRegDate(date1);
//			board2.setRegDate(date2);
//			board3.setRegDate(date3);
//			System.out.println(date1);
//		} catch (Exception e) {
//			// TODO: handle exception
//		}

		// 테이블 컬럼에 보드 객체의 getter 이름 지정
		// setCellValueFactory(): 셀의 값을 지정
		// PropertyValueFactory("getter이름") : 값으로 들어갈 객체의 게터 이름을 지정하는 객체
		// getter이름 : getXXX get를 제외한  getter의 메소드 이름 -> getNo() = No
		// module-info.java --> java.base 모듈을 추가해야 사용 가능
		colNo.setCellValueFactory(new PropertyValueFactory<>("No"));		
		colTitle.setCellValueFactory(new PropertyValueFactory<>("Title"));		
		colWriter.setCellValueFactory(new PropertyValueFactory<>("Writer"));		
		colRegDate.setCellValueFactory(new PropertyValueFactory<>("RegDate"));		
		colView.setCellValueFactory(new PropertyValueFactory<>("View"));		
		
		// 테이블뷰에 데이터 추가
		ObservableList<Board> list = FXCollections.observableArrayList(
//				board1, board2, board3
				Main.boardList
		);
		
		boardTableView.setItems(list);
	}
	
	// 글쓰기 클릭 이벤트
	public void moveToInsert(ActionEvent event) throws IOException{
		System.out.println("글쓰기 화면으로 이동");
		Main.setRoot("Insert");
	}
}