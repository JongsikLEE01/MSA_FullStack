package Mission;

public class Account {
	private int deposit;		// 잔액
	private String deposotor;	// 예금주
	private String depoNumber;	// 계좌
	private int passwd;	// 계좌
	
	// 생성자
	public Account() {
		this(0,"이름 없음","계좌 없음",0);
	}
	public Account(int deposit, String deposotor, String depoNumber, int passwd) {
		this.deposit = deposit;
		this.deposotor = deposotor;
		this.depoNumber = depoNumber;
		this.passwd = passwd;
	}
	// getter, setter
	public int getPasswd() {
		return passwd;
	}
	public void setPasswd(int passwd) {
		this.passwd = passwd;
	}
	public int getDeposit() {
		return deposit;
	}
	public void setDeposit(int deposit) {
		this.deposit = deposit;
	}
	public String getDeposotor() {
		return deposotor;
	}
	public void setDeposotor(String deposotor) {
		this.deposotor = deposotor;
	}
	public String getDepoNumber() {
		return depoNumber;
	}
	public void setDepoNumber(String depoNumber) {
		this.depoNumber = depoNumber;
	}
	@Override
	public String toString() {
		return deposotor+"\t\t"+depoNumber+"\t\t"+deposit;
	}
	
	
}