import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;

public class PCB {
	Queue<Process> readyQueue = new ArrayBlockingQueue(100);
	Queue<Process> blockQueue = new ArrayBlockingQueue(100);
	Queue<Process> dieQueue = new ArrayBlockingQueue(100);
	/**
	 * 创建进程
	 */
	public void createProcess(int id, String name, int time, int priority) {
		Process process = new Process(id, name, time, priority);
		addReadyQueue(process);
	}

	/**
	 * 加入就绪队列
	 */
	public void addReadyQueue(Process p) {
		readyQueue.add(p);
	}

	/**
	 * 从就绪队列取出
	 */
	public Process removeReadyQueue() {
		return readyQueue.remove();
	}

	/**
	 * 加入阻塞队列
	 */
	public void addBlockQueue(Process p) {
		blockQueue.add(p);
	}

	/**
	 * 从阻塞队列唤醒，并加入就绪队列
	 */
	public Process removeBlockQueue() {
		return blockQueue.remove();
	}

	/**
	 * 加入消亡队列
	 */
	public void addDieQueue(Process p) {
		dieQueue.add(p);
	}

	/**
	 * 显示各队列状态
	 */
	public void printState() {
		System.out.print("就绪队列：");
		for (Object i : readyQueue) {
			Process p = (Process) i;
			System.out.print(p.name);
			System.out.print(" ");
		}
		System.out.print("\n阻塞队列：");
		for (Object i : blockQueue) {
			Process p = (Process) i;
			System.out.print(p.name);
			System.out.print(" ");
		}
		System.out.print("\n死亡队列：");
		for (Object i : dieQueue) {
			Process p = (Process) i;
			System.out.print(p.name);
			System.out.print(" ");
		}
	}

	/**
	 * 冒泡排序对队列进行优先级排序
	 * 
	 * @param q
	 */
	public void sort(Queue<Process> q) {
		if (!q.isEmpty()) {
			Process[] obj = new Process[q.size()];
			int readyNumber = q.size();
			for (int i = 0; i < readyNumber; i++) {
				obj[i] = q.remove();
			}
			while (readyNumber > 0) {
				Process temp = new Process();
				int i = 0, j = 1;
				while (j < readyNumber) {
					if (obj[i].priority > obj[j].priority) {
						temp = obj[i];
						obj[i] = obj[j];
						obj[j] = temp;
					}
					i++;
					j++;
				}
				q.add(obj[i]);
				readyNumber--;
			}
		}
	}

	/**
	 * 执行PCB
	 */
	public void run() {
		int flag = 1;// 循环的次数
		int timeFlag = 0;//总时间片数
		while (!readyQueue.isEmpty() || !blockQueue.isEmpty()) {
			int time=0;//每次循环的时间片数
			if (readyQueue.isEmpty()) {// 如果就绪队列空，则唤醒一个阻塞进程
				Process p = removeBlockQueue();
				p.active();
				p.call();
				time++;
				if (p.time <= 0) {
					p.timeOut();
					addDieQueue(p);
				} else {
					addReadyQueue(p);
				}
			} else {
				for (int i = 0; i < readyQueue.size(); i++) {
					Process p = removeReadyQueue();
					p.call();
					time++;
					if (p.time <= 0) {
						p.timeOut();
						addDieQueue(p);
						continue;
					}
					Random random = new Random();
					if (random.nextBoolean()) {
						addBlockQueue(p);
					} else {
						addReadyQueue(p);
					}
				}
			}
			for (int i = 0; i < blockQueue.size(); i++) {
				Process p = removeBlockQueue();
				Random random = new Random();
				if (random.nextBoolean()) {
					p.active();
					addReadyQueue(p);
				} else {
					p.block();
					addBlockQueue(p);
				}
			}
			sort(readyQueue);
			sort(blockQueue);
			System.out.println("\n第" + flag + "次运行："+"本次运行时间片数："+time);
			flag++;
			timeFlag += time;
			printState();
		}
		System.out.println("\n所有进程运行结束,总时间片数："+timeFlag);
	}
}
