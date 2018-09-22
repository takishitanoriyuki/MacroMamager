package adapter;

import java.awt.event.*;

import dialog.*;
import model.*;
import window.*;

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
        int index = this.mainWindow.getTable().getSelectedRow() - 1;
        DataRecord record = this.mainWindow.getDataRecords().get(index);
        EditDialog dialog = new EditDialog(this.mainWindow, index, record);
        dialog.Show();
    }
}