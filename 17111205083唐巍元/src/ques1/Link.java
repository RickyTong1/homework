package ques1;

public class Link{
	int num;
	Link next;
	
	public static void reverse(Link head) {
		Link pre = head;
        Link cur = head.next;
        Link tmp;
        // ͷ����nextӦ��Ҫ�ÿ�
        pre.next = null;
        while (cur != null) {
            // �ȴ��nex���
            tmp = cur.next;
            // �޸�next���ָ��pre
            cur.next = pre;
            pre = cur;
            cur = tmp;
        }
        while(pre.next != null) {
        	pre = pre.next;
        	System.out.println(pre.num);
        	
        }
	}
	public static void main(String...strings) {
		Link mm = new Link();
		Link temp = new Link();
		temp = mm;
		int[] m = {1,2,3,4};
		for(int i = 0 ; i < m.length; i++) {
			temp.num = m[i];
			temp.next = new Link();
			temp = temp.next;
		}
		reverse(mm);
	}
}
