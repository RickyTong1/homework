import java.util.Scanner;

/**
 * ģ��PCB���Ƚ�������
 *	(1) �����ȷ����㷨 
 *	(2) ����ҵ�����㷨 
 *	(3) ����Ȩ�㷨 (����ռʽ����̬���ȼ�)
 *	(4) ����ʱ��Ƭ�Ķ༶���������㷨 
 * @author LiZhiWei
 * 2019.4.29
 */
public class main {

	public static void main(String[] args) {
		PCB pcb1 = new PCB();
		PCB pcb2 = new PCB();
		PCB pcb3 = new PCB();
		PCB pcb4 = new PCB();
//		pcb.createProcess(1, "a", 1, 2);
//		pcb.createProcess(2, "b", 3, 4);
//		pcb.createProcess(3, "c", 5, 6);
//		pcb.createProcess(4, "d", 7, 8);
//		pcb.createProcess(5, "e", 9, 10);
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("����ģ��ϵͳ");
		System.out.print("��������̵ĸ���:");
		int n = scanner.nextInt();
		scanner.nextLine();
		System.out.println("������������̵ı�š����ơ���Ҫ��ʱ��Ƭ�����ȼ�������ʱ�䣬�ԡ������ָ���ÿ������һ��");
		for(int i=0;i<n;i++) {
			String s = scanner.nextLine();
			String[] split = s.split(",");
			pcb1.createProcess(Integer.parseInt(split[0]), split[1], Integer.parseInt(split[2]), Integer.parseInt(split[3]),Integer.parseInt(split[4]));
			pcb2.createProcess(Integer.parseInt(split[0]), split[1], Integer.parseInt(split[2]), Integer.parseInt(split[3]),Integer.parseInt(split[4]));
			pcb3.createProcess(Integer.parseInt(split[0]), split[1], Integer.parseInt(split[2]), Integer.parseInt(split[3]),Integer.parseInt(split[4]));
			pcb4.createProcessMFQ(Integer.parseInt(split[0]), split[1], Integer.parseInt(split[2]), Integer.parseInt(split[3]),Integer.parseInt(split[4]));
		
		}
		System.out.println("�����ȷ���");
		pcb1.FCFS();//�����ȷ���
		System.out.println("");System.out.println("����ҵ����");
		pcb2.SJF();//����ҵ����
		System.out.println("");System.out.println("����Ȩ�㷨");
		pcb3.PSA();//����Ȩ�㷨
		System.out.println("");System.out.println("����ʱ��Ƭ�Ķ༶���������㷨");
		pcb4.MFQ();//����ʱ��Ƭ�Ķ༶���������㷨
//		for(int i=0;i<4;i++) {
//			pcb.createProcess(1, "a", 5, 2);
//			pcb.createProcess(2, "b", 1, 4);
//			pcb.createProcess(3, "c", 3, 6);
//			pcb.createProcess(4, "d", 9, 8);
//			pcb.createProcess(5, "e", 7, 10);
//			switch(i) {
//				case 0: System.out.println("�����ȷ���"); pcb.FCFS(); break;
//				case 1: System.out.println("");System.out.println("����ҵ����");pcb.SJF(); break;
//				case 2: System.out.println("");System.out.println("����Ȩ�㷨");pcb.PSA(); break;
//				case 3: System.out.println("");System.out.println("����ʱ��Ƭ�Ķ༶���������㷨");pcb.MFQ(); break;
//			}
//			pcb.removeDieQueue();
//		}
//		pcb.FCFS();//�����ȷ���
//		pcb.SJF();//����ҵ����
//		pcb.PSA();//����Ȩ�㷨
//		pcb.MFQ();//����ʱ��Ƭ�Ķ༶���������㷨
	}

}
