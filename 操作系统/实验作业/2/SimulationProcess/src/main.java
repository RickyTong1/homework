import java.util.Scanner;

/**
 * 模拟PCB调度进程运行
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
		System.out.println("进程模拟系统");
		System.out.print("请输入进程的个数:");
		int n = scanner.nextInt();
		scanner.nextLine();
		System.out.println("请依次输入进程的编号、名称、需要的时间片、优先级，以“，”分隔，每个进程一行");
		for(int i=0;i<n;i++) {
			String s = scanner.nextLine();
			String[] split = s.split(",");
			pcb.createProcess(Integer.parseInt(split[0]), split[1], Integer.parseInt(split[2]), Integer.parseInt(split[3]));
		}
		pcb.run();
	}

}
