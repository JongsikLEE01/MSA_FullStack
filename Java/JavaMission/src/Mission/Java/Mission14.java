package Mission;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Mission14 {
	public static ArrayList<String> fileRead(String path) {
		ArrayList<String> student = new ArrayList<String>();
		try {
			File text = new File(path);
			BufferedReader br = new BufferedReader(new FileReader(text));
			String list ="";
			
			// txt 파일 arraylist에 저장
			while ((list = br.readLine()) != null) {
				student.add(list);
			}
			
			// 구분기호 바꾸기
			for (int i = 0; i < student.size(); i++) {
				String replace = student.get(i).replace("/","\t");
				student.set(i, replace);
			}
			
			// 성적 순 내림차순
			Collections.sort(student, new Comparator<String>() {
				@Override
				public int compare(String o1, String o2) {
					int score1 = Integer.parseInt(o1.split("\t")[2]);
					int score2 = Integer.parseInt(o2.split("\t")[2]);
					return Integer.compare(score1, score2);
					
				}
			});
			br.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return student;
	}
	
	public static void main(String[] args) {
		String path = "./src/Mission/student.txt";
		
		System.out.println("반\t이름\t점수\t반");
		
		ArrayList<String> scoreList = fileRead(path);
		
		for (String studentList: scoreList) {
			System.out.println(studentList);
		}
	}
}