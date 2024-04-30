package Mission;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;

public class Mission12 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("몇게임? ");
		int game = sc.nextInt();
		
		for (int i = 0; i < game; i++) {
			// 번호 생성
			HashSet<Integer> lotto = new HashSet<Integer>();
			while(lotto.size() < 6) {
				int num = (int) (Math.random()*45+1);
				lotto.add(num);
			}
			
			// 번호 arrayList에 저장
			ArrayList<Integer> arr = new ArrayList<Integer>(lotto);
			int res = 0;
			for (int j = 0; j < arr.size(); j++) {
				res = arr.get(j);
			}
			// 정렬 및 출력
			Collections.sort(arr);
			System.out.println("["+(i+1)+" 게임]"+arr);
		}
		sc.close();
	}
}