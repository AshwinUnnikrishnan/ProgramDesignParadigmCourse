import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import solution.bst.BSTImpl;
import solution.bst.BST;

import static org.junit.Assert.assertEquals;

/**
 * Created by ashesh on 3/2/2017.
 */
public class BinarySearchTreeTest {

  @Test
  public void testInsertions() {
    BST<Integer> tree = new BSTImpl<Integer>();
    Set<Integer> expected = new TreeSet<Integer>();

    for (int i = 0; i < 1000; i++) {
      expected.add((int) (Math.random() * 2000 - 1000));
    }

    for (Integer e : expected) {
      tree.insert(e);
    }

    StringBuilder sb = new StringBuilder();
    for (Integer e : expected) {
      sb.append(e + " ");

    }
    String output = sb.toString();
    output = "[" + output.substring(0, output.length() - 1) + "]";


    assertEquals(output, tree.toString());
    assertEquals(expected.size(), tree.size());
  }

  @Test
  public void testMinMax() {
    BST<Integer> tree = new BSTImpl<Integer>();
    TreeSet<Integer> expected = new TreeSet<Integer>();

    for (int i = 0; i < 1000; i++) {
      expected.add((int) (Math.random() * 2000 - 1000));
    }

    for (Integer e : expected) {
      tree.insert(e);
    }


    assertEquals(expected.first(), tree.minimum());
    assertEquals(expected.last(), tree.maximum());
  }

  @Test
  public void testContains() {
    BST<Integer> tree = new BSTImpl<Integer>();
    List<Integer> expected = new ArrayList<Integer>();

    for (int i = 0; i < 1000; i++) {
      expected.add((int) (Math.random() * 2000 - 1000));
    }

    for (Integer e : expected) {
      tree.insert(e);
    }


    for (int i = -1000; i <= 1000; i++) {
      assertEquals(expected.contains(i), tree.present(i));
    }
  }

  @Test
  public void testTraversals() {
    BST<Integer> tree = new BSTImpl<Integer>();
    Integer[] input = {5, 1, 8, 3, 7, 6};
    Integer[] pre = {5, 1, 3, 8, 7, 6};
    Integer[] in = {1, 3, 5, 6, 7, 8};
    Integer[] post = {3, 1, 6, 7, 8, 5};


    for (Integer e : input) {
      tree.insert(e);
    }

    assertEquals(Arrays.asList(pre), tree.preorder());
    assertEquals(Arrays.asList(in), tree.inorder());
    assertEquals(Arrays.asList(post), tree.postorder());


  }

}