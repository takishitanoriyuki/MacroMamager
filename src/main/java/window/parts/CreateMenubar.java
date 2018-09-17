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
        JMenu actionMenu = new JMenu("Action");
        menubar.add(actionMenu);
        JMenuItem editMenuItem = new JMenuItem("Edit");
        actionMenu.add(editMenuItem);
        editMenuItem.setActionCommand(EDITITEM);
        editMenuItem.addActionListener(new ActionListener(){
        
            @Override
            public void actionPerformed(ActionEvent e) {
                if(main.getTable().getSelectedRowCount() == 1){
                    // 編集メニュークリック時の処理
                    if(e.getActionCommand() == EDITITEM){
                        int index = main.getTable().getSelectedRow() - 1;
                        DataRecord record = main.getDataRecords().get(index);
                        EditDialog dialog = new EditDialog(main, index, record);
                        dialog.Show();
                    }
                }
            }
        });

        JMenuItem copyMenuItem = new JMenuItem("Copy");
        actionMenu.add(copyMenuItem);
        copyMenuItem.setActionCommand(COPY);
        copyMenuItem.addActionListener(new ActionListener(){
        
            @Override
            public void actionPerformed(ActionEvent e) {
                if(main.getTable().getSelectedRowCount() == 1){
                    // 複製メニュークリック時の処理
                    if(e.getActionCommand() == COPY){
                        int index = main.getTable().getSelectedRow() - 1;
                        DataRecord record = main.getDataRecords().get(index);
                        IClipBoard clip = ClipBoard.getInstanse();
                        clip.Store(record);
                    }
                }
            }
        });

        JMenuItem pasteMenuItem = new JMenuItem("Paste");
        actionMenu.add(pasteMenuItem);
        pasteMenuItem.setActionCommand(PASTE);
        pasteMenuItem.addActionListener(new ActionListener(){
        
            @Override
            public void actionPerformed(ActionEvent e) {
                if(main.getTable().getSelectedRowCount() == 1){
                    // 貼付けメニュークリック時の処理
                    if(e.getActionCommand() == PASTE){
                        IClipBoard clip = ClipBoard.getInstanse();
                        DataRecord record = clip.Pull();
                        main.SetRecord(record);
                    }
                }
            }
        });

        JMenuItem deleteMenuItem = new JMenuItem("Delete");
        actionMenu.add(deleteMenuItem);
        deleteMenuItem.setActionCommand(DELETE);
        deleteMenuItem.addActionListener(new ActionListener(){
        
            @Override
            public void actionPerformed(ActionEvent e) {
                if(main.getTable().getSelectedRowCount() == 1){
                    // 削除メニュークリック時の処理
                    if(e.getActionCommand() == DELETE){
                        int index = main.getTable().getSelectedRow();
                        main.RemoveRecord(index);
                    }
                }
            }
        });

        return menubar;
    }
}