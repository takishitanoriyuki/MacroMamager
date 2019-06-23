package dialog;

import java.awt.*;
import java.awt.event.*;
import java.util.List;

import javax.swing.*;

import dialog.parts.InputDialogParts;
import model.*;
import other.GetValueFromTextField;
import window.*;

/**
 * 入力ダイアログ
 */
public class InputDialog{
    JDialog Dialog;
    List<DataRecord> searchResult;

    /**
     * コンストラクタ
     */
    public InputDialog(IMainWindow mainWindow){
        inputDialog(mainWindow);
    }

    /**
     * ダイアログを表示する
     */
    public void Show(){
        Dialog.setVisible(true);
    }

    /**
     * ダイアログ画面を作成する
     */
    private void inputDialog(IMainWindow mainWindow){
        Dialog = new JDialog(mainWindow.getFrame(), true);
        Dialog.setSize(480, 240);
        Dialog.setLocationRelativeTo(null);

        // コントロールの作成
        Container contentPane = this.Dialog.getContentPane();
        JPanel panel = new JPanel();
        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        panel.setLayout(layout);

        // ラベルItem
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.25;
        gbc.weighty = 0.2;
        gbc.ipady = 3;
        gbc.fill = GridBagConstraints.BOTH;
        JLabel label1 = new JLabel("Item");
        label1.setPreferredSize(new Dimension(80, 22));
        layout.setConstraints(label1, gbc);

        // Itemテキストボックス
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 0.25;
        gbc.weighty = 0.2;
        JTextField ItemName = new JTextField(8);
        layout.setConstraints(ItemName, gbc);

        DefaultListModel<String> model = new DefaultListModel<String>();

        // 検索テキストボックス
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.weightx = 0.3;
        gbc.weighty = 0.2;
        JTextField SearchBox = InputDialogParts.getSearchTextField();
        SearchBox.addKeyListener(new KeyListener(){
        
            @Override
            public void keyTyped(KeyEvent e) {
                
            }
        
            @Override
            public void keyReleased(KeyEvent e) {
                
            }
        
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == 10){
                    doSearch(model);
                }
            }
        });
        layout.setConstraints(SearchBox, gbc);

        // ラベルProtein
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.25;
        gbc.weighty = 0.2;
        JLabel label2 = new JLabel("Protein");
        label2.setPreferredSize(new Dimension(80, 22));
        layout.setConstraints(label2, gbc);

        // Proteinテキストボックス
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 0.25;
        gbc.weighty = 0.2;
        JTextField ProteinValue = new JTextField(8);
        layout.setConstraints(ProteinValue, gbc);

        // 検索ボタン
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.weightx = 0.2;
        gbc.weighty = 0.2;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        JButton SearchButton = InputDialogParts.getSearchButton();
        SearchButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent event){
                doSearch(model);
            }
        });
        layout.setConstraints(SearchButton, gbc);
        
        // ラベルCarbohydrate
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 0.25;
        gbc.weighty = 0.2;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        JLabel label3 = new JLabel("Carbohydrate");
        label3.setPreferredSize(new Dimension(80, 22));
        layout.setConstraints(label3, gbc);

        // Carbohydrateテキストボックス
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.weightx = 0.25;
        gbc.weighty = 0.2;
        JTextField CarbohydrateValue = new JTextField(8);
        layout.setConstraints(CarbohydrateValue, gbc);

        // ラベルLipid
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0.25;
        gbc.weighty = 0.2;
        JLabel label4 = new JLabel("Lipid");
        label4.setPreferredSize(new Dimension(80, 22));
        layout.setConstraints(label4, gbc);

        // Lipidテキストボックス
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.weightx = 0.25;
        gbc.weighty = 0.2;
        JTextField LipidValue = new JTextField(8);
        layout.setConstraints(LipidValue, gbc);

        // ラベルCalorie
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.weightx = 0.25;
        gbc.weighty = 0.2;
        JLabel label5 = new JLabel("Calorie");
        label5.setPreferredSize(new Dimension(80, 22));
        layout.setConstraints(label5, gbc);

        // Calorieテキストボックス
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.weightx = 0.25;
        gbc.weighty = 0.2;
        JTextField CalorieValue = new JTextField(8);
        layout.setConstraints(CalorieValue, gbc);

        // 検索結果
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.gridheight = 4;
        gbc.weightx = 0.5;
        gbc.weighty = 0.6;
        JList<String> resultList = InputDialogParts.getSearchResultList();
        resultList.setModel(model);
        resultList.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                int index = resultList.getSelectedIndex();
                DataRecord record = searchResult.get(index);
                ItemName.setText(record.ItemName);
                ProteinValue.setText(String.format("%.2f", record.Protein));
                CarbohydrateValue.setText(String.format("%.2f", record.Carbohydrate));
                LipidValue.setText(String.format("%.2f", record.Lipid));
                CalorieValue.setText(String.format("%.2f", record.Calorie));
            }
        });
        JScrollPane sp = new JScrollPane();
        sp.getViewport().setView(resultList);
        sp.setPreferredSize(new Dimension(200, 80));
        layout.setConstraints(sp, gbc);

        // 追加ボタン
        JButton registerButton = new JButton("ADD");
        // 登録ボタン押下時の処理
        registerButton.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent event){
                String inputItemName = ItemName.getText();
                DataRecord record = GetValueFromTextField.GetValue(Dialog, ProteinValue, CarbohydrateValue, LipidValue, CalorieValue);
                if(record == null){
                    return;
                }
                record.ItemName = inputItemName;
                mainWindow.SetRecord(record);
                // 履歴を残す
                IHistoryData history = HistoryData.getInstanse();
                history.add(record);
                history.save();

                // 検索ボックスを殻にする
                SearchBox.setText("");

                // ダイアログを閉じる
                Dialog.setVisible(false);
            }
        });

        // コントロールの配置
        panel.add(label1);
        panel.add(ItemName);
        panel.add(SearchBox);
        panel.add(SearchButton);
        panel.add(label2);
        panel.add(ProteinValue);
        panel.add(sp);
        panel.add(label3);
        panel.add(CarbohydrateValue);
        panel.add(label4);
        panel.add(LipidValue);
        panel.add(label5);
        panel.add(CalorieValue);
        contentPane.add(panel, BorderLayout.CENTER);
        Dialog.add(registerButton, BorderLayout.SOUTH);
        Dialog.setFocusTraversalPolicy(new FocusTraversalPolicy(){
        
            @Override
            public Component getLastComponent(Container aContainer) {
                return ProteinValue;
            }
        
            @Override
            public Component getFirstComponent(Container aContainer) {
                return ItemName;
            }
        
            @Override
            public Component getDefaultComponent(Container aContainer) {
                return ItemName;
            }
        
            @Override
            public Component getComponentBefore(Container aContainer, Component aComponent) {
                if (aComponent == ItemName) {
                    return CalorieValue;
                } else if (aComponent == CalorieValue) {
                    return CarbohydrateValue;
                } else if (aComponent == CarbohydrateValue) {
                    return LipidValue;
                } else if (aComponent == LipidValue) {
                    return ProteinValue;
                } else if (aComponent == ProteinValue) {
                    return ItemName;
                } else {
                    return null;
                }
            }
        
            @Override
            public Component getComponentAfter(Container aContainer, Component aComponent) {
                if (aComponent == ItemName) {
                    return ProteinValue;
                } else if (aComponent == ProteinValue) {
                    return LipidValue;
                } else if (aComponent == LipidValue) {
                    return CarbohydrateValue;
                } else if (aComponent == CarbohydrateValue) {
                    return CalorieValue;
                } else if (aComponent == CalorieValue) {
                    return ItemName;
                } else {
                    return null;
                }
            }
        });
    }

    private void doSearch(DefaultListModel<String> model){
        String word = InputDialogParts.getSearchTextField().getText();
        IHistoryData history = HistoryData.getInstanse();
        searchResult = history.search(word);
        model.removeAllElements();
        for (DataRecord record : searchResult) {
            model.addElement(record.ItemName);
        }
        JButton button = InputDialogParts.getInsertButton();
        button.setEnabled(false);
    }
}
