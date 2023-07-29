/**
 * Created by histravelstories.
 * This is a class
 * Date : 11/14/22
 * Project Name : lab7
 */

package betterpizza;

import java.util.HashMap;
import java.util.Map;

import pizza.Crust;
import pizza.Size;
import pizza.ToppingName;
import pizza.ToppingPortion;

/**
 * This class is a general-purpose implementation that can be used to create pizzas with different
 * crusts, sizes and an arbitrary number of toppings (the ''build-your-own" pizza).
 */
public class AlaCartePizza implements ObservablePizza {
  protected Crust crust;
  protected Size size;
  protected Map<ToppingName, ToppingPortion> toppings;

  /**
   * Constructor to create the AlaCartePizza.
   *
   * @param size     size of pizza
   * @param crust    crust type
   * @param toppings toppings on pizza
   */
  AlaCartePizza(Size size, Crust crust, Map<ToppingName, ToppingPortion> toppings) {
    if (size == null || crust == null || toppings == null) {
      throw new IllegalArgumentException("size, crust, toppings cannot be null");
    }
    this.crust = crust;
    this.size = size;
    this.toppings = toppings;
  }

  @Override
  public ToppingPortion hasTopping(ToppingName name) {
    return this.toppings.getOrDefault(name, null);
  }

  @Override
  public double cost() {
    double cost = 0.0;
    for (Map.Entry<ToppingName, ToppingPortion> item : this.toppings.entrySet()) {
      cost += item.getKey().getCost() * item.getValue().getCostMultiplier();
    }
    return cost + this.size.getBaseCost();
  }


  /**
   * AlaCarte Pizza Builder helper class.
   */
  public static class AlaCartePizzaBuilder extends PizzaBuilder {

    protected Crust crust;
    protected Size size;
    protected Map<ToppingName, ToppingPortion> toppings;

    /**
     * Constructor to create pizza with a classic crust.
     */
    public AlaCartePizzaBuilder() {
      this.crust = Crust.Classic;
      this.size = null;
      this.toppings = new HashMap<>();
    }

    @Override
    public AlaCartePizzaBuilder crust(Crust crust) {
      this.crust = crust;
      return this;
    }

    @Override
    public AlaCartePizzaBuilder size(Size size) {
      this.size = size;
      return this;
    }

    @Override
    public AlaCartePizzaBuilder addTopping(ToppingName name, ToppingPortion portion) {
      this.toppings.put(name, portion);
      return this;
    }

    @Override
    public AlaCartePizzaBuilder removeTopping(ToppingName name) {
      this.toppings.remove(name);
      return this;
    }


    @Override
    public ObservablePizza build() {
      if (this.size == null) {
        throw new IllegalStateException("Size is not mentioned");
      }
      return new AlaCartePizza(this.size, this.crust, this.toppings);
    }
  }
}
