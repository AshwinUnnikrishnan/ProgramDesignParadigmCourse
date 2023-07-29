package model;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class that implements stockAPI using AlphaVantage.
 */
public class AlphaVantageAPI implements StockAPI {

  /**
   * Method to getAPI data using alphavantage.
   *
   * @param stockSymbol stockName : name of the stock to get the data.
   * @param inputDate   inputDate : date for which we need the details.
   * @param now         to get regularMarketPrice or closing price
   * @return the value as string.
   */
  public String getAPIdata(String stockSymbol, String inputDate, boolean now) {

    String stockSymbolInput = stockSymbol; //ticker symbol for
    URL url = null;
    String outputString = null;
    String apiKey = "6NBLKRHSKPUYOROW";
    int previousDay = 0;
    boolean checkNow = now;
    LocalDate currentDate = LocalDate.now();
    //Get date from user as a string


    try {
      url = new URL("https://www.alphavantage"
              + ".co/query?function=TIME_SERIES_DAILY"
              + "&outputsize=full"
              + "&symbol"
              + "=" + stockSymbol + "&apikey=" + apiKey + "&datatype=csv");

    } catch (MalformedURLException e) {
      throw new RuntimeException("the alphavantage API has either changed or "
              + "no longer works");
    }

    InputStream in = null;
    StringBuilder output = new StringBuilder();

    try {
      in = url.openStream();
      int b;

      while ((b = in.read()) != -1) {
        output.append((char) b);
      }
      while (true) {
        String regex = inputDate + "(,\\d+.\\d+,)(\\d+.\\d+,)(\\d+.\\d+,)(\\d+.\\d+)(,\\d+)$";
        Pattern pattern = Pattern.compile(regex);
        Scanner scan = new Scanner(String.valueOf(output));
        while (scan.hasNext()) {
          String temp = scan.nextLine();
          Matcher matcher = pattern.matcher(temp);

          if (matcher.matches()) {
            outputString = (matcher.group(4));
          }
        }
        if (outputString != null) {
          break;
        }
        LocalDate inputDateString = LocalDate.parse(inputDate,
                DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        //if checkNow is true, it is normal scenario.
        //if checkNow is false, it is for strategy porfolio.
        if (checkNow) {
          inputDateString = inputDateString.minusDays(1);
        } else {
          //We increment the dates until current date
          if (inputDateString.isBefore(currentDate)) {
            inputDateString = inputDateString.plusDays(1);
          } else {
            inputDateString = inputDateString.minusDays(1);
          }
        }
        inputDate = inputDateString.toString();
      }
    } catch (IOException e) {
      throw new IllegalArgumentException("No price data found for " + stockSymbolInput);
    }
    return outputString;
  }
}
