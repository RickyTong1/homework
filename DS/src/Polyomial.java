
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
		
		while (ptr != null && ths.next != null) {
			result.coefficient = ths.coefficient;
			result.exponent = ths.exponent;
			if (ptr.exponent == ths.exponent) {
				result.coefficient += mode*ptr.coefficient;
				ptr = ptr.next;
				ths = ths.next;
				result = result.next;
			} else {
				result = result.next;
				result.coefficient = mode*ptr.coefficient;
				result.exponent = ptr.exponent;
				result = result.next;
				ptr = ptr.next;
			}
		}
		if(ptr != null && ths == null) {
			while(ptr != null) {
				result.coefficient = mode*ptr.coefficient;
				result.exponent = ptr.exponent;
				result = result.next;
				ptr = ptr.next;
			}
		} else {
			while(ths != null) {
				result.coefficient = ths.coefficient;
				result.exponent = ths.exponent;
				result = result.next;
				ths = ths.next;
			}
		}
		return result;
	}
}
