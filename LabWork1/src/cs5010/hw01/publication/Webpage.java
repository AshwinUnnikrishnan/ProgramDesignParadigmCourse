package cs5010.hw01.publication;

/**
 * Represents bibliographical information for a web page.
 */
public class Webpage implements Publication {
  private final String title;
  private final String url;
  private final String date;

  /**
   * Constructs a  web page.
   *
   * @param title title of web page
   * @param url   the URL to access the web page
   * @param date  the last date on which the web page was accessed
   */
  public Webpage(String title, String url, String date) {
    this.title = title;
    this.url = url;
    this.date = date;
  }

  @Override
  public String citeApa() {
    return String.format("%s. Retrieved %s, from %s.", this.title, this.date,
            this.url);
  }

  @Override
  public String citeMla() {
    return String.format("\"%s.\" Web. %s <%s>.", this.title, this.date, this
            .url);
  }
}
