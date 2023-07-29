package view.panel;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;


import java.util.ArrayList;

/**
 * Class to implement BarChart for portfolio performance.
 */
public class BarChart extends JPanel {
  private ArrayList<Integer> values;
  private ArrayList<String> title;

  /**
   * Constructor to initialise the list of Arrays.
   */
  public BarChart() {
    values = new ArrayList<>();
    title = new ArrayList<>();
  }

  /**
   * Method to set values of the String.
   *
   * @param resultString the resultant ojects.
   */
  public void setValues(String resultString) {
    String[] lines = resultString.split("\n");
    String scales;
    for (int i = 1; i < lines.length - 1; i++) {
      String[] val = lines[i].split(": ");
      this.values.add(val[1].length() - 2);
      this.title.add(val[0]);

    }
    scales = lines[lines.length - 1];
    System.out.println(scales);

  }

  /**
   * Method to display the graph.
   *
   * @param g the <code>Graphics</code> object to protect
   */
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    if (values == null || values.size() == 0) {
      return;
    }
    double minValue = 0;
    double maxValue = 0;
    for (int i = 0; i < values.size(); i++) {
      if (minValue > values.get(i)) {
        minValue = values.get(i);
      }
      if (maxValue < values.get(i)) {
        maxValue = values.get(i);
      }
    }

    Dimension d = getSize();
    int clientWidth = d.width;
    int clientHeight = d.height;
    int barWidth = clientWidth / values.size();

    Font titleFont = new Font("SansSerif", Font.BOLD, 6);
    FontMetrics titleFontMetrics = g.getFontMetrics(titleFont);
    Font labelFont = new Font("SansSerif", Font.PLAIN, 6);
    FontMetrics labelFontMetrics = g.getFontMetrics(labelFont);

    int y = titleFontMetrics.getAscent();
    int x = (clientWidth) / 20;
    g.setFont(titleFont);
    g.drawString(String.valueOf(title), x, y);

    int top = titleFontMetrics.getHeight();
    int bottom = labelFontMetrics.getHeight();
    if (maxValue == minValue) {
      return;
    }
    double scale = (clientHeight - top - bottom) / (maxValue - minValue);
    y = clientHeight - labelFontMetrics.getDescent();
    g.setFont(labelFont);

    for (int i = 0; i < values.size(); i++) {
      int valueX = i * barWidth + 1;
      int valueY = top;
      int height = (int) (values.get(i) * scale);
      if (values.get(i) >= 0) {
        valueY += (int) ((maxValue - values.get(i)) * scale);
      } else {
        valueY += (int) (maxValue * scale);
        height = -height;
      }

      g.setColor(Color.pink);
      g.fillRect(valueX, valueY, barWidth - 2, height);
      g.setColor(Color.black);
      g.drawRect(valueX, valueY, barWidth - 2, height);
      int labelWidth = labelFontMetrics.stringWidth(String.valueOf(values.get(i)));
      x = i * barWidth + (barWidth - labelWidth) / 2;
      g.drawString(String.valueOf(values.get(i)), x, y);
    }
  }

}
