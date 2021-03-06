import java.io.IOException;
import java.util.Observable;

public class CommandListenerThread extends Observable implements Runnable {
	private Connection con;
	private volatile Command lastCommand;
	private volatile boolean disconnected;

	public CommandListenerThread(Connection con) {
		this.con = con;
	}

	public Command getLastCommand() {
		return lastCommand;
	}

	@Override
	public void run() {
		this.addObserver(MainForm.obj);
		while (isDisconnected() != true) {
			synchronized (this) {
				try {
					this.lastCommand = con.receive();
					 setChanged();
					this.notifyObservers(lastCommand);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}

	}

	void start() {
		this.disconnected = false;
		Thread t = new Thread(this);
		t.start();
	}

	boolean isDisconnected() {
		return disconnected;
	}

	void stop() {
		disconnected = true;
	}
	public Connection getConnection(){
		return this.con;
	}
}
