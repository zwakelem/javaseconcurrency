package za.co.simplitate.runnables;

import za.co.simplitate.beans.User;
import za.co.simplitate.dao.UserDao;

import java.util.StringTokenizer;
import java.util.concurrent.Callable;

public class UserProcessor implements Callable<Integer> {

  private String userRecord;

  private UserDao dao;

  public UserProcessor(String userRecord, UserDao dao) {
    this.userRecord = userRecord;
    this.dao = dao;
  }

  @Override
  public Integer call() throws Exception {
    int rows = 0;
    System.out.println(Thread.currentThread().getName() + " processing " + userRecord);
    StringTokenizer tokenizer = new StringTokenizer(userRecord, ",");
    User user = null;
    while(tokenizer.hasMoreTokens()) {
      user = new User();
      user.setEmail(tokenizer.nextToken());
      user.setName(tokenizer.nextToken());
      user.setId(Integer.valueOf(tokenizer.nextToken()));
      rows = dao.saveUser(user);
    }
    return rows;
  }

}
