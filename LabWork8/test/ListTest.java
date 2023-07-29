import org.junit.Assert;
import org.junit.Test;

/**
 * Class to test working of mutablelistadt.
 */
public class ListTest {


  @Test
  public void testFunc() {

    MutableListADT<Integer> mutADLList = new MutableListADTImpl<>();


    mutADLList.addBack(9);
    mutADLList.addBack(7);
    mutADLList.addBack(4);
    mutADLList.addBack(1);

    ImmutableListADT<Integer> immutADLList = mutADLList.getImmutableList();
    MutableListADT<Integer> mutableListADTNew = immutADLList.getMutableList();


    Assert.assertEquals("(9 7 4 1)", mutADLList.toString());
    Assert.assertEquals("(9 7 4 1)", immutADLList.toString());

    Assert.assertEquals(7, (int) immutADLList.get(1));
    Assert.assertEquals(4, immutADLList.getSize());

    Assert.assertEquals("(9 7 4 1)", mutableListADTNew.toString());
    mutableListADTNew.addBack(10);

    Assert.assertEquals("(9 7 4 1 10)", mutableListADTNew.toString());
    Assert.assertEquals("(9 7 4 1)", immutADLList.toString());
    Assert.assertEquals("(9 7 4 1)", mutADLList.toString());

  }

  @Test
  public void testPartThree() {

    ListADT<String> stringList = new ListADTImpl<>();

    String str = "Historical sites are awesome to walk through";
    String[] nodes = str.split("\\s+");
    for (int i = 0; i < nodes.length; i++) {
      stringList.addBack(nodes[i]);
    }

    CommonListADT<Object> wLength = stringList.map(s -> s.length());
    Assert.assertEquals(stringList.getSize(), wLength.getSize());

    for (int i = 0; i < nodes.length; i++) {
      Assert.assertEquals(nodes[i].length(), (int) wLength.get(i));
    }
  }
}
