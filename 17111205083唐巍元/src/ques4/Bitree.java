package ques4;

public class Bitree {

	Bitree left;
	Bitree right;
	int num;
	
	public static void trance(Bitree head, int[] m,int i) {
		
		if(i > m.length)
			head = null;
		else {
			head = new Bitree();
			head.num=m[i-1];
			trance(head.left,m,2*i);
			trance(head.right,m,2*i+1);
		}
	}

	public static void main(String...strings) {
		int[] m = {1,2,3,4,5,6,6,7,8};
		trance(new Bitree(),m,1);
	}
}
