package Mission;

public class Mission3 {
	public static void main(String[] args) {
		
		for (int i = 1; i <= 100; i++) {
			int a = i / 10;
			int b = i % 10;
			boolean one = b == 3 || b ==6 || b==9;
			boolean ten = a == 3 || a ==6 || a==9;
			
			if(ten && one) {
				System.out.println("**");
			}else if(ten || one){
				System.out.println("*");
			}else {
				System.out.println(i);
			}
		}
		
	}
}