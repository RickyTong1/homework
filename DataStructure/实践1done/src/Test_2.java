import java.util.Scanner;

public class Test_2 {

	public static void main(String... strings) {
		Polyomial fir = new Polyomial();
		Polyomial sec = new Polyomial();
		Polyomial ptrF = fir;
		Polyomial ptrS = sec;
		int i = 1;

		Scanner scan = new Scanner(System.in);
		System.out.println("请输入两个多项式，升幂排列，以#号结尾。\n第一个多项式：");
		System.out.println("第"+i++ +"项的系数和指数：");
		
		while(scan.hasNext()) {
			try {
			
			ptrF.coefficient = scan.nextInt();
			ptrF.exponent = scan.nextInt();
			ptrF.next = new Polyomial();
			ptrF = ptrF.next;
			System.out.println("第"+i++ +"项的系数和指数：");
			} catch (Exception e){
				break;
			}
		}
		ptrF =null;
		String s = scan.next();
		s = null;
		System.gc();
		i = 1;
		System.out.println("升幂排列，以#号结尾。\n第二个多项式：");
		System.out.println("第"+i++ +"项的系数和指数：");
		while(scan.hasNext()) {
			try {
			ptrS.coefficient = scan.nextInt();
			ptrS.exponent = scan.nextInt();
			ptrS.next = new Polyomial();
			ptrS = ptrS.next;
			System.out.println("第"+i++ +"项的系数和指数：");
			} catch (Exception e) {
				break;
			}
		}
		String ss = scan.next();
		ss = null;
		System.gc();
		Polyomial result = new Polyomial();
		result = fir.add(sec, Polyomial.ADD);
		while(result.next != null) {
			if(result.coefficient != 0) {
				System.out.print(result.coefficient+"x^"+result.exponent);
				if(result.next.next != null)
					System.out.print("+");
			}
			result = result.next;
		}
	}
	
//	public static void legalCheck(String...s) {//多项式合法性检查
//		
//		int[] coef;//系数
//		int[] expo;//指数
//		for(int i = 0; i < s.length; i++) {
//			coef[i] = s[i].s
//		}
//		
//	}
	public static void input() {
		
	}
}
