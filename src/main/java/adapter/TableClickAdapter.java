package adapter;

import java.util.List;
import java.awt.event.*;

import javax.swing.*;

import dialog.EditDialog;
import model.*;
import window.*;
import window.parts.*;

public class TableClickAdapter extends MouseAdapter {
    private IMainWindow mainWindow;
    private List<DataRecord> dataRecord;

    private final String DELETE = "Delete";
    private final String EDITITEM = "EditItem";
    private final String COPY = "Copy";
    private final String PASTE = "Paste";

    public TableClickAdapter(IMainWindow main, List<DataRecord> list){
        this.mainWindow = main;
        this.dataRecord = list;
    }

    @Override
    public void mouseClicked(MouseEvent event){
        // テーブルを右クリックしたときの処理
        if(event.getButton() == MouseEvent.BUTTON3){
            int indexs[] = this.mainWindow.getTable().getSelectedRows();
            // 選択している行がないときは何もしない
            if(indexs.length == 0){
                return;
            }
            // 選択している行が合計の行だった場合
            for (int index : indexs) {
                if(index == 0){
                    return;
                }
            }
            JPopupMenu popupMenu = new JPopupMenu();
            // 編集メニューの実装
            JMenuItem menuItemEdit = new JMenuItem("EDIT");
            menuItemEdit.setActionCommand(EDITITEM);
            menuItemEdit.addActionListener(new ActionListener(){

                @Override
                public void actionPerformed(ActionEvent e) {
                    // 編集メニュークリック時の処理
                    if(e.getActionCommand() == EDITITEM){
                        int index = mainWindow.getTable().getSelectedRow() - 1;
                        DataRecord record = dataRecord.get(index);
                        EditDialog dialog = new EditDialog(mainWindow, index, record);
                        dialog.Show();
                    }
                }
            });
            popupMenu.add(menuItemEdit);

            // 複製メニューの実装
            JMenuItem menuItemCopy = new JMenuItem("COPY");
            menuItemCopy.setActionCommand(COPY);
            menuItemCopy.addActionListener(new ActionListener(){
            
                @Override
                public void actionPerformed(ActionEvent e) {
                    // 複製メニュークリック時の処理
                    if(e.getActionCommand() == COPY){
                        int index = mainWindow.getTable().getSelectedRow() - 1;
                        DataRecord record = dataRecord.get(index);
                        IClipBoard clip = ClipBoard.getInstanse();
                        clip.Store(record);
                    }
                }
            });
            popupMenu.add(menuItemCopy);

            // 貼付けメニューの実装
            JMenuItem menuItemPaste = new JMenuItem("PASTE");
            menuItemPaste.setActionCommand(PASTE);
            menuItemPaste.addActionListener(new ActionListener(){
            
                @Override
                public void actionPerformed(ActionEvent e) {
                    // 貼付けメニュークリック時の処理
                    if(e.getActionCommand() == PASTE){
                        IClipBoard clip = ClipBoard.getInstanse();
                        DataRecord record = clip.Pull();
                        mainWindow.SetRecord(record);
                    }
                }
            });
            popupMenu.add(menuItemPaste);
            
            // 削除メニューの実装
            JMenuItem menuItemDelele = new JMenuItem("DELETE");
            menuItemDelele.setActionCommand(DELETE);
            menuItemDelele.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    // 削除メニュークリック時の処理
                    if(e.getActionCommand() == DELETE){
                        int index = mainWindow.getTable().getSelectedRow();
                        mainWindow.RemoveRecord(index);
                    }
                }
            });
            popupMenu.add(menuItemDelele);
            popupMenu.show(event.getComponent(), event.getX(), event.getY());
        }else if(event.getButton() == MouseEvent.BUTTON1){
            int indexs[] = this.mainWindow.getTable().getSelectedRows();
            // 選択している行がないときは何もしない
            if(indexs.length == 0){
                ButtonControl.getEditButtonInstanse().setEnabled(false);
                ButtonControl.getCopyButtonInstanse().setEnabled(false);
                ButtonControl.getDeleteButtonInstanse().setEnabled(false);
                return;
            }
            // 選択している行が合計の行だった場合
            for (int index : indexs) {
                if(index == 0){
                    ButtonControl.getEditButtonInstanse().setEnabled(false);
                    ButtonControl.getCopyButtonInstanse().setEnabled(false);
                    ButtonControl.getDeleteButtonInstanse().setEnabled(false);
                    return;
                }
            }
            ButtonControl.getEditButtonInstanse().setEnabled(true);
            ButtonControl.getCopyButtonInstanse().setEnabled(true);
            ButtonControl.getDeleteButtonInstanse().setEnabled(true);
        }
    }
}