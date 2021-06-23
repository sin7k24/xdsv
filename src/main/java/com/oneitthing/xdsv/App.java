package com.oneitthing.xdsv;

import javax.swing.SwingUtilities;

import com.oneitthing.xdsv.controller.XdsvController;
import com.oneitthing.xdsv.ui.main.MainFrame;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        System.out.println("started");
        org.apache.xml.security.Init.init();
        
        SwingUtilities.invokeLater(() -> {
            new XdsvController();

//            LafManager.install(new DarculaTheme());
            
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
//            Toolkit.getDefaultToolkit().addAWTEventListener(new AWTEventListener(){
//                public void eventDispatched(AWTEvent evt) {
//                    System.out.println(evt);
//                }
//            }, AWTEvent.COMPONENT_EVENT_MASK);

            MainFrame mainFrame = new MainFrame();
            mainFrame.setVisible(true);
        });
    }
}
