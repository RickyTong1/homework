package ques3;

public class Bitree {

	public static void show(int[] m, int i) {
		if(i <= 1) {
			
			return;
		}
		int anc = i / 2;
		System.out.println(m[anc-1]);
		show(m , anc);
			
	}
	public static void main(String...strings) {
		int[] m = {1,2,3,4,5,6,7,8};
		show(m,6);
	}
}
