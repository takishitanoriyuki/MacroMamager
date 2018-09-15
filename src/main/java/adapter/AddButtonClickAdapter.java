package adapter;

import java.awt.event.*;

import dialog.*;
import window.*;

public class AddButtonClickAdapter extends MouseAdapter {
    private IMainWindow mainWindow;

    /**
     * コンストラクタ
     */
    public AddButtonClickAdapter(IMainWindow main){
        this.mainWindow = main;
    }

    /**
     * ボタンクリック時の処理
     */
    public void mouseClicked(MouseEvent event){
        InputDialog dialog = new InputDialog(this.mainWindow);
        dialog.Show();
    }

}