package window.parts;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import dialog.TargetSetiingDialog;
import window.*;

public class CreateMenubar {
    public static JMenuBar Create(IMainWindow main){
        JMenuBar menubar = new JMenuBar();
        JMenu OptionMenu = new JMenu("Option");
        menubar.add(OptionMenu);
        JMenuItem settingMenuItem = new JMenuItem("Setting");
        OptionMenu.add(settingMenuItem);
        settingMenuItem.addActionListener(new ActionListener(){
        
            /**
             * メニューボタン押下処理
             */
            @Override
            public void actionPerformed(ActionEvent event) {
                TargetSetiingDialog dialog = new TargetSetiingDialog(main);
                dialog.Show();
            }
        });
        return menubar;
    }
}