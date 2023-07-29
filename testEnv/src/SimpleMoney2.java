/**
 * Created by histravelstories
 * This is a class
 * Date : 9/20/22
 * Project Name : testEnv
 */

public class SimpleMoney2 implements Money{
  private final int cent;
  public SimpleMoney2(int cent){
    this.cent=cent;
  }

  @Override
  public Money add(Money other){
    if (! (other instanceof SimpleMoney2)){
      throw new IllegalArgumentException("Passed argument is not instance of simple Money");
    }
    SimpleMoney2 typeCasted = (SimpleMoney2) other;
    return(new SimpleMoney(this.cent + typeCasted.cent));
  }
}
