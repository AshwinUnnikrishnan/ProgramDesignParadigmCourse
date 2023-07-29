package model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Class for having stockMarket related functions, responsible to fetch data from database, and
 * update userdata.
 */
public class StockMarketModel implements StockMarketModelInterface {

  private final Set<String> tickerSymbols;  // file that contains list of all stock symbols
  private Set<String> userNames;  // Contains list of all users registered

  private final String userNameFile; // file that contains just the userName

  private String userDetailFile; // file that contains all the details about the user

  private final StockAPI stockMarketAPI; // API to make calls to the stock market online version

  private final JsonReader readUserDetails; // json object initialized with file of userDetails

  /**
   * Constructor for the Stock market model, checks and creates userFile if not existing, same thing
   * for userDetail database file as well and creates an API object to call for latest data.
   *
   * @param tickerSymbolFile : file that contains valid ticker symbols.
   * @param userNameFile     : file that contains users registered, would add password check login.
   * @param userDetailFile   : file that contains the details of all the user.
   * @throws IOException : If there is IO related issue.
   */
  public StockMarketModel(String tickerSymbolFile, String userNameFile, String userDetailFile)
          throws IOException {
    new File(userNameFile).createNewFile();
    checkAndCreateDatabase(userDetailFile);
    this.tickerSymbols = convertTextToHash(tickerSymbolFile);
    this.userNames = convertTextToHash(userNameFile);
    this.userNameFile = userNameFile;
    this.userDetailFile = userDetailFile;
    this.stockMarketAPI = new AlphaVantageAPI();
    this.readUserDetails = new JsonReader(userDetailFile);
  }

  /**
   * Method to check if the userDatabase file exists, if it does not then create a json with the
   * passed name.
   *
   * @param userDetailsDatabase : File that contains all the details.
   * @throws IOException : Raised if file related issues.
   */
  private static void checkAndCreateDatabase(String userDetailsDatabase) throws IOException {
    File myObj = new File(userDetailsDatabase);
    if (myObj.createNewFile()) {
      PrintWriter pw = new PrintWriter(new FileWriter(myObj));
      pw.println("{\n}");
      pw.flush();
      pw.close();
    }
  }


  /**
   * Helper method to create a hashSet of the valid tickerSymbols so that we can avoid pinging the
   * website each time.
   *
   * @param tickerSymbolFile : file that contains the tickerSymbols
   * @return : Reference of Hashset contains all the ticker.
   * @throws FileNotFoundException : if the tickerFile does not exist.
   */
  private Set<String> convertTextToHash(String tickerSymbolFile) throws FileNotFoundException {
    Scanner in = new Scanner(new FileReader(tickerSymbolFile));
    Set<String> set = new HashSet<>();
    while (in.hasNext()) {
      set.add(in.next());
    }
    in.close();
    return set;
  }


  /**
   * Method to add username to username file and database file when new user is created.
   *
   * @param userName : username with which we want to create profile.
   * @throws IOException : Thrown when write exception happens in file.
   */
  @Override
  public Investor addUserName(String userName) throws IOException {
    this.userNames.add(userName);
    BufferedWriter bw = new BufferedWriter(new FileWriter(this.userNameFile, true));
    bw.write("\n" + userName);
    bw.close();
    Investor currentUser = new Investor(userName);
    return currentUser;
  }


  /**
   * Load the userDetails if the user exists in database or create a new investor with userName
   * passed.
   *
   * @param userName userName of the person logged in.
   * @return Investor object of the user.
   * @throws FileNotFoundException : thrown if the user details database does not exist.
   */
  @Override
  public Investor loadFromExisting(String userName) throws FileNotFoundException {
    String details = this.readUserDetails.readUserDataBase(userName);
    return details == null ? new Investor(userName) : new Investor(userName, details);
  }


  /**
   * Helper method to check if the username is present in the list.
   *
   * @param name          name of the stock or name to check.
   * @param userNameCheck boolean to mention if checking userName or stock
   * @return true if the thing searching exists.
   */
  @Override
  public boolean checkUserOrStock(String name, boolean userNameCheck) {
    return userNameCheck ? this.userNames.contains(name) : this.tickerSymbols.contains(name);
  }

  /**
   * Method to check if entered stock exists in the portfolio of not.
   *
   * @param nPortfolio portfolio to chekc the stock.
   * @param stockName  name of the stock to be checked.
   * @return true if stock exists, false if it does not.
   */
  @Override
  public Stock checkStockInPortfolio(Portfolio nPortfolio, String stockName) {
    Set<Stock> temp = nPortfolio.getStockList();
    Iterator<Stock> itr = temp.iterator();
    while (itr.hasNext()) {
      Stock stockSearch = itr.next();
      if (stockSearch.getName().equals(stockName)) {
        return stockSearch;
      }
    }
    return null;
  }


