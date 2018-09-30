package dialog;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import model.DataRecord;
import other.GetValueFromTextField;
import window.*;

/**
 * 入力ダイアログ
 */
public class InputDialog{
    JDialog Dialog;

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

        // 検索テキストボックス
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.weightx = 0.3;
        gbc.weighty = 0.2;
        JTextField SearchBox = new JTextField(8);
        layout.setConstraints(SearchBox, gbc);

        // 検索ボタン
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.weightx = 0.2;
        gbc.weighty = 0.2;
        JButton SearchButton = new JButton("Search");
        layout.setConstraints(SearchButton, gbc);

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

        // 検索結果
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.gridheight = 3;
        gbc.weightx = 0.5;
        gbc.weighty = 0.6;
        JList list = new JList();
        JScrollPane sp = new JScrollPane();
        sp.getViewport().setView(list);
        sp.setPreferredSize(new Dimension(200, 80));
        layout.setConstraints(sp, gbc);
        
        // ラベルCarbohydrate
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0.25;
        gbc.weighty = 0.2;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        JLabel label3 = new JLabel("Carbohydrate");
        label3.setPreferredSize(new Dimension(80, 22));
        layout.setConstraints(label3, gbc);

        // Carbohydrateテキストボックス
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.weightx = 0.25;
        gbc.weighty = 0.2;
        JTextField CarbohydrateValue = new JTextField(8);
        layout.setConstraints(CarbohydrateValue, gbc);

        // ラベルLipid
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 0.25;
        gbc.weighty = 0.2;
        JLabel label4 = new JLabel("Lipid");
        label4.setPreferredSize(new Dimension(80, 22));
        layout.setConstraints(label4, gbc);

        // Lipidテキストボックス
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.weightx = 0.25;
        gbc.weighty = 0.2;
        JTextField CalorieValue = new JTextField(8);
        layout.setConstraints(CalorieValue, gbc);

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
        JTextField LipidValue = new JTextField(8);
        layout.setConstraints(LipidValue, gbc);

        // 入力ボタン
        gbc.gridx = 2;
        gbc.gridy = 4;
        gbc.weightx = 0.5;
        gbc.weighty = 0.2;
        gbc.gridwidth = 2;
        JButton InputButton = new JButton("Input");
        layout.setConstraints(InputButton, gbc);

        // 追加ボタン
        JButton registerButton = new JButton("ADD");

        // 登録ボタン押下時の処理
        registerButton.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent event){
                String inputItemName = ItemName.getText();
                DataRecord record = GetValueFromTextField.GetValue(Dialog, ProteinValue, CarbohydrateValue, LipidValue, CalorieValue);
                record.ItemName = inputItemName;
                mainWindow.SetRecord(record);

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
        panel.add(InputButton);
        contentPane.add(panel, BorderLayout.CENTER);
        Dialog.add(registerButton, BorderLayout.SOUTH);
    }
}
