/**
 * Created by histravelstories
 * This is a class
 * Date : 9/20/22
 * Project Name : testEnv
 */

public class SimpleMoney implements Money{
  private final int cent;
  public SimpleMoney(int cent){
    this.cent=cent;
  }

  @Override
  public Money add(Money other){
    if (! (other instanceof SimpleMoney)){
      throw new IllegalArgumentException("Passed argument is not instance of simple Money");
    }
    SimpleMoney typeCasted = (SimpleMoney) other;
    return(new SimpleMoney(this.cent + typeCasted.cent));
  }
}

