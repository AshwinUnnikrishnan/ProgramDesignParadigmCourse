package solution.bst;

import java.util.List;

/**
 * This represents the operations on all nodes of a binary search tree.
 */
public interface BSTNode<T extends Comparable<T>> {
  /**
   * The number of objects in the tree rooted at this node.
   *
   * @return the number of objects in the tree rooted at this node
   */

  int count();

  /**
   * Inserts new data into the tree rooted at this node, and return the
   * resulting tree.
   *
   * @param data data to be inserted.
   * @return
   */
  BSTNode<T> insert(T data);

  /**
   * Determine and return the minimum element in the tree rooted at this node.
   *
   * @return
   */

  T minimum();

  /**
   * Determine and return the maximum element in the tree rooted at this node.
   *
   * @return
   */
  T maximum();

  /**
   * Search to see if the specific data is present in the tree rooted at this
   * node.
   *
   * @param data data to be searched
   * @return true if data is present in the tree, false otherwise
   */
  boolean contains(T data);

  /**
   * Returns a string containing all the data in the tree rooted at this node.
   * The string is formatted as d1 d2 ... dn
   *
   * @return
   */
  String toString();

  /**
   * Add elements in this tree in (left->right) pre-order traversal order to the given list.
   *
   * @param result the list which accumulates the result
   */
  void preorder(List<T> result);

  /**
   * Add elements in this tree in (left->right) in-order traversal order to the given list.
   *
   * @param result the list which accumulates the result
   */
  void inorder(List<T> result);

  /**
   * Add elements in this tree in (left->right) post-order traversal order to the given list.
   *
   * @param result the list which accumulates the result
   */
  void postorder(List<T> result);
}
