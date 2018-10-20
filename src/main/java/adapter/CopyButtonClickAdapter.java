package adapter;

import java.awt.event.*;

import model.*;
import window.*;
import window.parts.StatusBarControl;

public class CopyButtonClickAdapter extends MouseAdapter {
    private IMainWindow mainWindow;

    /**
     * コンストラクタ
     */
    public CopyButtonClickAdapter(IMainWindow main){
        super();
        this.mainWindow = main;
    }

    /**
     * ボタンクリック処理
     */
    public void mouseClicked(MouseEvent event){
        int select = this.mainWindow.getTable().getSelectedRow();
        if(select < 1){
            StatusBarControl.setStatusText("Can not copy...");
            return;
        }
        DataRecord record = this.mainWindow.getDataRecords().get(select - 1);
        IClipBoard clip = ClipBoard.getInstanse();
        clip.Store(record);
        StatusBarControl.setStatusText("Copied...");
    }
}