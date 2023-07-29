/**
 * Created by histravelstories.
 * This is an interface
 * Date : 11/14/22
 * Project Name : lab7
 */

package betterpizza;

import pizza.ToppingName;
import pizza.ToppingPortion;

/**
 * Interface to make pizza immutable. This interface will contain only those methods from the Pizza
 * interface that do not mutate (cost and hasTopping)
 */
public interface ObservablePizza {

  /**
   * Get the cost of this pizza.
   *
   * @return the cost of this pizza in MM.CC format
   */
  double cost();


  /**
   * Determines if the specified topping is on this pizza and if so, return its portion.
   *
   * @param name the name of the topping
   * @return the portion of this topping on this pizza, or null if the given topping is not present
   */
  ToppingPortion hasTopping(ToppingName name);

}
