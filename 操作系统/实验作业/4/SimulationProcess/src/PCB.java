import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;

public class PCB {
	Queue<Process> readyQueue = new ArrayBlockingQueue(100);
	Queue<Process> blockQueue = new ArrayBlockingQueue(100);
	Queue<Process> dieQueue = new ArrayBlockingQueue(100);
	Queue<Process> timeQueue = new ArrayBlockingQueue(100);
	/**
	 * ��������
	 */
	public void createProcessMFQ(int id, String name, int time, int priority,int readyTime) {
		Process process = new Process(id, name, time, priority,readyTime);
		timeQueue.add(process);
	}
	
	public void createProcess(int id, String name, int time, int priority,int readyTime) {
		Process process = new Process(id, name, time, priority,readyTime);
		readyQueue.add(process);
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
		p.block();
		blockQueue.add(p);
	}

	/**
	 * ���������л��ѣ��������������
	 */
	public Process removeBlockQueue() {
		Process p = blockQueue.remove();
		p.active();
		return p;
	}

	/**
	 * ������������
	 */
	public void addDieQueue(Process p) {
		p.timeOut();;
		dieQueue.add(p);
	}
	public void removeDieQueue() {
		for (int i = dieQueue.size(); i >0 ; i--) {
			dieQueue.remove();
		}
		
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
		System.out.println("");
	}
	
