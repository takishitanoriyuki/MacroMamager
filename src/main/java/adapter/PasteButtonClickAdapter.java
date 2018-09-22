package adapter;

import java.awt.event.*;

import dialog.*;
import model.*;
import window.*;

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
        DataRecord record = clip.Pull();
        this.mainWindow.SetRecord(record);
    }
}