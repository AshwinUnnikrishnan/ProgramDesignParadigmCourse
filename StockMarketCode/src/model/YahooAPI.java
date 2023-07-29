package model;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class that uses yahoo finance API to get details about the stocks.
 */
public class YahooAPI implements StockAPI {

  private final String timeZoneCheck; // which timezone we want to work in

  /**
   * Constructor that takes in the timeZone the stock market works in.
   *
   * @param timeZoneCheck timezone to check the stockmarket.
   */
  public YahooAPI(String timeZoneCheck) {
    this.timeZoneCheck = timeZoneCheck;
  }

  /**
   * Method to fetch the stock value of a particular stock on a given date.
   *
   * @param stockSymbol stockName : name of the stock to get the data.
   * @param inputDate   inputDate : date for which we need the details.
   * @param now         to get regularMarketPrice or closing price
   * @return returns the value the stock on the given date as string.
   */
  @Override
  public String getAPIdata(String stockSymbol, String inputDate, boolean now) {
    URL url;
    String outputString;
    LocalDateTime currentTime = LocalDateTime.now();
    int hrs = currentTime.getHour();
    String defaultTime = " " + hrs + ":00:00.000";
    String dateFormat = "yyyy-MM-dd H:mm:ss.SSS";
    if (hrs > 9) {
      dateFormat = "yyyy-MM-dd HH:mm:ss.SSS";
    }
    String dateText = inputDate + defaultTime;
    ZonedDateTime zonedDateTime = LocalDateTime.parse(dateText,
            DateTimeFormatter.ofPattern(dateFormat)).atZone(ZoneId.of(timeZoneCheck));
    String epochTime = String.valueOf((zonedDateTime.toInstant().toEpochMilli()) / 1000);

    try {
      url = new URL("https://query1.finance.yahoo.com/v8/finance/chart/" + stockSymbol
              + "?&period1=" + epochTime + "&period2=" + epochTime + "&interval=1d");
      InputStream in;
      StringBuilder output = new StringBuilder();

      in = url.openStream();
      int b;
      while ((b = in.read()) != -1) {
        output.append((char) b);
      }
      Scanner scan = new Scanner(String.valueOf(output));
      String regex = "\\{\"chart\":(.*),\"regularMarketPrice\":([0-9]+.[0-9]+),.*$";
      Pattern pattern = Pattern.compile(regex);

      while (scan.hasNext()) {
        String temp = scan.nextLine();
        Matcher matcher = pattern.matcher(temp);
        if (matcher.matches()) {
          outputString = (matcher.group(2));
          return outputString;
        }
      }
      return null;
    } catch (IOException e) {
      throw new IllegalArgumentException("No price data found for " + stockSymbol);
    }
  }
}