package com.oneitthing.xdsv;

import javax.swing.SwingUtilities;

import com.github.weisj.darklaf.LafManager;
import com.github.weisj.darklaf.theme.DarculaTheme;
import com.oneitthing.xdsv.controller.XdsvController;
import com.oneitthing.xdsv.ui.main.MainFrame;

/**
 * start app.
 */
public class App {
    public static void main(String[] args) {
        System.out.println("started");
        org.apache.xml.security.Init.init();
        
        SwingUtilities.invokeLater(() -> {
            new XdsvController();

            LafManager.install(new DarculaTheme());
            

            MainFrame mainFrame = new MainFrame();
            mainFrame.setVisible(true);
        });
    }
}
