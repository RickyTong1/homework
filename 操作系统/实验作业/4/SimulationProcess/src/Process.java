
public class Process {

	int id;//��ʶ��
	String name;//����
	enum State{READY,RUNNING,BLOCK,DIE}
	State state;//״̬
	int time;//�ܹ���Ҫ��ʱ��Ƭ
	int RTime;//����ִ�е�ʱ��Ƭ
	int priority;//���ȼ�
	int readyTime;//����ʱ��
	/**
	 * ��������
	 */
	public Process(int id, String name, int time, int priority,int readyTime) {
		this.id = id;
		this.name = name;
		this.time = time;
		this.priority = priority;
		state = State.READY;
		RTime=time;
		this.readyTime=readyTime;
	}
	public Process() {
	}
	/**
	 * ɾ������
	 */
	public void delete() {
		id = 0;
		name = null;
		state = null;
		time = 0;
		priority = 0;
	}
	/**
	 * ���Ƚ���
	 */
	public void call() {
		state = State.RUNNING;
		RTime--;
	}
	/**
	 * ��������
	 */
	public void block() {
		state = State.BLOCK;
	}
	/**
	 * ʱ�䵽
	 */
	public void timeOut() {
		state = State.DIE;
	}
	/**
	 * ����
	 */
	public void active() {
		state = State.READY;
	}
}
