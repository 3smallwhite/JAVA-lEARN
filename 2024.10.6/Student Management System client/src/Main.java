import view.LoginScreen;
import view.ManagementScreen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        LoginScreen loginScreen = new LoginScreen();
        while (true) {
            Thread.sleep(100);
            if (loginScreen.pass()) {
                break;
            }
        }
        System.out.println("登入成功");
        ManagementScreen managementScreen = new ManagementScreen();

    }
}