/**
 * Created by Nagy Dóra on 2017.04.06..
 */
public class ArgumentHandler {
  String[] args;

  public ArgumentHandler(String[] args) {
    this.args = args;
  }

  public boolean contains(String argument) {
    if (args.length > 0) {
      for (String arg : args) {
        if (arg.startsWith("-") && arg.contains(argument))
          return true;
      }
    }
    return false;
  }
}
