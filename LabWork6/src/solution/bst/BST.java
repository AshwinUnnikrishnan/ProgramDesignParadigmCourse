package solution.bst;

import java.util.List;

/**
 * This interface represents all the operations that a binary search tree
 * should support. It can store any type of data that implements the
 * Comparable interface
 */
public interface BST<T extends Comparable<T>> {
  /**
   * Returns the number of objects stored in this tree.
   * @return the number of objects stored in this tree
   */

  int size();
  /**
   * Add data to the binary search tree. This is ignored if the data item is
   * already present.
   * @param data the data to be added
   */

  void insert(T data);

  /**
   * Find if this data is present in the binary search tree.
   * @param data the data to be searched
   * @return true if the data is present, false otherwise
   */
  boolean present(T data);

  /**
   * Determine and return the minimum data in the tree as defined by its
   * ordering.
   * @return the minimum data if it exists, null otherwise
   */
  T minimum();

  /**
   * Determine and return the maximum data in the tree as defined by its
   * ordering.
   * @return the maximum data if it exists, null otherwise
   */
  T maximum();

  /**
   * Returns a string that present all the data in the tree, sorted in
   * ascending order. The string is formatted as [d1 d2 ... dn]
   * @return
   */

  String toString();

  /**
   * Return a list of the elements in this tree in (left->right) pre-order traversal order.
   * @return a list of elements in pre-order traversal order
   */
  List<T> preorder();

  /**
   * Return a list of the elements in this tree in (left->right) in-order traversal order.
   * @return a list of elements in in-order traversal order
   */
  List<T> inorder();

  /**
   * Return a list of the elements in this tree in (left->right) post-order traversal order.
   * @return a list of elements in post-order traversal order
   */
  List<T> postorder();
}
