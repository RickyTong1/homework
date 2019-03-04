import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class Sqlist {

	int length = 0;
	final int MAX = 65535;
	int sqlist[] = new int[MAX];

	public void sort() {
		sqlist = biSort(0, length - 1);
	}

	public void show() {
		for (int i = 0; i < length; i++) {
			System.out.print(sqlist[i] + " ");
		}
	}

	public void trimRept() {// 去除重复
		int i = 1;
		int key = sqlist[0];
		while (i < length) {
			if (sqlist[i] == key) {
				for (int j = i; j < length - 1;)
					sqlist[j] = sqlist[++j];
				length--;
			} else
				key = sqlist[i++];
		}
	}

	public void trimReptPro() {//哈希表排序
		HashMap<Integer, ? extends Integer> hash = new HashMap<>();
		for (int i = 0; i < length; i++) {
			hash.put(sqlist[i], null);
		}
		length = hash.size();
		Set<Integer> key = hash.keySet();
		Iterator<Integer> iter = key.iterator();
		int i = 0;
		while (iter.hasNext()) {
			sqlist[i++] = iter.next();
		}
	}

	private int[] biSort(int low, int high) {// 快速排序
		if (high <= low || sqlist == null)
			return sqlist;
		int pivot = sqlist[(high + low) / 2];
		int i = low;
		int j = high;
		while (i <= j) {
			while (sqlist[i] < pivot)
				++i;
			while (sqlist[j] > pivot)
				--j;
			if (i < j) {
				int t = sqlist[i];
				sqlist[i] = sqlist[j];
				sqlist[j] = t;
				++i;
				--j;
			} else
				i++;
		}
		sqlist = biSort(low, j);
		sqlist = biSort(i, high);

		return sqlist;
	}
}
