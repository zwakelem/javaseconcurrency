package za.co.simplitate.runnables;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class AppThread extends Thread {

  @Override public void run() {

    try(BufferedReader reader = new BufferedReader(new FileReader(new File("src/main/resources/sample.txt")))) {
      String line = null;
      while((line = reader.readLine()) != null) {
        System.out.println(Thread.currentThread().getName() + " reading line " + line);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

  }
}
