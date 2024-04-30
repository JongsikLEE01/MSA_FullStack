package _1_4;

public class chap4 {
	public static void main(String[] args) {
		//4-1
		int num1 = 10;
		int num2 = 2;
		char operator = '+';
		// if문
		if(operator == '+') {
			System.out.printf("%d + %d = %d", num1, num2, num1+num2);
		}else if(operator == '-') {
			System.out.printf("%d -  %d = %d", num1, num2, num1-num2);
		}else if(operator == '*') {
			System.out.printf("%d / %d = %d", num1, num2, num1/num2);
		}else if(operator == '%') {
			System.out.printf("%d % %d = %d", num1, num2, num1%num2);
		}
		System.out.println();
		// switch문
		switch (operator) {
		case '+':
			System.out.printf("%d + %d = %d", num1, num2, num1+num2);
			break;
		case '-':
			System.out.printf("%d -  %d = %d", num1, num2, num1-num2);
			break;
		case '/':
			System.out.printf("%d / %d = %d", num1, num2, num1/num2);
			break;
		case '%':
			System.out.printf("%d % %d = %d", num1, num2, num1%num2);
			break;
		}
		System.out.println();
		
		//4-2
		for (int i = 1; i < 10; i++) {
			if(i % 2 == 1)
				continue;
			for (int j = 1; j < 10; j++) {
				System.out.print(i+"*"+j+"="+i*j);
				System.out.println();
			}	
		}
		System.out.println();
		
		//4-3
		for(int i = 1; i < 10; i++) {
			for (int j = 1; j < 10; j++) {
				for (int j2 = 0; j2 < j; j2++) {
					if (i >= j) {
						System.out.print(i+"*"+j+"="+i*j);
						System.out.println();
						break;
					}
				}
			}
		}
		System.out.println();
		
		//4-4
		for (int i=0; i<7/2+1;i++){
			for (int j=0; j<7;j++ ){
				//위쪽 영역
				if(i<=7/2){
					if (i+j<=7/2-1)
						System.out.print(" ");
					else if(j-i>=7/2+1)
						System.out.print(" ");
					else
						System.out.print("*");
				}
			}
			System.out.println();
		}
		System.out.println();
		
		//4-5
		for (int i=0; i<7;i++){
			for (int j=0; j<7;j++ ){
				//위쪽 영역
				if(i<=7/2)
				{
					if (i+j<=7/2-1)
						System.out.print(" ");
					else if(j-i>=7/2+1)
						System.out.print(" ");
					else
						System.out.print("*");
				}
				//아래쪽 영역
				else if(i>7/2){
					if(i-j>=7/2+1)
						System.out.print(" ");
					else if(i+j>=7/2*3+1)
						System.out.print(" ");
					else
						System.out.print("*");
				}
			}
			System.out.println();
		}
	}
}