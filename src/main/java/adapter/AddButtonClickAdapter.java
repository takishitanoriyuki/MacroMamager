package adapter;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;

import dialog.InputDialog;
import window.MainWindow;

public class AddButtonClickAdapter extends MouseAdapter {
    private JFrame frame;
    private MainWindow mainWindow;

    /**
     * コンストラクタ
     */
    public AddButtonClickAdapter(JFrame frame, MainWindow main){
        this.frame = frame;
        this.mainWindow = main;
    }

    /**
     * ボタンクリック時の処理
     */
    public void mouseClicked(MouseEvent event){
        InputDialog dialog = new InputDialog(frame, mainWindow);
        dialog.Show();
    }

}