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
public class VeggiePizza extends AlaCartePizza {

  /**
   * Constructor to create cheeze pizza.
   *
   * @param size     pizza size
   * @param crust    crust type
   * @param toppings toppings on pizza
   */
  protected VeggiePizza(Size size, Crust crust, Map<ToppingName, ToppingPortion> toppings) {
    super(size, crust, toppings);
  }


  /**
   * Veggie Pizza Builder helper class.
   */
  public static class VeggiePizzaBuilder extends betterpizza.AlaCartePizza.AlaCartePizzaBuilder {

    @Override
    public ObservablePizza build() {
      if (this.size == null) {
        throw new IllegalStateException("Size is not mentioned");
      }
      this.addTopping(ToppingName.Cheese, ToppingPortion.Full);
      this.addTopping(ToppingName.Sauce, ToppingPortion.Full);
      this.addTopping(ToppingName.BlackOlive, ToppingPortion.Full);
      this.addTopping(ToppingName.GreenPepper, ToppingPortion.Full);
      this.addTopping(ToppingName.Onion, ToppingPortion.Full);
      this.addTopping(ToppingName.Jalapeno, ToppingPortion.Full);
      this.addTopping(ToppingName.Tomato, ToppingPortion.Full);
      return new VeggiePizza(this.size, this.crust, this.toppings);
    }

  }
}
