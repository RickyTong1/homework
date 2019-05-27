
public class Process {

	int id;//标识符
	String name;//名称
	enum State{READY,RUNNING,BLOCK,DIE}
	State state;//状态
	int time;//需要的时间片
	int priority;//优先级
	/**
	 * 创建进程
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
	 * 删除进程
	 */
	public void delete() {
		id = 0;
		name = null;
		state = null;
		time = 0;
		priority = 0;
	}
	/**
	 * 调度进程
	 */
	public void call() {
		state = State.RUNNING;
		time--;
		priority--;
	}
	/**
	 * 阻塞进程
	 */
	public void block() {
		state = State.BLOCK;
		priority++;
	}
	/**
	 * 时间到
	 */
	public void timeOut() {
		state = State.DIE;
	}
	/**
	 * 激活
	 */
	public void active() {
		state = State.READY;
	}
}
