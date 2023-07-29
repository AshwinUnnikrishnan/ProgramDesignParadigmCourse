/**
 * Created by histravelstories.
 * This is a class
 * Date : 11/14/22
 * Project Name : lab7
 */

package betterpizza;

import java.util.Map;

import pizza.Crust;
import pizza.Size;
import pizza.ToppingName;
import pizza.ToppingPortion;

/**
 * This class represents a cheese pizza.
 */
public class CheesePizza extends AlaCartePizza {

  /**
   * Constructor to create cheeze pizza.
   *
   * @param size     pizza size
   * @param crust    crust type
   * @param toppings toppings on pizza
   */
  protected CheesePizza(Size size, Crust crust, Map<ToppingName, ToppingPortion> toppings) {
    super(size, crust, toppings);
  }

  /**
   * Cheese Pizza Builder helper class.
   */
  public static class CheesePizzaBuilder extends betterpizza.AlaCartePizza.AlaCartePizzaBuilder {


    @Override
    public ObservablePizza build() {
      if (this.size == null) {
        throw new IllegalStateException("Size is not mentioned");
      }
      this.addTopping(ToppingName.Cheese, ToppingPortion.Full);
      this.addTopping(ToppingName.Sauce, ToppingPortion.Full);
      return new betterpizza.CheesePizza(this.size, this.crust, this.toppings);
    }


    /**
     * Method to remove cheese from toppings.
     *
     * @return cheese topping removed pizza builder.
     */
    public CheesePizzaBuilder noCheese() {
      this.removeTopping(ToppingName.Cheese);
      return this;
    }

    /**
     * Method to add cheese toppings to left half of pizza.
     *
     * @return cheese toppings added to left half of pizza.
     */
    public CheesePizzaBuilder leftHalfCheese() {
      this.addTopping(ToppingName.Cheese, ToppingPortion.LeftHalf);
      return this;
    }

    /**
     * Method to add cheese toppings to right half of pizza.
     *
     * @return cheese toppings added to right half of pizza.
     */
    public CheesePizzaBuilder rightHalfCheese() {
      this.addTopping(ToppingName.Cheese, ToppingPortion.RightHalf);
      return this;
    }
  }
}
