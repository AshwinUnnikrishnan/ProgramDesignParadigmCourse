package model;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Class that is has functionalities to read a json file.
 */

public class JsonReader implements DatabaseReader {
  private final String fileName;

  /**
   * Constructor that takes the filename, from which we need to read the json.
   *
   * @param fileName : json file from where we want to read.
   */
  public JsonReader(String fileName) {
    this.fileName = fileName;
  }


  @Override
  public String readUserDataBase(String name) throws FileNotFoundException {
    Scanner scan = new Scanner(new FileReader(this.fileName));
    String regex = "^\"(" + name + ")\" :\\{\"Portfolio\":(.*)}$";
    Pattern pattern = Pattern.compile(regex);
    Matcher matcher;
    String res = null;
    while (scan.hasNext()) {
      String temp = scan.nextLine();
      matcher = pattern.matcher(temp);
      if (matcher.matches()) {
        res = matcher.group(2);
      }
    }
    return res;
  }
}
