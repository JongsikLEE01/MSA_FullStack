package Mission;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Bank {
	static Scanner sc = new Scanner(System.in);
	static int passwd;
	static int deposit;		// 잔액
	static String deposotor;	// 예금주
	static String depoNumber;	// 계좌
	static Account acc = new Account();
	static List<Account> list = new ArrayList<Account>();
	
	// 계좌 개설
	static void Acc1() {
		System.out.print("계좌번호>> ");
		depoNumber = sc.nextLine();
		sc.nextLine();
		System.out.print("예금주>> ");
		deposotor = sc.nextLine();
		System.out.print("최소예금액>> ");
		deposit = sc.nextInt();
		sc.nextLine();
		System.out.print("비밀번호>> ");
		passwd = sc.nextInt();
		
		acc.setDepoNumber(depoNumber);
		acc.setDeposit(deposit);
		acc.setDeposotor(deposotor);
		acc.setPasswd(passwd);
		list.add(new Account(acc.getDeposit(),acc.getDeposotor(),acc.getDepoNumber(),acc.getPasswd() ));
		System.out.println("'"+deposotor+"' 님의 계좌가 개설되었습니다.");
	}
	
	// 계좌 입금
	static void Acc2() {
		System.out.println("============= 입금 =============");
		System.out.print("계좌번호>> ");
		depoNumber = sc.nextLine();
		sc.nextLine();
		System.out.println("입금액>> ");
		int money = sc.nextInt();
		// 입금 한도 확인
		if(money > 1000000) {
			System.err.println("1,000,000원을 초과하여 입금할 수 없습니다.");
		}else {
			System.out.println("'"+acc.getDeposotor()+"' 님에게 입금하는게 맞으십니까?");
			System.out.println("1. 예");
			System.out.println("2. 아니요");
			System.out.print("입력>> ");
			int yesNo = sc.nextInt();
			// 입금 확인
			if(yesNo==1) {
				System.out.println("'"+acc.getDeposotor()+"' 님 계좌에 "+money+" 원이 입금되었습니다.");
				acc.setDeposit(money+acc.getDeposit());
			}
			if(yesNo==2) {
				System.out.println("입금이 취소되었습니다.");
			}
			
		}
	}
	
	// 출금
	static void Acc3() {
		System.out.println("============= 출금 =============");
		System.out.print("계좌번호>> ");
		depoNumber = sc.nextLine();
		sc.nextLine();
		System.out.print("비밀번호>> ");
		passwd = sc.nextInt();
		// 비밀번호확인
		if(passwd == acc.getPasswd()) {
			System.out.println("출금액>> ");
			int money = sc.nextInt();
			System.out.println("'"+acc.getDeposotor()+"' 님 계좌에 "+money+" 원이 출금되었습니다.");
			acc.setDeposit(acc.getDeposit()-money);
		}else {
			System.err.println("비밀번호가 다릅니다!!");
		}
	}
	
	// 조회
	static void Acc4() {
		System.out.println("============= 계좌조회 =============");
		System.out.print("계좌번호>> ");
		depoNumber = sc.nextLine();
		sc.nextLine();
		System.out.print("비밀번호>> ");
		passwd = sc.nextInt();
		if(passwd == acc.getPasswd()) {
			System.out.println("'"+acc.getDeposotor()+"' 님 계좌에 "+acc.getDeposit()+" 원입니다.");
		}else {
			System.err.println("비밀번호가 다릅니다!!");
		}
	}
	
	// 계좌 목록 확인
	static void Acc5() {
		System.out.print("관리자 비밀번호>> ");
		passwd = sc.nextInt();
		if(passwd == 1111) {
			System.out.println("============= 계좌목록 =============");
			System.out.println("예금주\t\t계좌번호\t\t잔고");
			for (Account account : list) {
				System.out.print(account);
				System.out.println();
			}
		}else {
			System.err.println("관리자 비밀번호가 다릅니다!!");
		}
	}
	
	public static void main(String[] args) {
		do {
			System.out.println("===================================");
			System.out.println("1. 계좌동륵");
			System.out.println("2. 입금");
			System.out.println("3. 출금");
			System.out.println("4. 계좌조회");
			System.out.println("5. 계좌목록");
			System.out.println("6. 종료");
			System.out.println("===================================");
			System.out.print("입력>> ");
			int num = sc.nextInt();
			
			if(num == 6) {
				System.out.println("시스템을 종료합니다.");
				break; 
			}
			
			switch (num) {
			case 1:
				Acc1();
				break;
			case 2:
				Acc2();
				break;
			case 3:
				Acc3();
				break;
			case 4:
				Acc4();
				break;
			case 5:
				Acc5();
				break;
			default:
				System.err.println("메뉴 안의 번호를 입력해주세요");
				break;
			}
		} while (true);
		sc.close();
	}
}