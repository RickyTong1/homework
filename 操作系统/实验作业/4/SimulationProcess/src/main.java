import java.util.Scanner;

/**
 * 模拟PCB调度进程运行
 *	(1) 先来先服务算法 
 *	(2) 短作业优先算法 
 *	(3) 优先权算法 (非抢占式，静态优先级)
 *	(4) 基于时间片的多级反馈队列算法 
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
		System.out.println("进程模拟系统");
		System.out.print("请输入进程的个数:");
		int n = scanner.nextInt();
		scanner.nextLine();
		System.out.println("请依次输入进程的编号、名称、需要的时间片、优先级、到达时间，以“，”分隔，每个进程一行");
		for(int i=0;i<n;i++) {
			String s = scanner.nextLine();
			String[] split = s.split(",");
			pcb1.createProcess(Integer.parseInt(split[0]), split[1], Integer.parseInt(split[2]), Integer.parseInt(split[3]),Integer.parseInt(split[4]));
			pcb2.createProcess(Integer.parseInt(split[0]), split[1], Integer.parseInt(split[2]), Integer.parseInt(split[3]),Integer.parseInt(split[4]));
			pcb3.createProcess(Integer.parseInt(split[0]), split[1], Integer.parseInt(split[2]), Integer.parseInt(split[3]),Integer.parseInt(split[4]));
			pcb4.createProcessMFQ(Integer.parseInt(split[0]), split[1], Integer.parseInt(split[2]), Integer.parseInt(split[3]),Integer.parseInt(split[4]));
		
		}
		System.out.println("先来先服务");
		pcb1.FCFS();//先来先服务
		System.out.println("");System.out.println("短作业优先");
		pcb2.SJF();//短作业优先
		System.out.println("");System.out.println("优先权算法");
		pcb3.PSA();//优先权算法
		System.out.println("");System.out.println("基于时间片的多级反馈队列算法");
		pcb4.MFQ();//基于时间片的多级反馈队列算法
//		for(int i=0;i<4;i++) {
//			pcb.createProcess(1, "a", 5, 2);
//			pcb.createProcess(2, "b", 1, 4);
//			pcb.createProcess(3, "c", 3, 6);
//			pcb.createProcess(4, "d", 9, 8);
//			pcb.createProcess(5, "e", 7, 10);
//			switch(i) {
//				case 0: System.out.println("先来先服务"); pcb.FCFS(); break;
//				case 1: System.out.println("");System.out.println("短作业优先");pcb.SJF(); break;
//				case 2: System.out.println("");System.out.println("优先权算法");pcb.PSA(); break;
//				case 3: System.out.println("");System.out.println("基于时间片的多级反馈队列算法");pcb.MFQ(); break;
//			}
//			pcb.removeDieQueue();
//		}
//		pcb.FCFS();//先来先服务
//		pcb.SJF();//短作业优先
//		pcb.PSA();//优先权算法
//		pcb.MFQ();//基于时间片的多级反馈队列算法
	}

}
