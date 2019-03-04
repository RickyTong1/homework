
public class Polyomial {

	public static final int ADD = 1;
	public static final int MINUS = -1;
	int coefficient;// 系数
	int exponent;// 指数
	Polyomial next = null;

	public Polyomial add(Polyomial sec, int mode) {
		Polyomial ptr = sec;
		Polyomial ths = this;
		Polyomial result = new Polyomial();
		Polyomial p = result;
		
		
		while (ptr != null && ths != null
				&& !(ptr.coefficient* ptr.exponent == 0
				&& ths.coefficient* ths.exponent == 0)) {
			result.exponent = ths.exponent;
			result.coefficient = ths.coefficient;
			if (ptr.exponent == ths.exponent) {
				result.coefficient += mode*ptr.coefficient;
				ptr = ptr.next;
				ths = ths.next;
				result.next = new Polyomial();
				result = result.next;
			} else {
				result.next = new Polyomial();
				result = result.next;		
				result.coefficient = mode*ptr.coefficient;
				result.exponent = ptr.exponent;
				result.next = new Polyomial();
				result = result.next;
				ptr = ptr.next;
			}
			//result = new Polyomial();
		}
		if(ptr != null && ths == null&&ptr.coefficient != 0 &&ptr.exponent !=0) {
			while(ptr != null) {
				result.coefficient = mode*ptr.coefficient;
				result.exponent = ptr.exponent;
				result.next = new Polyomial();
				result = result.next;
				ptr = ptr.next;
				

			}
		} else {
			while(ths.next != null) {
				result.coefficient = ths.coefficient;
				result.exponent = ths.exponent;
				result.next = new Polyomial();
				result = result.next;
				ths = ths.next;
			}
		}
		return p;
	}
}
