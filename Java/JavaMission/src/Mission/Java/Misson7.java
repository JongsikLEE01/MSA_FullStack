package Mission;

import java.util.Scanner;

public class Misson7 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int a[] =new int[5];
		int temp;
		
		for (int i = 0; i < a.length; i++) {
			int n = sc.nextInt();
			a[i] = n;
		}
		
		// 오름차순, 출력
		for (int i = 0; i < a.length; i++) {
			for (int j = 1; j < a.length-i; j++) {
				if(a[j-1] > a[j]) {
					temp = a[j-1];
					a[j-1] = a[j];
					a[j] = temp;
				}
			}
		}
		for (int print : a) {
			System.out.print(print+" ");
		}
		System.out.println();
		
		// 내림차순, 출력
		for (int i = 0; i < a.length; i++) {
			for (int j = 1; j < a.length-i; j++) {
				if(a[j-1] < a[j]) {
					temp = a[j-1];
					a[j-1] = a[j];
					a[j] = temp;
				}
			}
		}
		for (int print : a) {
			System.out.print(print+" ");
		}
		
		sc.close();
	}
}