package adapter;

import java.awt.event.*;

import dialog.*;
import model.*;
import window.*;
import window.parts.StatusBarControl;

public class EditButtonClickAdapter extends MouseAdapter {
    private IMainWindow mainWindow;

    /**
     * コンストラクタ
     */
    public EditButtonClickAdapter(IMainWindow main){
        super();
        this.mainWindow = main;
    }

    /**
     * ボタンクリック処理
     */
    public void mouseClicked(MouseEvent event){
        int select = this.mainWindow.getTable().getSelectedRow();
        if(select < 1){
            StatusBarControl.setStatusText("Can not edit...");
            return;
        }
        DataRecord record = this.mainWindow.getDataRecords().get(select - 1);
        EditDialog dialog = new EditDialog(this.mainWindow, select - 1, record);
        dialog.Show();
        StatusBarControl.setStatusText("Edited...");
    }
}