package Mission;

import java.util.Scanner;

public class Mission5 {
	static int count=0;
	
	static void print(int me) {
		switch (me) {
		case 1:
			System.out.println("'황금올리브 반반한 치킨'이(/가) 주문되었습니다.\n");
			count++;
			break;
		case 2:
			System.out.println("'뿌잉클 치킨'이(/가) 주문되었습니다.\n");
			count++;
			break;
		case 3:
			System.out.println("'처갓집에서 호식이가 만든 치킨'이(/가) 주문되었습니다.\n");
			count++;
			break;
		default:
			System.out.println("(0~3) 사이의 번호를 입력해주세요.\n");
			break;
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		do {
			System.out.println("********** 치킨 메뉴판 **********");
			System.out.println("1. 황금올리브 반반한 치킨");
			System.out.println("2. 뿌잉클 치킨");
			System.out.println("3. 처갓집에서 호식이가 만든 치킨");
			System.out.println("0. 종료");
			System.out.print("********** 번호 : ");
			int menu = sc.nextInt();
			System.out.println();
			
			if(menu == 0) {
				System.out.println("총 "+count+" 개의 상품이 주문되었습니다.");
				break;
			}
			print(menu);
		}while(true);
		
		sc.close();
	}
}