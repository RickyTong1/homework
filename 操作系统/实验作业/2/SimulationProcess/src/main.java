import java.util.Scanner;

/**
 * ģ��PCB���Ƚ�������
 * @author LiZhiWei
 * 2019.3.17
 */
public class main {

	public static void main(String[] args) {
		PCB pcb = new PCB();
//		pcb.createProcess(1, "a", 1, 2);
//		pcb.createProcess(2, "b", 3, 4);
//		pcb.createProcess(3, "c", 5, 6);
//		pcb.createProcess(4, "d", 7, 8);
//		pcb.createProcess(5, "e", 9, 10);
//		pcb.run();
		Scanner scanner = new Scanner(System.in);
		System.out.println("����ģ��ϵͳ");
		System.out.print("��������̵ĸ���:");
		int n = scanner.nextInt();
		scanner.nextLine();
		System.out.println("������������̵ı�š����ơ���Ҫ��ʱ��Ƭ�����ȼ����ԡ������ָ���ÿ������һ��");
		for(int i=0;i<n;i++) {
			String s = scanner.nextLine();
			String[] split = s.split(",");
			pcb.createProcess(Integer.parseInt(split[0]), split[1], Integer.parseInt(split[2]), Integer.parseInt(split[3]));
		}
		pcb.run();
	}

}
