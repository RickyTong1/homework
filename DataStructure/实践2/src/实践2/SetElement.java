package 实践2;

import java.util.ArrayList;

public class SetElement {

	char elem;
	SetElement next = null;
	// ArrayList<? extends Object> em = new ArrayList<>();

	public SetElement setaUnion(SetElement b) {
		SetElement result = new SetElement();
		SetElement p = result;
		SetElement ths = this;
		SetElement ptr = b;
	
		SetElement pre = ptr;
		ptr = ptr.next;
		
		while(ths != null) {//查重
			while(ptr != null) {
				if(ths.elem == ptr.elem) 
					pre.next = pre.next.next;
				ptr = ptr.next;
			}
			ths = ths.next;
		}
		ths = this;
		ptr = b;
		p.elem = this.elem;
		if(b.elem != ths.elem) {//第一项
			p.next = new SetElement();
			p = p.next;
			p.elem = b.elem;
		}
		while(ths != null) {
			p.elem = ths.elem;
			p.next = new SetElement();
			p = p.next;
			ths = ths.next;
		}
		while(ptr != null) {
			
			p.elem = ptr.elem;
			p.next = new SetElement();
			p = p.next;
			ptr = ptr.next;
		}
		return result;
	}

	public SetElement intersection(SetElement b) {

		SetElement result = new SetElement();
		SetElement p = result;
		SetElement ths = this;
		SetElement ptr = b;

		while (ths != null) {
			while (ptr != null) {
				if (ths.elem == ptr.elem) {
					p.elem = ths.elem;
					p.next = new SetElement();
					p = p.next;
				}
				ptr = ptr.next;
			}
			ths = ths.next;
		}

		return result;

	}
	
	public void show() {
		SetElement p = this;
		while(p != null && p.elem != '\0') {
			System.out.print(p.elem+" ");
			p = p.next;
		}
	}
}
