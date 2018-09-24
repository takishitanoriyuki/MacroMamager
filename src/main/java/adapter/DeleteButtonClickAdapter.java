package adapter;

import java.awt.event.*;

import dialog.*;
import model.*;
import window.*;

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
        int index = this.mainWindow.getTable().getSelectedRow();
        this.mainWindow.RemoveRecord(index);
    }
}