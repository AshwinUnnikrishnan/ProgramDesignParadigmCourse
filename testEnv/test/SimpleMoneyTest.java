import org.junit.Test;

import static org.junit.Assert.*;


public class SimpleMoneyTest {

  @Test
  public void testInstace(){
    Money t = new SimpleMoney(2);
    Money t2 = new SimpleMoney(3);
    Money t3 = t.add(t2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testException(){
    Money t = new SimpleMoney(2);
    Money t2 = new SimpleMoney2(2);
    Money t3 = t.add(t2);
  }
}