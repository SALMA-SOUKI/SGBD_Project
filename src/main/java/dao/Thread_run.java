package dao;

import java.io.IOException;

public class Thread_run extends Thread{
	Thread_f th;
	File_c f;

	public Thread_run(Thread_f th,File_c f) {
		super();
		this.th = th;
		this.f = f;
	}
	
	public void run() {
		try {
			th.enter_file(f);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
