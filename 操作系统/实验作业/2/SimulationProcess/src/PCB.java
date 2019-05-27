import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;

public class PCB {
	Queue<Process> readyQueue = new ArrayBlockingQueue(100);
	Queue<Process> blockQueue = new ArrayBlockingQueue(100);
	Queue<Process> dieQueue = new ArrayBlockingQueue(100);
	/**
	 * ��������
	 */
	public void createProcess(int id, String name, int time, int priority) {
		Process process = new Process(id, name, time, priority);
		addReadyQueue(process);
	}

	/**
	 * �����������
	 */
	public void addReadyQueue(Process p) {
		readyQueue.add(p);
	}

	/**
	 * �Ӿ�������ȡ��
	 */
	public Process removeReadyQueue() {
		return readyQueue.remove();
	}

	/**
	 * ������������
	 */
	public void addBlockQueue(Process p) {
		blockQueue.add(p);
	}

	/**
	 * ���������л��ѣ��������������
	 */
	public Process removeBlockQueue() {
		return blockQueue.remove();
	}

	/**
	 * ������������
	 */
	public void addDieQueue(Process p) {
		dieQueue.add(p);
	}

	/**
	 * ��ʾ������״̬
	 */
	public void printState() {
		System.out.print("�������У�");
		for (Object i : readyQueue) {
			Process p = (Process) i;
			System.out.print(p.name);
			System.out.print(" ");
		}
		System.out.print("\n�������У�");
		for (Object i : blockQueue) {
			Process p = (Process) i;
			System.out.print(p.name);
			System.out.print(" ");
		}
		System.out.print("\n�������У�");
		for (Object i : dieQueue) {
			Process p = (Process) i;
			System.out.print(p.name);
			System.out.print(" ");
		}
	}

	/**
	 * ð������Զ��н������ȼ�����
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
	 * ִ��PCB
	 */
	public void run() {
		int flag = 1;// ѭ���Ĵ���
		int timeFlag = 0;//��ʱ��Ƭ��
		while (!readyQueue.isEmpty() || !blockQueue.isEmpty()) {
			int time=0;//ÿ��ѭ����ʱ��Ƭ��
			if (readyQueue.isEmpty()) {// ����������пգ�����һ����������
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
			System.out.println("\n��" + flag + "�����У�"+"��������ʱ��Ƭ����"+time);
			flag++;
			timeFlag += time;
			printState();
		}
		System.out.println("\n���н������н���,��ʱ��Ƭ����"+timeFlag);
	}
}
