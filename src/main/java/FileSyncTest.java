import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileSyncTest {
    private static final Object lock = new Object();

    public static void main(String[] args) {
        FileSyncTest test = new FileSyncTest();

        // create two threads that will write to the file concurrently
        Thread t1 = new Thread(() -> test.writeToFile1("thread 1", "data 1"));
        Thread t2 = new Thread(() -> test.writeToFile2("thread 2", "data 2"));

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void writeToFile1(String threadName, String data) {
        synchronized(lock) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("c:\\\\\\\\nouhaila.txt", true))) {
                writer.write(threadName + ": " + data);
                writer.newLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void writeToFile2(String threadName, String data) {
        synchronized(lock) {
            /*try (BufferedWriter writer = new BufferedWriter(new FileWriter("file.txt", true))) {
                writer.write(threadName + ": " + data);
                writer.newLine();
            } catch (IOException e) {
                e.printStackTrace();
            }*/
        	
        	try (BufferedReader reader = new BufferedReader(new FileReader("c:\\\\nouhaila.txt"))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    // process the line
                	System.out.println(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
