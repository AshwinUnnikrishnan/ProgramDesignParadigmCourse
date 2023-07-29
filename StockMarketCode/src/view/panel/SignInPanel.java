package view.panel;

import java.awt.GridLayout;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import controller.Features;

/**
 * Class representing Sign In panel.
 */
public class SignInPanel extends JPanel implements PanelInterface {
  private final JButton existingUser;
  private final JButton newUser;
  private final JButton exitButton;
  private final JButton login;
  private final JLabel warningString;
  private final JLabel userNameText;
  private final JTextField userName;

  private boolean createUser;

  /**
   * Constructor to initialise the JButton Values and add layouts to the JPanel.
   */
  public SignInPanel() {
    this.existingUser = new JButton("Existing User");
    this.newUser = new JButton("Create New User");
    this.exitButton = new JButton("Exit Program");
    this.login = new JButton("Login");

    this.warningString = new JLabel("Error Messages will come here");
    this.userNameText = new JLabel("Enter User Name");
    this.userName = new JTextField("");
    this.setLayout(new GridLayout(7, 1, 1, 1));
    this.exitButton.setBackground(Color.RED);
    this.exitButton.setOpaque(true);
    this.exitButton.setBorderPainted(false);
    this.add(this.newUser);
    this.add(this.existingUser);
    this.add(this.exitButton);
    this.add(this.userNameText);
    this.add(this.userName);
    this.add(this.login);
    this.add(this.warningString);
    this.setLogin(false);
  }

  @Override
  public void addFeatures(Features features) {
    this.newUser.addActionListener(evt -> features.showUserLoginButtons(true));
    this.existingUser.addActionListener(evt -> features.showUserLoginButtons(false));
    this.login.addActionListener(evt -> features.login(this.userName.getText(), this.createUser));
    this.exitButton.addActionListener(evt -> features.exitProgram());

  }

  /**
   * Class to set Login Page.
   *
   * @param flag to check the value.
   */
  private void setLogin(boolean flag) {
    this.userNameText.setVisible(flag);
    this.userName.setVisible(flag);
    this.login.setVisible(flag);
    this.warningString.setVisible(flag);
  }

  /**
   * Method to set warning.
   *
   * @param newUser resultant object.
   */
  public void setWarningLabel(String newUser) {
    this.warningString.setText(newUser);
  }

  /**
   * Method to make the Login Page Visible.
   *
   * @param createUser resultant object.
   */
  public void makeLoginVisible(boolean createUser) {
    this.setLogin(true);
    this.createUser = createUser;
  }
}