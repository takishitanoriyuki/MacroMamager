package adapter;

import java.awt.event.*;

import dialog.*;
import model.*;
import window.*;
import window.parts.StatusBarControl;

public class DeleteButtonClickAdapter extends MouseAdapter {
    private IMainWindow mainWindow;

    /**
     * コンストラクタ
     */
    public DeleteButtonClickAdapter(IMainWindow main){
        super();
        this.mainWindow = main;
    }

    /**
     * ボタンクリック処理
     */
    public void mouseClicked(MouseEvent event){
        int select = this.mainWindow.getTable().getSelectedRow();
        if(select < 1){
            StatusBarControl.setStatusText("Can not delete...");
            return;
        }
        this.mainWindow.RemoveRecord(select);
        StatusBarControl.setStatusText("Deleted...");
    }
}