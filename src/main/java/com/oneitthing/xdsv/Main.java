package com.oneitthing.xdsv;

import javax.swing.SwingUtilities;

import com.github.weisj.darklaf.LafManager;
import com.github.weisj.darklaf.theme.DarculaTheme;
import com.oneitthing.xdsv.controller.XdsvController;
import com.oneitthing.xdsv.ui.main.MainFrame;

/**
 * Hello world!
 *
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("started");

        SwingUtilities.invokeLater(() -> {
            new XdsvController();

            LafManager.install(new DarculaTheme());
            //            try {
            //                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            //            } catch (ClassNotFoundException e) {
            //                // TODO 自動生成された catch ブロック
            //                e.printStackTrace();
            //            } catch (InstantiationException e) {
            //                // TODO 自動生成された catch ブロック
            //                e.printStackTrace();
            //            } catch (IllegalAccessException e) {
            //                // TODO 自動生成された catch ブロック
            //                e.printStackTrace();
            //            } catch (UnsupportedLookAndFeelException e) {
            //                // TODO 自動生成された catch ブロック
            //                e.printStackTrace();
            //            }
            //

            MainFrame mainFrame = new MainFrame();
            mainFrame.setVisible(true);
        });
    }
}
