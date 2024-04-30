package _1_4;

public class chap3 {
	public static void main(String[] args) {
		//3-1
		int myAge = 23;
		int teacherAge = 38;
		
		boolean value = (myAge > 25);
		System.out.println(value);
		
		System.out.println(myAge > 25);
		System.out.println(myAge == teacherAge);
		
		char ch;
		ch = (myAge > teacherAge) ? 'T' : 'F';
		System.out.println(ch);
		
		//3-2
		int num;
		num = -5 +3 * 10 /2;
		System.out.println(num);
		
		//3-3
		int num0 = 10;
		System.out.println(num0);
		System.out.println(num0++);
		System.out.println(num0);
		System.out.println(--num0);
		
		
		// 3-4
		int num1 = 10;
		int num2 = 20;
		boolean result;
		
		result = ((num1 > 10)&&(num2 > 10));
		System.out.println(result);
		result = ((num1 > 10)||(num2 > 10));
		System.out.println(result);
		System.out.println(!result);
		
		//3-5
		int num11 = 2;
		int num22 = 10;
		
		System.out.println(num11 & num22);
		System.out.println(num11 | num22);
		System.out.println(num11 ^ num22);
		System.out.println(~num11);
		
		//3-6
		int num111 = 8;
		
		System.out.println(num111 += 10);
		System.out.println(num111 -= 10);
		System.out.println(num111 >>= 10);
		
		//3-7
		int num1111 = 10;
		int num222 = 20;
		
		int result1 = (num1111 > 20) ? num1111+10 : num222-10;
		System.out.println(result1);
	}
}
