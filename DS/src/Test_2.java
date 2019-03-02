import java.util.Scanner;

public class Test_2 {

	public static void main(String... strings) {
		Polyomial fir = new Polyomial();
		Polyomial sec = new Polyomial();

		String inFir;
		String inSec;

		Scanner scan = new Scanner(System.in);
		System.out.println("请输入两个多项式，升幂排列，以#号结尾。例如：5+3x^2+4x^3.\n第一个：");
		inFir = scan.next();
		System.out.println("第二个：");
		inSec = scan.next();
		
		String firs[] = inFir.split("[+-]");
		String secs[] = inSec.split("[+-]");
	}
	
	public static void legalCheck(String...s) {//多项式合法性检查
		
		for(int i = 0; i < s.length; i++) {
			
		}
	}
	public static void input() {
		
	}
}
