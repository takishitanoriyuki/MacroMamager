package adapter;

import java.awt.event.*;

import dialog.*;
import window.*;
import window.parts.StatusBarControl;

public class AddButtonClickAdapter extends MouseAdapter {
    private IMainWindow mainWindow;

    /**
     * コンストラクタ
     */
    public AddButtonClickAdapter(IMainWindow main){
        super();
        this.mainWindow = main;
    }

    /**
     * ボタンクリック時の処理
     */
    public void mouseClicked(MouseEvent event){
        InputDialog dialog = new InputDialog(this.mainWindow);
        dialog.Show();
        StatusBarControl.setStatusText("Added...");
    }

}