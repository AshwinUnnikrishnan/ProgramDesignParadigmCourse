package solution.bst;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * This class represents a binary search tree. It implements the BSTADT
 * interface
 */
public class BSTImpl<T extends Comparable<T>>
        implements BST<T> {

  /**
   * New generic class which is an inner helper class in ListADTImpl .
   * It stores two fields: one of type S and another of type T.
   *
   * @param <S> First part of the pair
   * @param <T> Second part of the pair storing the next address
   */
  public class Pair<S, T> {
    S sObject;
    T tObject;

    public Pair(S s, T t) {
      this.sObject = s;
      this.tObject = t;
    }
  }

  private BSTNode<T> root;

  public BSTImpl() {
    root = new BSTEmptyNode<T>(); //no tree
  }

  @Override
  public int size() {
    LinkedList<Pair<String, BSTNode<T>>> tS = new LinkedList<>();
    LinkedList<Integer> rS = new LinkedList<>();

    tS.push(new Pair<>("recur", root));
    while (!tS.isEmpty()) {
      Pair temp = tS.pop();
      if (temp.sObject.equals("recur")) {
        if (temp.tObject instanceof BSTEmptyNode) {
          //simply process this node, no recursive call
          tS.push(new Pair<>("process", (BSTEmptyNode) temp.tObject));
        }
        if (temp.tObject instanceof BSTElementNode) {
          //first make the recursive call, then process this node (add 1 to result)
          //added in reverse order so that they come out of stack in correct order
          tS.push(new Pair<>("process", (BSTNode<T>) temp.tObject));
          tS.push(new Pair<>("recur", ((BSTElementNode) temp.tObject).getRight()));
          tS.push(new Pair<>("recur", ((BSTElementNode) temp.tObject).getLeft()));
        }
      }
      if (temp.sObject.equals("process")) {
        if (temp.tObject instanceof BSTEmptyNode) {
          //an empty node simply returns 0, as per above implementation
          rS.push(0);
        }
        if (temp.tObject instanceof BSTElementNode) {
          //add 1 to the "last result", as per above implementation
          int num1 = rS.pop();
          int num2 = rS.pop();
          rS.push(1 + num1 + num2);
        }
      }
    }
    return rS.pop(); //the last thing remaining is the final result
  }

  @Override
  public void insert(T data) {
    root = root.insert(data);
  }

  @Override
  public boolean present(T data) {
    return root.contains(data);
  }

  @Override
  public T minimum() {
    return root.minimum();
  }

  @Override
  public T maximum() {
    return root.maximum();
  }

  public String toString() {
    return "[" + root.toString() + "]";
  }

  @Override
  public List<T> preorder() {
    List<T> result = new ArrayList<T>();


    LinkedList<Pair<String, BSTNode<T>>> tS = new LinkedList<>();

    tS.push(new Pair<>("recur", root));
    while (!tS.isEmpty()) {
      Pair temp = tS.pop();
      if (temp.sObject.equals("recur")) {
        if (temp.tObject instanceof BSTEmptyNode) {
          //simply process this node, no recursive call
          tS.push(new Pair<>("process", (BSTEmptyNode) temp.tObject));
        }
        if (temp.tObject instanceof BSTElementNode) {
          result.add((T) ((BSTElementNode) temp.tObject).getData());
          tS.push(new Pair<>("recur", ((BSTElementNode) temp.tObject).getRight()));
          tS.push(new Pair<>("recur", ((BSTElementNode) temp.tObject).getLeft()));
        }
      }
    }
    return result;
  }

  @Override
  public List<T> inorder() {
    List<T> result = new ArrayList<T>();

    LinkedList<Pair<String, BSTNode<T>>> tS = new LinkedList<>();

    tS.push(new Pair<>("recur", root));
    while (!tS.isEmpty()) {
      Pair temp = tS.pop();
      if (temp.sObject.equals("recur")) {
        if (temp.tObject instanceof BSTEmptyNode) {
          //simply process this node, no recursive call
          tS.push(new Pair<>("process", (BSTEmptyNode) temp.tObject));
        }
        if (temp.tObject instanceof BSTElementNode) {
          //first make the recursive call, then process this node (add 1 to result)
          //added in reverse order so that they come out of stack in correct order
          tS.push(new Pair<>("recur", ((BSTElementNode) temp.tObject).getRight()));
          tS.push(new Pair<>("process", (BSTNode<T>) temp.tObject));
          tS.push(new Pair<>("recur", ((BSTElementNode) temp.tObject).getLeft()));
        }
      }
      if (temp.sObject.equals("process")) {
        if (temp.tObject instanceof BSTElementNode) {
          //add 1 to the "last result", as per above implementation
          result.add((T) ((BSTElementNode) temp.tObject).getData());
        }
      }
    }
    return result;
  }

  @Override
  public List<T> postorder() {
    List<T> result = new ArrayList<T>();

    LinkedList<Pair<String, BSTNode<T>>> tS = new LinkedList<>();

    tS.push(new Pair<>("recur", root));
    while (!tS.isEmpty()) {
      Pair temp = tS.pop();
      if (temp.sObject.equals("recur")) {
        if (temp.tObject instanceof BSTEmptyNode) {
          //simply process this node, no recursive call
          tS.push(new Pair<>("process", (BSTEmptyNode) temp.tObject));
        }
        if (temp.tObject instanceof BSTElementNode) {
          //first make the recursive call, then process this node (add 1 to result)
          //added in reverse order so that they come out of stack in correct order
          tS.push(new Pair<>("process", (BSTNode<T>) temp.tObject));
          tS.push(new Pair<>("recur", ((BSTElementNode) temp.tObject).getRight()));
          tS.push(new Pair<>("recur", ((BSTElementNode) temp.tObject).getLeft()));

        }
      }
      if (temp.sObject.equals("process")) {
        if (temp.tObject instanceof BSTElementNode) {
          //add 1 to the "last result", as per above implementation
          result.add((T) ((BSTElementNode) temp.tObject).getData());
        }
      }
    }
    return result;
  }


}
