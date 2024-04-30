package Mission;

import java.util.Scanner;

public class Mission8 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[][] b = new int[n][n];
		int a =1;
		
		for (int i = 0; i < n; i++) {
			if(i % 2 == 0) {
				for (int j = 0; j < n; j++) {
					b[i][j]=a++;
				}
			}
			else if(i % 2 !=0) {
				for (int j = n-1; j >= 0; j--) {
					b[i][j]=a++;
				}
			}
			for (int j = 0; j < n; j++) {
				System.out.print(b[i][j]+" ");
			}
			System.out.println();
		}
		sc.close();
	}
}