	/*
	 * ��ʾ�༶����״̬
	 */
	public void printStateMFQ(Queue<Process> readyQueue1,Queue<Process> readyQueue2,Queue<Process> readyQueue3) {
		System.out.print("��������1��");
		for (Object i : readyQueue1) {
			Process p = (Process) i;
			System.out.print(p.name);
			System.out.print(" ");
		}
		System.out.print("\n��������2��");
		for (Object i : readyQueue2) {
			Process p = (Process) i;
			System.out.print(p.name);
			System.out.print(" ");
		}
		System.out.print("\n��������3��");
		for (Object i : readyQueue3) {
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
		System.out.println("");
	}
	
	/**
	 * ð������Զ��н������ȼ�����(����)
	 * * @param q
	 */
	public void sortP(Queue<Process> q) {
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
	 * ð������Զ��н���ʱ��Ƭ����(����)
	 * * @param q
	 */
	public void sortT(Queue<Process> q) {
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
					if (obj[i].time < obj[j].time) {
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
	public void addRQ(int timeFlag,Queue<Process> q) {
		for (int i = timeQueue.size(); i >0 ; i--) {
			Process p =timeQueue.remove();
			if(p.time<=timeFlag) {
				q.add(p);
			}else {
				timeQueue.add(p);
			}
		}
	}
	/*
	 * �����ȷ���
	 */
	public void FCFS() {
		int timeFlag = 0;
		while (!readyQueue.isEmpty() || !blockQueue.isEmpty()) {
			int time = 0;
//			if (readyQueue.isEmpty()) {// ����������пգ�����һ����������
//				Process p = removeBlockQueue();
//				p.active();
//				while(true) {
//					p.call();
//					time++;
//					if (p.RTime <= 0) {
//						p.timeOut();
//						addDieQueue(p);
//						break;
//					}
//					Random random = new Random();
//					if (random.nextBoolean()) {
//						addBlockQueue(p);
//						break;
//					}
//				}
//				timeFlag += time;
//				System.out.println("���� "+p.name+" ���ĵ�ʱ��ƬΪ: "+time);
//			} else {
				Process p = removeReadyQueue();
				while(true) {
					p.call();
					time++;
					if (p.RTime <= 0) {
						p.timeOut();
						addDieQueue(p);
						break;
					}
//					Random random = new Random();
//					if (random.nextBoolean()) {
//						addBlockQueue(p);
//						break;
//					}
				}
				int n=timeFlag+time;
				System.out.println(timeFlag+"--���� "+p.name+" --> "+n);
				timeFlag += time;
				
//			}
//			for (int i = blockQueue.size(); i >0 ; i--) {//������������
//				p = removeBlockQueue();
//				Random random = new Random();
//				if (random.nextBoolean()) {
//					p.active();
//					addReadyQueue(p);
//				} else {
//					p.block();
//					addBlockQueue(p);
//				}
//			}
//			printState();
		}
		System.out.println("���н�������ʱ��Ƭ: "+timeFlag);
	}
	/*
	 * ����ҵ����
	 */
	public void SJF() {
		sortT(readyQueue);
		int timeFlag = 0;
		while (!readyQueue.isEmpty() || !blockQueue.isEmpty()) {
			int time = 0;
//			if (readyQueue.isEmpty()) {// ����������пգ�����һ����������
//				Process p = removeBlockQueue();
//				p.active();
//				while(true) {
//					p.call();
//					time++;
//					if (p.RTime <= 0) {
//						p.timeOut();
//						addDieQueue(p);
//						break;
//					}
//					Random random = new Random();
//					if (random.nextBoolean()) {
//						addBlockQueue(p);
//						break;
//					}
//				}
//				timeFlag += time;
//				System.out.println("���� "+p.name+" ���ĵ�ʱ��ƬΪ: "+time);
//			} else {
				Process p = removeReadyQueue();
				while(true) {
					p.call();
					time++;
					if (p.RTime <= 0) {
						p.timeOut();
						addDieQueue(p);
						break;
					}
//					Random random = new Random();
//					if (random.nextBoolean()) {
//						addBlockQueue(p);
//						break;
//					}
				}
				int n=timeFlag+time;
				System.out.println(timeFlag+"--���� "+p.name+" --> "+n);
				timeFlag += time;
//			}
//			for (int i = blockQueue.size(); i >0 ; i--) {//������������
//				Process p = removeBlockQueue();
//				Random random = new Random();
//				if (random.nextBoolean()) {
//					p.active();
//					addReadyQueue(p);
//				} else {
//					p.block();
//					addBlockQueue(p);
//				}
//			}
			sortT(readyQueue);
			sortT(blockQueue);
//			printState();
		}
		System.out.println("���н�������ʱ��Ƭ: "+timeFlag);
	}
	/*
	 * ����Ȩ�㷨
	 */
	public void PSA() {
		sortP(readyQueue);
		int timeFlag = 0;
		while (!readyQueue.isEmpty() || !blockQueue.isEmpty()) {
			int time = 0;
//			if (readyQueue.isEmpty()) {// ����������пգ�����һ����������
//				Process p = removeBlockQueue();
//				p.active();
//				while(true) {
//					p.call();
//					time++;
//					if (p.RTime <= 0) {
//						p.timeOut();
//						addDieQueue(p);
//						break;
//					}
//					Random random = new Random();
//					if (random.nextBoolean()) {
//						addBlockQueue(p);
//						break;
//					}
//				}
//				timeFlag += time;
//				System.out.println("���� "+p.name+" ���ĵ�ʱ��ƬΪ: "+time);
//			} else {
				Process p = removeReadyQueue();
				while(true) {
					p.call();
					time++;
					if (p.RTime <= 0) {
						p.timeOut();
						addDieQueue(p);
						break;
					}
//					Random random = new Random();
//					if (random.nextBoolean()) {
//						addBlockQueue(p);
//						break;
//					}
				}
				int n=timeFlag+time;
				System.out.println(timeFlag+"--���� "+p.name+" --> "+n);
				timeFlag += time;
//			}
//			for (int i = blockQueue.size(); i >0 ; i--) {//������������
//				Process p = removeBlockQueue();
//				Random random = new Random();
//				if (random.nextBoolean()) {
//					p.active();
//					addReadyQueue(p);
//				} else {
//					p.block();
//					addBlockQueue(p);
//				}
//			}
			sortP(readyQueue);
			sortP(blockQueue);
//			printState();
		}
		System.out.println("���н�������ʱ��Ƭ: "+timeFlag);
	}
	/*
	 * ����ʱ��Ƭ�Ķ༶���������㷨
	 */
	public void MFQ() {
		TRQFlag trq= new TRQFlag();
		Queue<Process> readyQueue1 = new ArrayBlockingQueue(100);
		Queue<Process> readyQueue2 = new ArrayBlockingQueue(100);
		Queue<Process> readyQueue3 = new ArrayBlockingQueue(100);
		while(readyQueue1.isEmpty()) {
			trq.timeFlag++;
			addRQ(trq.timeFlag,readyQueue1);
		}
		while(!readyQueue1.isEmpty()||!readyQueue2.isEmpty()||!readyQueue3.isEmpty()||!timeQueue.isEmpty()) {
			Q1(readyQueue1,readyQueue2,trq);
			Q2(readyQueue1,readyQueue2,readyQueue3,trq);
			Q3(readyQueue1,readyQueue2,readyQueue3,trq);
		}
		System.out.println("���н�������ʱ��Ƭ: "+trq.timeFlag);
	}
	public TRQFlag Q1(Queue<Process> readyQueue1,Queue<Process> readyQueue2,TRQFlag trq) {
		while(!readyQueue1.isEmpty()) {
			int time=0;
			Process p = readyQueue1.remove();
			for(int j=0;j<2;j++) {
				p.call();
				time++;
				addRQ(time+trq.timeFlag,readyQueue1);
				if (p.RTime <= 0) {
					p.timeOut();
					addDieQueue(p);
					break;
				}
			}
			if(p.RTime>0 && p.state==Process.State.RUNNING) {
				readyQueue2.add(p);
			}
			int n=trq.timeFlag+time;
			System.out.println(trq.timeFlag+"--���� "+p.name+" --> "+n);
			trq.timeFlag+=time;
			printStateMFQ(readyQueue1,readyQueue2,readyQueue3);
			System.out.println("");
		}
		return trq;
	}
	public TRQFlag Q2(Queue<Process> readyQueue1,Queue<Process> readyQueue2,Queue<Process> readyQueue3,TRQFlag trq) {
		while(!readyQueue2.isEmpty()) {
			int time =0;
			Process p = readyQueue2.remove();
			for(int j=0;j<4;j++) {
				p.call();
				time++;
				addRQ(time+trq.timeFlag,readyQueue1);
				
				if (p.RTime <= 0) {
					p.timeOut();
					addDieQueue(p);
					break;
				}
			}
			if(p.RTime>0 && p.state==Process.State.RUNNING) {
				readyQueue3.add(p);
			}
			if(!readyQueue1.isEmpty()) {
				int n=trq.timeFlag+time;
				System.out.println(trq.timeFlag+"--���� "+p.name+" --> "+n);
				trq.timeFlag+=time;
				trq.flag=true;
				printStateMFQ(readyQueue1,readyQueue2,readyQueue3);
				System.out.println("");
				return trq;
			}
			int n=trq.timeFlag+time;
			System.out.println(trq.timeFlag+"--���� "+p.name+" --> "+n);
			trq.timeFlag+=time;
			
		}
		printStateMFQ(readyQueue1,readyQueue2,readyQueue3);
		System.out.println("");
		return trq;	
	}
		
	public TRQFlag Q3(Queue<Process> readyQueue1,Queue<Process> readyQueue2,Queue<Process> readyQueue3,TRQFlag trq) {
		while(!readyQueue3.isEmpty()) {
			int time=0;
			Process p;
			p = readyQueue3.remove();
			for(int j=0;j<6;j++) {
				p.call();
				time++;
				addRQ(time+trq.timeFlag,readyQueue1);
				
				if (p.RTime <= 0) {
					p.timeOut();
					addDieQueue(p);
					break;
				}
			}
			if(p.RTime>0) {
				readyQueue3.add(p);
			}
			if(!readyQueue1.isEmpty()) {
				int n=trq.timeFlag+time;
				System.out.println(trq.timeFlag+"--���� "+p.name+" --> "+n);
				trq.timeFlag+=time;
				trq.flag=true;
				return trq;
			}
			int n=trq.timeFlag+time;
			System.out.println(trq.timeFlag+"--���� "+p.name+" --> "+n);
			trq.timeFlag+=time;
		}
		printStateMFQ(readyQueue1,readyQueue2,readyQueue3);
		System.out.println("");
		return trq;
	}

}
