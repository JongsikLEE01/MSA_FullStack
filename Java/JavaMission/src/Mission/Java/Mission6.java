package Mission;

import java.util.Scanner;

public class Mission6 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("몇게임? ");
		int game = sc.nextInt();
		int[] lotto = new int[6]; 
		
		// 게임 출력
		for (int i = 0; i < game; i++) {
			System.out.print("["+(i+1)+" 게임] : ");
			// 번호 출력
			for (int j = 0; j < lotto.length; j++) {
				lotto[j] = (int) (Math.random()*45)+1;
				// 중복제거
				for (int k = 0; k < j; k++) {
					if(lotto[j] == lotto[k]) {
						k--;
						break;
					}
				}
				System.out.print(lotto[j]+" ");
			}
			System.out.println();
		}
		sc.close();
	}
}