package lab_6;

import java.util.concurrent.ThreadLocalRandom;

public class Main {

    static ProgramState state = ProgramState.UNKNOWN;
    static Thread abstractThread = new Thread(new Program());
    final static Object object = new Object();
    
    public static class Program implements Runnable {
    	
   	 	private boolean isActive;
   	 	
   	 	Program(){
   	 		isActive = true;
   	 	}
        
   	 	void disable(){
   	 		isActive = false;
   	 	}
   	
   	 	@Override
   	 	public void run() {
   	 		System.out.println("Program: started");
   	 		Thread daemon = new Thread(() -> {
   	 			System.out.println("Daemon: started");
   	 			while (isActive) {
   	 				try {
   	 					Thread.sleep(500);
   	 				} catch (InterruptedException e) {
   	 					e.printStackTrace();
   	 					disable();
   	 					break;
   	 				}
   	 				synchronized (object) {
   	 					if (state == ProgramState.UNKNOWN) {
   	 						System.out.println("Daemon: state of program - " + state.toString());
	 						object.notify();
   	 					} else {
   	 					state = ProgramState.values()[ThreadLocalRandom.current().nextInt(1, ProgramState.values().length)];
						System.out.println("Daemon: changing state of program on " + state.toString());
						object.notify();
   	 					}
   	 				}
   	 			}
   	 		});
   	 		daemon.setDaemon(true);
   	 		daemon.start();
   	 	}
    }
    
   

    public static class Supervisor implements Runnable {
        @Override
        public void run() {
            System.out.println("Supervisor: started");
            abstractThread.start();
            while (!abstractThread.isInterrupted()) {
                synchronized (object) {
                    try {
                        object.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        break;
                    }
                    switch (state) {
                    	case UNKNOWN:
                    		System.out.println("Supervisor: state - UNKNOWN -> starting program");
                    		state = ProgramState.values()[ThreadLocalRandom.current().nextInt(1, ProgramState.values().length)];
                    		break;
                        case FATAL_ERROR:
                        	System.out.println("Supervisor: state - FATAL_ERROR -> stopping program");
                            abstractThread.interrupt();
                        	break;
                        case STOPPING:
                        	System.out.println("Supervisor: state - STOPPING -> restating program");
                            state = ProgramState.RUNNING;
                        	break;
                        default:
                        	System.out.println("Supervisor: state - RUNNING -> running program");
                    }
                }
            }
        }
    }

    
    public static void main(String[] args) {
    	Runnable runnable = new Supervisor();
        Thread supervisorThread = new Thread(runnable);
        supervisorThread.start();
    }
}
