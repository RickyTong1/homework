package ques2;

public class Link {
	int num;
	Link next;

	public static boolean ifReverse(Link head) {
		Link temp = new Link();
		temp = head;
		int[] m = new int[1000];
		int i = 0;
		int length = 0;
		while (temp.next != null) {
			m[i++] = temp.num;
			length++;
			temp = temp.next;
		}

		for (i = 0; i < length / 2; i++) {
			if(m[i] != m[length - i -1]) return false;
		}
		return true;
	}
	public static void main(String...strings) {
		Link mm = new Link();
		Link temp = new Link();
		temp = mm;
		
		int[] m = {1,2,3,4,3,2,1};
		for(int i = 0; i < m.length; i++) {
			temp.num = m[i];
			temp.next = new Link();
			temp = temp.next;
		}
		if(ifReverse(mm))
			System.out.println("yes");
		else
			System.out.println("no");
	}
}
