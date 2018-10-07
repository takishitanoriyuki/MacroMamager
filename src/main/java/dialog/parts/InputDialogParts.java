package dialog.parts;

import javax.swing.*;

public class InputDialogParts {
    // 検索テキストフィールド
    private static JTextField searchTextField;
    private static JButton searchButton;
    private static JButton inputButton;
    private static JList<String> searchResultList;
    private static InputDialogParts dialogParts = new InputDialogParts();

    /**
     * コンストラクタ
     */
    private InputDialogParts(){
        searchTextField = new JTextField(8);
        searchButton = new JButton("Search");
        inputButton = new JButton("Input");
        searchResultList = new JList<String>();
    }

    /**
     * 検索テキストフィールドを返す
     * @return
     */
    public static JTextField getSearchTextField(){
        return searchTextField;
    }

    /**
     * 検索ボタンを返す
     * @return
     */
    public static JButton getSearchButton(){
        return searchButton;
    }

    /**
     * 挿入ボタンを返す
     * @return
     */
    public static JButton getInsertButton(){
        return inputButton;
    }

    /**
     * 検索結果リストを返す
     * @return
     */
    public static JList<String> getSearchResultList(){
        return searchResultList;
    }
}
