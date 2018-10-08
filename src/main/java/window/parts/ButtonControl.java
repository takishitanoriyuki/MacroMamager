package window.parts;

import javax.swing.*;

public class ButtonControl {
    private static JButton addButton;
    private static JButton editButton;
    private static JButton copyButton;
    private static JButton pasteButton;
    private static JButton deleteButton;
    private static ButtonControl control = new ButtonControl();

    /**
     * コンストラクタ
     * @param main
     */
    private ButtonControl(){
        addButton = new JButton("ADD");
        editButton = new JButton("EDIT");
        copyButton = new JButton("COPY");
        pasteButton = new JButton("PASTE");
        deleteButton = new JButton("DELETE");
    }

    /**
     * ADDボタンのインスタンス取得
     */
    public static JButton getAddButtonInstanse(){
        return addButton;
    }

    /**
     * EDITボタンのインスタンス取得
     */
    public static JButton getEditButtonInstanse(){
        return editButton;
    }

    /**
     * COPYボタンのインスタンス取得
     */
    public static JButton getCopyButtonInstanse(){
        return copyButton;
    }

    /**
     * PASTEボタンのインスタンス取得
     */
    public static JButton getPasteButtonInstanse(){
        return pasteButton;
    }

    /**
     * DELETEボタンのインスタンス取得
     */
    public static JButton getDeleteButtonInstanse(){
        return deleteButton;
    }
}