  /**
   * Method to get current price of particular stock on a date.
   *
   * @param stockName Stock name
   * @param date      Date
   * @param now       if true then we calculate for current time "RegularMarketPrice", else
   *                  we take close price.
   * @return closing/regular market price of stock on that date.
   */
  @Override
  public float getPriceOnDate(String stockName, LocalDate date, boolean now) {
    return Float.parseFloat(this.stockMarketAPI.getAPIdata(stockName, date.toString(), now));
  }

  /**
   * Method to update the user details to the database file.
   *
   * @param currentUser Details about the user passed as investor object.
   * @param newUser     boolean that tells if the call is for newUser or existing
   * @throws IOException Thrown when write fails, when writing new user or existing.
   */
  @Override
  public void writeUpdatedUser(Investor currentUser, boolean newUser) throws IOException {

    File inFile = new File(this.userDetailFile);
    File tempFile = new File(inFile.getAbsolutePath() + ".tmp");
    PrintWriter pw = new PrintWriter(new FileWriter(tempFile));
    Scanner scan = new Scanner(new FileReader(this.userDetailFile));
    // As there is no way to edit and read the text as of now that I know, creating a file when
    // editing existing user
    if (!newUser) {
      // if not new user then check for the line where the user is and instead of copying the
      // same thing we write to the new file the updated details.
      String regex = "^\"(" + currentUser.getName() + ")\" :\\{\"Portfolio\":(.*)}$";
      Pattern pattern = Pattern.compile(regex);
      Matcher matcher;
      while (scan.hasNext()) {
        String temp = scan.nextLine();
        matcher = pattern.matcher(temp);
        pw.println(matcher.matches() ? currentUser : temp);
        pw.flush();
      }
    } else {
      // writing a new user
      boolean first = true; // maintaining this to distinguish between if we are adding first time
      while (scan.hasNext()) {
        String temp = scan.nextLine();
        if (temp.equals("}")) {
          pw.println((first ? "" : ",\n") + currentUser);
        } else if (!temp.equals("{")) {
          first = false;
        }
        pw.println(temp);
        pw.flush();
      }
    }
    pw.close();

    if (!inFile.delete()) {
      throw new IOException("Could not delete file");
    }
    if (!tempFile.renameTo(inFile)) {
      throw new IOException("Could not rename file");
    }
  }

  /**
   * Method to calculate evaluation of certain set of stocks on a particular date.
   *
   * @param currentUser A set containing all the stocks
   * @param dateToCheck Date where we want to evaluate.
   * @return returns a float of evaluation of profit.
   */
  @Override
  public float getEvaluation(Investor currentUser, String portfolioName, String dateToCheck) {
    ArrayList<String> transactionHistory = ((AbstractPortfolio) currentUser.getPortfolio()
            .get(portfolioName)).getTransactionHistory();
    Iterator<String> itr = transactionHistory.iterator();
    float totalEval = 0.0f;
    while (itr.hasNext()) {
      String temp = itr.next();
      String[] history = temp.split(",");

      if (LocalDate.parse(dateToCheck).isAfter(LocalDate.parse(history[1]))
              || LocalDate.parse(dateToCheck).isEqual(LocalDate.parse(history[1]))) {
        if (history[0].equals("Sell")) {
          totalEval = totalEval + Float.parseFloat(history[4]) * Float.parseFloat(history[2]);
        } else {
          totalEval = totalEval + Float.parseFloat(history[4]) * this.getPriceOnDate(history[3],
                  LocalDate.parse(dateToCheck), true);
        }
        totalEval = totalEval - Float.parseFloat(history[5]);
      }
    }
    return totalEval;
  }

  /**
   * Helper method to convert string date to Local Date of type yyyy-MM-dd.
   *
   * @param date date in string format
   * @return date in localdate
   */
  private LocalDate formatTimeStringToLocalDate(String date) {
    return LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
  }

  /**
   * Method to check if portfolio exists.
   *
   * @param currentUser   User to check.
   * @param portfolioName portfolio name to check.
   * @return true if the portfolio exists.
   */
  @Override
  public boolean portfolioExists(Investor currentUser, String portfolioName) {
    return currentUser.getPortfolio().get(portfolioName) != null;
  }

  /**
   * Method to check if the portfolio is flexible or not.
   *
   * @param currentUser   investor object.
   * @param portfolioName portfolioName.
   * @return returns true if portfolio is flexible.
   */
  @Override
  public boolean checkPortfolioFlexible(Investor currentUser, String portfolioName) {
    return ((Portfolio) currentUser.getPortfolio().get(portfolioName)).isFlexible();
  }


