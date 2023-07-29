/**
 * Created by histravelstories.
 * This is a class
 * Date : 11/14/22
 * Project Name : lab7
 */

package betterpizza;

import pizza.Crust;
import pizza.Size;
import pizza.ToppingName;
import pizza.ToppingPortion;

/**
 * Abstract representation of a pizzabuilder class.
 *
 * @param <T> Pizza type.
 */
abstract class PizzaBuilder<T> {


  /**
   * Method to set crust type.
   *
   * @param crust type of the crust.
   * @return Pizza with the crust set.
   */
  public abstract T crust(Crust crust);

  /**
   * Method to set size of pizza.
   *
   * @param size type of the crust.
   * @return Pizza with the size set.
   */
  public abstract T size(Size size);

  /**
   * Method to add toppings to pizza.
   *
   * @param name    Name of the topping
   * @param portion Portion of topping
   * @return Pizza with toppings added
   */
  public abstract T addTopping(ToppingName name, ToppingPortion portion);

  /**
   * Method to build the pizza.
   *
   * @return the final pizza.
   */
  public abstract ObservablePizza build();

  /**
   * Remove the topping from pizza.
   *
   * @param name name of the topping to be removed
   * @return Pizza after the topping removed if the topping present.
   */
  public abstract T removeTopping(ToppingName name);

}
