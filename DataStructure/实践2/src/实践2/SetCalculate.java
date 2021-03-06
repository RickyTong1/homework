package 实践2;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

public class SetCalculate {
	
	public static void main(String...strings) {
		SetElement A = new SetElement();
		SetElement B = new SetElement();
		
		SetElement ap = A;
		SetElement bp = B;
		Scanner scan = new Scanner(System.in);
		
		char[] a, b;
		
		System.out.println("请输入两串字符，以$结尾。");
		System.out.println("第一串：");
		a = scan.next().toCharArray();
		System.out.println("第二串：");
		b = scan.next().toCharArray();
		scan.close();
		HashMap<Character, ? extends Object> hs = new HashMap<>();
		for(int i =0 ; i< a.length-1; i++) {
			hs.put(a[i], null);
		}
		Iterator<Character> iter = hs.keySet().iterator();
		while(iter.hasNext()) {
			ap.elem = iter.next();
			ap.next = new SetElement();
			ap = ap.next;
		}
		hs.clear();
		for(int i =0 ; i< b.length-1; i++) {
			hs.put(b[i], null);
		}
		iter = hs.keySet().iterator();
		while(iter.hasNext()){
			bp.elem = iter.next();
			bp.next = new SetElement();
			bp = bp.next;
		}
		A.setaUnion(B).show();
		System.out.println();
		A.intersection(B).show();
		System.out.println();
		B.show();
	}
}
