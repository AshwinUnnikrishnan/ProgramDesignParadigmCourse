package solution.bst;

import java.util.List;

/**
 * This class represents a data-containing node of the binary search tree
 * It mutates on all relevant operations.
 */
public class BSTElementNode<T extends Comparable<T>> implements BSTNode<T> {
  private BSTNode<T> left;
  private BSTNode<T> right;
  private T data;

  /**
   * Constructor for the class that takes in data, left and right node references.
   *
   * @param data  data of the node
   * @param left  left side
   * @param right right side
   */
  public BSTElementNode(T data, BSTNode<T> left, BSTNode<T> right) {
    this.data = data;
    this.left = left;
    this.right = right;
  }

  @Override
  public int count() {
    return 1 + this.left.count() + this.right.count();
  }

  @Override
  public BSTNode insert(T data) {
    if (data.compareTo(this.data) < 0) {
      this.left = this.left.insert(data);
    } else if (data.compareTo(this.data) > 0) {
      this.right = this.right.insert(data);
    }
    return this;
  }

  @Override
  public T minimum() {
    T minimum;

    minimum = this.left.minimum();
    if (minimum == null) {
      minimum = this.data;
    }
    return minimum;
  }

  @Override
  public T maximum() {
    T maximum;

    maximum = this.right.maximum();
    if (maximum == null) {
      maximum = this.data;
    }
    return maximum;
  }

  @Override
  public boolean contains(T data) {
    int compareResult = data.compareTo(this.data);

    if (compareResult == 0) {
      return true;
    } else if (compareResult < 0) {
      return this.left.contains(data);
    } else {
      return this.right.contains(data);
    }
  }

  @Override
  public String toString() {
    String left;
    String right;
    String middle;

    middle = this.data.toString();
    left = this.left.toString();
    right = this.right.toString();
    if (left.length() > 0) {
      left = left + " ";
    }
    if (right.length() > 0) {
      right = " " + right;
    }
    return left + middle + right;
  }

  @Override
  public void preorder(List<T> result) {
    result.add(this.data);
    this.left.preorder(result);
    this.right.preorder(result);
  }

  @Override
  public void inorder(List<T> result) {
    this.left.inorder(result);
    result.add(this.data);
    this.right.inorder(result);
  }

  @Override
  public void postorder(List<T> result) {
    this.left.postorder(result);
    this.right.postorder(result);
    result.add(this.data);
  }

  /**
   * getter function to get object.
   *
   * @return object of this node
   */
  public T getData() {
    return this.data;
  }

  /**
   * method to get link to left of the tree.
   *
   * @return rest object of this class.
   */
  public BSTNode<T> getLeft() {
    return this.left;
  }

  /**
   * method to get link to right of the tree.
   *
   * @return rest object of this class.
   */
  public BSTNode<T> getRight() {
    return this.right;
  }
}