  /**
   * Method to add stocks to portfolio.
   *
   * @param newPortfolio InflexiblePortfolio to add the stock to
   * @param stockName    name of the stock
   * @param quantity     number of stocks
   * @param date         date to buy the stock
   * @param create       true if to add stock
   * @param broker       broker fees charged
   * @return updated portfolio
   */
  @Override
  public Portfolio addStockToPortfolio(Portfolio newPortfolio, String stockName, String quantity,
                                       String date, boolean create, float broker) {
    if (!create && !newPortfolio.isFlexible()) {
      return newPortfolio;
    }
    LocalDate formattedDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    newPortfolio.addStockToPortfolio(stockName, Integer.parseInt(quantity),
            this.getPriceOnDate(stockName, formattedDate, true), formattedDate, broker,
            -1);
    return newPortfolio;
  }

  /**
   * Method to add the newly created portfolio to the user.
   *
   * @param newPortfolio portfolio to be added.
   * @return updated investor.
   */
  @Override
  public Investor addPortfolio(Investor investor, Portfolio newPortfolio) {
    investor.getPortfolio().put(newPortfolio.getName(), newPortfolio);
    return investor;
  }

  /**
   * Method to print portfolio composition for a given profile.
   *
   * @param investor      name of the investor whose portfolio composition we want.
   * @param portfolioName name of the portfolio.
   * @return returns the composition.
   */
  @Override
  public String printPortfolioComposition(Investor investor, String portfolioName, String date) {
    Set<Stock> temp = ((Portfolio) investor.getPortfolio().get(portfolioName)).getStockList();
    StringBuilder sb = new StringBuilder();
    Iterator<Stock> itr = temp.iterator();

    String regex = "^\"\\w+\" : \\[.*,\"(.*)\"]$";
    Pattern pattern = Pattern.compile(regex);
    Matcher matcher;
    while (itr.hasNext()) {
      String tempStock = itr.next().toString();
      matcher = pattern.matcher(tempStock);
      if (matcher.matches()) {
        if (LocalDate.parse(date).isAfter(LocalDate.parse(matcher.group(1))) ||
                date.equals(matcher.group(1))) {
          sb.append(tempStock);
          sb.append(itr.hasNext() ? "\n" : "");
        }
      }

    }
    return sb.toString();
  }

  /**
   * Method to sell stocks from a portfolio.
   *
   * @param nPortfolio Portfolio from which we need to sell the stock.
   * @param stockName  Name of the stock to sell.
   * @param quantity   Number of stocks to sell.
   * @param sellDate   Date on which we want to sell the stocks.
   * @param brokerfee  broker fees
   * @return updated portfolio.
   */
  @Override
  public Portfolio deleteStockFromPortfolio(Portfolio nPortfolio, String stockName, String quantity,
                                            String sellDate, float brokerfee) {
    // TODO what if it goes to zero delete it for now not
    float priceOnDate = this.getPriceOnDate(stockName, formatTimeStringToLocalDate(sellDate),
            false);

    nPortfolio.editStockInPortfolio(stockName, quantity, priceOnDate, sellDate, brokerfee);
    return nPortfolio;
  }

  /**
   * Method to evaluate cost basis of a portfolio given a user.
   *
   * @param currentUser   Investor whose cost basis we want to calculate.
   * @param portfolioName portfolioName that we want to evaluate cost basis.
   * @param date          date to evaluate cost basis.
   * @return cost
   */
  @Override
  public float evaluateCostBasis(Investor currentUser, String portfolioName, String date) {
    return ((Portfolio) currentUser.getPortfolio().get(portfolioName)).evaluateCostBasis(date);
  }

  @Override
  public String calculatePortfolioPerformance(Investor currentUser, String portfolioName,
                                              String dateInitial, String dateFinal) {
    PortfolioPerformance tempPerf = new PortfolioPerformance((Portfolio)
            currentUser.getPortfolio().get(portfolioName));
    return tempPerf.portfolioPerformance(dateInitial, dateFinal);

  }

  @Override
  public Portfolio strategyInvestment(Portfolio nPortfolio, ArrayList<String> stockNames,
                                      String amount, ArrayList<String> weight, String startDate,
                                      String endDate, String range, int flag) {
    InvestmentStrategy strategy = null;
    if (flag == 0) {
      strategy = new DollarCost(Integer.parseInt(range), Float.parseFloat(amount));
    }
    return strategy.calculateTheInvestment(nPortfolio, stockNames, weight, startDate, endDate,
            new AlphaVantageAPI());
  }
}
