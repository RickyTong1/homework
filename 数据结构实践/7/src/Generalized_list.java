
import java.util.Stack;

/**
 * ���������� 1�������Ĺ��� �� 1.1 ����һ���յĹ���� 1.2 �������еĹ��������һ���µĹ���� 1.3 ���ݹ�����ַ�������һ�������
 * 2����������� 3�������ĳ��� 4�������������˳���ӡ����� 5���������ͷ 6���������β
 *
 */
public class Generalized_list {

	public static final int TAG_ITEM = 0; // ԭ�ӽڵ�
	public static final int TAG_TABLE = 1; // ��ڵ�
	/*
	 * �����֧�ֵķ��Ű���'(' , ')' , '{' , '}' , '[' , ']' ������ʾ����,ʹ���ַ�����������ʱ��һ���ַ�������'(',
	 * '{' , '[' ֮һ ����')' , '}' , ']' ֮һ������ ���Ҹ��������Ӧ
	 */
	private char mStartSymb = '(';
	private char mEndSymb = ')';
	private Node mGenTable;

	public Generalized_list() {
		mGenTable = new Node(null, null, TAG_TABLE, null);
	}

	/**
	 * ʹ�ù����������
	 * 
	 * @param src
	 */
	public Generalized_list(Generalized_list src) {
		if (src != null) {
			mGenTable = src.mGenTable;
		}

	}

	/**
	 * ʹ���ַ�����������
	 * 
	 * @param genTable
	 */
	public Generalized_list(String genTable) {
		if (genTable == null) {
			throw new NullPointerException("genTable is null in constructor GeneralizedTable!...");
		}
		initTable(genTable);
	}

	private void initTable(String genTable) {
		String ts = genTable.replaceAll("\\s", "");
		int len = ts.length();
		Stack<Character> symbStack = new Stack<Character>();
		Stack<Node> nodeStck = new Stack<Node>();
		initSymbolicCharactor(ts);
		mGenTable = new Node(null, null, TAG_TABLE, null);
		Node itemNode, tableNode = mGenTable, tmpNode;
		for (int i = 0; i < len; i++) {
			if (ts.charAt(i) == mStartSymb) {
				tmpNode = new Node(null, null, TAG_TABLE, null);
				// tableNode = tableNode.mPt;
				symbStack.push(ts.charAt(i));
				if (symbStack.size() > 1) {
					nodeStck.push(tableNode);
					tableNode.mPh = tmpNode;
					tableNode = tableNode.mPh;
				} else {
					tableNode.mPt = tmpNode;
					tableNode = tableNode.mPt;
				}
			} else if (ts.charAt(i) == mEndSymb) {
				if (symbStack.isEmpty()) {
					throw new IllegalArgumentException("IllegalArgumentException in constructor GeneralizedTable!...");
				}
				if (symbStack.size() > 1) {
					tableNode = nodeStck.pop();
				}
				symbStack.pop();
			} else if (ts.charAt(i) == ',') {
				tableNode.mPt = new Node(null, null, TAG_TABLE, null);
				tableNode = tableNode.mPt;
			} else {
				itemNode = new Node(null, null, TAG_ITEM, ts.charAt(i));
				tableNode.mPh = itemNode;
			}
		}

		if (!symbStack.isEmpty()) {
			throw new IllegalArgumentException("IllegalArgumentException in constructor GeneralizedTable!...");
		}
	}

	private void initSymbolicCharactor(String ts) {
		mStartSymb = ts.charAt(0);
		switch (mStartSymb) {
		case '(':
			mEndSymb = ')';
			break;
		case '{':
			mEndSymb = '}';
			break;
		case '[':
			mEndSymb = ']';
			break;
		default:
			throw new IllegalArgumentException("IllegalArgumentException ---> initSymbolicCharactor");
		}
	}

	public void print() {
		print(mGenTable);
	}

	private void print(Node node) {
		if (node == null) {
			return;
		}
		if (node.mTag == 0) {
			System.out.print(node.mData.toString() + "\t");
		}
		print(node.mPh);
		print(node.mPt);

	}

	public int depth() { // ���������
		if (mGenTable == null) {
			throw new NullPointerException("Generalized Table is null !.. ---> method depth");
		}
		return depth(mGenTable);
	}

	private int depth(Node node) {
		if (node == null || node.mTag == TAG_ITEM) {
			return 0;
		}
		int depHeader = 0, depTear = 0;
		depHeader = 1 + depth(node.mPh);
		depTear = depth(node.mPt);
		return depHeader > depTear ? depHeader : depTear;
	}

	public int length() { // �����ĳ���
		if (mGenTable == null || mGenTable.mPt == null) {
			return -1;
		}
		int tLen = 0;
		Node node = mGenTable;
		while (node.mPt != null) {
			node = node.mPt;
			if (node.mPh == null && node.mPt == null) {
				break;
			}
			tLen++;
		}
		return tLen;
	}

	public Generalized_list getHeader() {
		if (isEmpty())
			return null;
		Node node = mGenTable.mPt;
		Generalized_list gt = new Generalized_list();
		gt.mGenTable.mPt = node.mPh;
		return gt;
	}

	public Generalized_list getTear() {
		if (isEmpty())
			return null;
		Node node = mGenTable.mPt;
		Generalized_list gt = new Generalized_list();
		gt.mGenTable.mPt = node.mPt;
		return gt;
	}

	public boolean isEmpty() {
		if (mGenTable == null) {
			return true;
		}
		Node node = mGenTable.mPt;
		return node == null || node.mPh == null;
	}

	public class Node {// �����ڵ�
		Node mPh; // �����ı�ڵ�
		Node mPt; // ������β�ڵ�
		int mTag; // mTag == 0 , ԭ�ӽڵ� ; mTag == 1 , ��ڵ� ��
		Object mData; // ����������ֵ

		public Node(Node ph, Node pt, int tag, Object data) {
			mPh = ph;
			mPt = pt;
			mTag = tag;
			mData = data;
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String tStr = "((),(a,b,c),a,d,((d,g,(c))),(k,g),c)";
		String p = "((),a,b,(a,b,c),(a,(a,b),c))";
		String p2 = "((()),2)";
		String space = "()";
		String big = "{{a,b},{{a,g},{h},{a,n,f,{a,b,c}}},c}";
		String middle = "[[p],[[d,f,[g]]],[h],[2]]";
		Generalized_list gTab = new Generalized_list(middle);
		Generalized_list header, tear;
		System.out.print(middle+":	");
		gTab.print();
		System.out.println();
		System.out.println("length: " + gTab.length());
		System.out.println("depth: " + gTab.depth());
		header = gTab.getHeader();
		if (header != null) {
			System.out.print("head: ");
			header.print();
		}
		tear = gTab.getTear();

		if (tear != null) {
			System.out.print("\ntail: ");
			tear.print();
		}
	}

}
