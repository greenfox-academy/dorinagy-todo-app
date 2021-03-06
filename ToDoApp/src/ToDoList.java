import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nagy Dóra on 2017.04.06..
 */
public class ToDoList {
  String[] args;

  public ToDoList(String[] args) {
    this.args = args;
  }

  public void getToDo() {
    try {
      Path filePath = Paths.get("../files/data.txt");
      List<String> lines = Files.readAllLines(filePath);
      List<String> myList = new ArrayList<>();
      if (lines.isEmpty()) {
        System.out.println("No todos for today! :)");
      } else {
        for (String line : lines) {
          myList.add(line);
        }
        int number = 1;
        for (String line : myList) {
          System.out.println(number + " - " + line);
          number++;
        }
      }
    } catch (Exception e) {
      System.out.println("Error" + e.getClass());
    }
  }

  public void addToDo() {
    if (args.length == 1) {
      System.out.println("Unable to add: no task provided");
    } else {
      Path filePath = Paths.get("../files/data.txt");
      List<String> newToDo;
      try {
        newToDo = Files.readAllLines(filePath);
        newToDo.add(newToDo.size(), "[ ] " + args[1]);
        Files.write(filePath, newToDo);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  public void removeToDo() {
    try {
      Path filePath = Paths.get("../files/data.txt");
      List<String> removeToDo;
      removeToDo = Files.readAllLines(filePath);
      if (args.length == 1) {
        System.out.println("Unable to remove: no index provided");
      } else if (Integer.parseInt(args[1]) > removeToDo.size()) {
        System.out.println("Unable to remove: index is out of bound");
      } else {
        removeToDo.remove(Integer.parseInt(args[1]) - 1);
        Files.write(filePath, removeToDo);
      }
    } catch (IOException e) {
      e.printStackTrace();
    } catch (Exception ex) {
      System.out.println("Unable to remove: index is not a number");
    }
  }

  public void checkTasks() {
    try {
      Path filePath = Paths.get("../files/data.txt");
      List<String> checkTask;
      checkTask = Files.readAllLines(filePath);
      String chosenTask;
      if (args.length == 1) {
        System.out.println("Unable to check: no index provided");
      } else if (Integer.parseInt(args[1]) > checkTask.size()) {
        System.out.println("Unable to check: index is out of bound");
      } else {
        chosenTask = checkTask.get(Integer.parseInt(args[1]) - 1);
        String checkedTask = chosenTask.replaceFirst(" ", "x");
        checkTask.set((Integer.parseInt(args[1]) - 1), checkedTask);
        Files.write(filePath, checkTask);
      }
    } catch (IOException e) {
      e.printStackTrace();
    } catch (Exception ex) {
      System.out.println("Unable to check: index is not a number");
    }
  }
}
