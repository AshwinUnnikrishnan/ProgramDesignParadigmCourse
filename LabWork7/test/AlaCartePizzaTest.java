import org.junit.Before;
import org.junit.Test;

import betterpizza.AlaCartePizza;
import betterpizza.CheesePizza;
import betterpizza.ObservablePizza;
import betterpizza.VeggiePizza;
import pizza.Crust;
import pizza.Size;
import pizza.ToppingName;
import pizza.ToppingPortion;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


/**
 * Class to test functionalities of AlaCartePizza.
 */
public class AlaCartePizzaTest {

  private ObservablePizza alacarte;
  private ObservablePizza cheese;
  private ObservablePizza halfCheese;
  private ObservablePizza veggie;


  @Before
  public void setup() {
    alacarte = new AlaCartePizza.AlaCartePizzaBuilder()
            .crust(Crust.Classic)
            .size(Size.Medium)
            .addTopping(ToppingName.Cheese, ToppingPortion.Full)
            .addTopping(ToppingName.Sauce, ToppingPortion.Full)
            .addTopping(ToppingName.GreenPepper, ToppingPortion.Full)
            .addTopping(ToppingName.Onion, ToppingPortion.Full)
            .addTopping(ToppingName.Jalapeno, ToppingPortion.LeftHalf)
            .build();

    cheese = new CheesePizza.CheesePizzaBuilder()
            .crust(Crust.Thin)
            .size(Size.Large)
            .build();

    veggie = new VeggiePizza.VeggiePizzaBuilder()
            .size(Size.Medium)
            .crust(Crust.Thin)
            .addTopping(ToppingName.Cheese, ToppingPortion.LeftHalf)
            .build();
  }

  @Test
  public void testPizzaCreation() {
    ObservablePizza alacarte = new AlaCartePizza.AlaCartePizzaBuilder()
            .crust(Crust.Classic)
            .size(Size.Medium)
            .addTopping(ToppingName.Cheese, ToppingPortion.Full)
            .addTopping(ToppingName.Sauce, ToppingPortion.Full)
            .addTopping(ToppingName.GreenPepper, ToppingPortion.Full)
            .addTopping(ToppingName.Onion, ToppingPortion.Full)
            .addTopping(ToppingName.Jalapeno, ToppingPortion.LeftHalf)
            .build();
    assertNotNull(alacarte);

  }

  @Test
  public void testCost() {
    assertEquals(8.25, alacarte.cost(), 0.01);
    assertEquals(9, cheese.cost(), 0.01);
    assertEquals(9.5, veggie.cost(), 0.01);
    assertEquals(7.0, halfCheese.cost(), 0.01);
  }
}