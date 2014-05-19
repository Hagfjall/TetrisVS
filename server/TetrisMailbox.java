package server;

public class TetrisMailbox {

	private Input input;

	public synchronized void insert(Input in) {
		while (input != null) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		input = in;
		notifyAll();
	}

	public synchronized Input get() {
		while (input == null) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		Input ret = input;
		input = null;
		notifyAll();
		return ret;
	}

}
