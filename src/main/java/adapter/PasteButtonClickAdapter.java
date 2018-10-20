package adapter;

import java.awt.event.*;

import model.*;
import window.*;
import window.parts.StatusBarControl;

public class PasteButtonClickAdapter extends MouseAdapter {
    private IMainWindow mainWindow;

    /**
     * コンストラクタ
     */
    public PasteButtonClickAdapter(IMainWindow main){
        super();
        this.mainWindow = main;
    }

    /**
     * ボタンクリック処理
     */
    public void mouseClicked(MouseEvent event){
        IClipBoard clip = ClipBoard.getInstanse();
        if(clip.isStore() == false){
            StatusBarControl.setStatusText("Can not paste...");
            return;
        }
        DataRecord record = clip.Pull();
        this.mainWindow.SetRecord(record);
        StatusBarControl.setStatusText("Pasted...");
    }
}