package Mission;

import java.util.Scanner;

public class Mission2 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int odd=0;
		int even=0;
		String strOdd = "";
		String strEven = "";
		// 홀수
		for (int i = 1; i <= n; i++) {
			if(i%2 == 1) {
				strOdd += i+"+";
				odd += i;				
			}
		}
		strOdd = strOdd.substring(0, strOdd.length()-1);
		System.out.println (strOdd + "=" + odd);
		
		// 짝수
		for (int i = 1; i <= n; i++) {
			if(i%2 == 0) {
				strEven += i+"+";
				even += i;
			}
		}
		strEven = strEven.substring(0, strEven.length()-1);
		System.out.println (strEven+"="+even);
		
		// 약수
		for (int i = 1; i <= n; i++) {
			if(n%i==0) {
				System.out.print(i +" ");
			}
		}
		sc.close();
	}
}