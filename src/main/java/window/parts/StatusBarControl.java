package window.parts;

import javax.swing.JLabel;

public class StatusBarControl{
    private static JLabel statusBar;
    private static StatusBarControl control = new StatusBarControl();

    /**
     * コンストラクタ
     */
    private StatusBarControl(){
        statusBar = new JLabel("Ready...");
    }

    /**
     * ステータスバー
     * @return
     */
    public static JLabel getStatusBarInstanse(){
        return statusBar;
    }

    /**
     * ステータスバーにテキストを表示
     */
    public static void setStatusText(String text){
        statusBar.setText(text);
    }
}