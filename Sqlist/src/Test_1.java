import java.util.Scanner;

public class Test_1 {

	public static void main(String...strings) {
		int i = 0;//¼ÆÊýÆ÷
		Sqlist test = new Sqlist();
		System.out.println("Begin!");
		
		Scanner scan = new Scanner(System.in);
		while(!scan.next().equals("#")) {
			test.sqlist[i++] = scan.nextInt();
			System.out.println("Begin!");
		}
		System.out.println(test.sqlist[0]);
		scan.close();
	}
}
