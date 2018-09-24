package window.parts;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import dialog.*;
import model.*;
import window.*;

public class CreateMenubar {
    private static final String DELETE = "Delete";
    private static final String EDITITEM = "EditItem";
    private static final String COPY = "Copy";
    private static final String PASTE = "Paste";

    /**
     * メニューバーを作成する
     * @param main
     * @return
     */
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