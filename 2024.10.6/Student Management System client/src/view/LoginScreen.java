package view;

import control.Datas;
import entity.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.Date;

public class LoginScreen {

    JFrame loginScreen;
    private Boolean password = false;

    public Boolean pass() {
        return password;
    }
    public LoginScreen() {
        loginScreen = new JFrame("登入");
        loginScreen.setLayout(new CardLayout());

        loginScreen.add(getLoginScreen());

        loginScreen.setBounds(300, 300, 500, 300);
        loginScreen.setVisible(true);

    }

    public JPanel getLoginScreen() {
        JPanel loginJPanel = new JPanel();
        loginJPanel.setLayout(new FlowLayout(1, 400, 10));
        TextField userTextField = new TextField();
        userTextField.setPreferredSize(new Dimension(100, 20));
        TextField passwordTextField = new TextField();
        passwordTextField.setEchoChar('*');
        passwordTextField.setPreferredSize(new Dimension(100, 20));
        JButton enterJButton = new JButton("登入");
        JLabel tips = new JLabel();
        tips.setForeground(Color.red);
        loginJPanel.add(userTextField);
        loginJPanel.add(passwordTextField);
        loginJPanel.add(enterJButton);
        loginJPanel.add(tips);






        enterJButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User user = new User(userTextField.getText(), passwordTextField.getText(), new Date());
                Datas datas = new Datas();
                if (datas.userLogin(user)) {
                    loginScreen.dispose();
                    password = true;
                } else {
                    System.out.println("账户或密码错误");
                    passwordTextField.setText("");
                    tips.setText("账户或密码错误");
                    loginJPanel.repaint();
                    new Thread(() -> {
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException ex) {
                            throw new RuntimeException(ex);
                        }
                        tips.setText("");
                    }).start();
                    loginJPanel.repaint();
                }
            }
        });







        return loginJPanel;
    }
    public JPanel getSignUpcreen() {
        JPanel signUpJPanel = new JPanel();


        return signUpJPanel;
    }
}
