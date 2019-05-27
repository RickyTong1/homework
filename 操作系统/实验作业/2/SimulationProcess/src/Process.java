
public class Process {

	int id;//��ʶ��
	String name;//����
	enum State{READY,RUNNING,BLOCK,DIE}
	State state;//״̬
	int time;//��Ҫ��ʱ��Ƭ
	int priority;//���ȼ�
	/**
	 * ��������
	 */
	public Process(int id, String name, int time, int priority) {
		this.id = id;
		this.name = name;
		this.time = time;
		this.priority = priority;
		state = State.READY;
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
		time--;
		priority--;
	}
	/**
	 * ��������
	 */
	public void block() {
		state = State.BLOCK;
		priority++;
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
