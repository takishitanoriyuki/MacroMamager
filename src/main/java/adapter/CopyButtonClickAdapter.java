package adapter;

import java.awt.event.*;

import dialog.*;
import model.*;
import window.*;
import window.parts.ButtonControl;

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
        int index = this.mainWindow.getTable().getSelectedRow() - 1;
        DataRecord record = this.mainWindow.getDataRecords().get(index);
        IClipBoard clip = ClipBoard.getInstanse();
        clip.Store(record);
        ButtonControl.getPasteButtonInstanse().setEnabled(true);
    }
}