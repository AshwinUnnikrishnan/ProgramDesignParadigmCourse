package bookutil;

import java.util.List;

/**
 * A class that contains several useful operations on lists of strings
 */
public class StringListUtils {

  public static<T> void swap(List<T> list, int index1, int index2) {
    T obj = list.get(index1);
    list.set(index1,list.get(index2));
    list.set(index2,obj);
  }

  public static String toString(List<String> list) {
    return toStringRec(list,0);
  }

  public static String toStringLoop(List<String> list) {
    String result="";
    for (String obj:list) {
      result = result + " (" + obj.toString() + ")";
    }
    return result.substring(1);
  }

  public static String toStringPos(List<String> list) {
    String result="";
    for (int index=0;index<list.size();index=index+1) {
      result = result + " (" + index+":"+list.get(index).toString() + ")";
    }
    return result.substring(1);
  }


  private static String toStringRec(List<String> list,int index) {


    if (index>=list.size()) {
      return "";
    }
    String rest = toStringRec(list,index+1);
    if (rest.length()>0) {
      return "("+list.get(index).toString()+")" + " " +rest;
    }
    else {
      return "("+list.get(index).toString()+")";
    }


  }
}
