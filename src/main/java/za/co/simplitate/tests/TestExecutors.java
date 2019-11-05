package za.co.simplitate.tests;

import za.co.simplitate.dao.UserDao;
import za.co.simplitate.runnables.UserProcessor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TestExecutors {

  public static void main(String[] args) {

    List<String> users = getUsersFromFile("src/main/resources/new_users.txt");
    ExecutorService service = Executors.newSingleThreadExecutor();
    UserDao dao  = new UserDao();

    for(String user : users) {
      Future<Integer> future = service.submit(new UserProcessor(user, dao));
      try {
        System.out.println("future " + future.get());
      } catch (InterruptedException e) {
        e.printStackTrace();
      } catch (ExecutionException e) {
        e.printStackTrace();
      }
    }
    service.shutdown();
    System.out.println("Main execution over!!");
  }

  public static List<String> getUsersFromFile(String fileName) {
    List<String> users = new ArrayList<>();
    try(BufferedReader reader = new BufferedReader(new FileReader(new File(fileName)))) {
      String line = null;
      while((line = reader.readLine()) != null) {
        users.add(line);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return users;
  }
}
