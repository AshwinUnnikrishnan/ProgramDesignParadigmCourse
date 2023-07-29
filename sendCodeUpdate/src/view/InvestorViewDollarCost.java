package view;

import java.io.PrintStream;

/**
 * Class that implements the dollar cost investment view. It extends the previous text based view
 * to add the dollar cost.
 */
public class InvestorViewDollarCost extends InvestorView {

  /**
   * Constructor that takes in the type of printStream where we need to print the data for user to
   * see.
   *
   * @param out : where to print.
   */
  public InvestorViewDollarCost(PrintStream out) {
    super(out);
  }

  /**
   * Method to print options the user has once he logs in.
   */
  @Override
  public void printAfterLoginScreen() {
    out.println("Please select one of the options");
    out.println("Press 1 to create new Portfolio");
    out.println("Press 2 to examine composition of a portfolio");
    out.println("Press 3 to determine total value of a portfolio on a certain date");
    out.println("Press 4 to edit portfolio");
    out.println("Press 5 to determine cost basis of portfolio");
    out.println("Press 6 to see portfolio Performance");
    out.println("Press 7 to create dollar cost averaging portfolio");
    out.println("Press 8 to exit");
  }
}
