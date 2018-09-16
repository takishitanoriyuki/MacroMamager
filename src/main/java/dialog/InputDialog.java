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
        Dialog.setSize(320, 240);
        Dialog.setLocationRelativeTo(null);

        // コントロールの作成
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2));
        JLabel label1 = new JLabel("Item");
        JLabel label2 = new JLabel("Protein");
        JLabel label3 = new JLabel("Carbohydrate");
        JLabel label4 = new JLabel("Lipid");
        JLabel label5 = new JLabel("Calorie");
        JTextField ItemName = new JTextField();
        JTextField ProteinValue = new JTextField();
        JTextField CarbohydrateValue = new JTextField();
        JTextField LipidValue = new JTextField();
        JTextField CalorieValue = new JTextField();
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
        panel.add(label2);
        panel.add(ProteinValue);
        panel.add(label3);
        panel.add(CarbohydrateValue);
        panel.add(label4);
        panel.add(LipidValue);
        panel.add(label5);
        panel.add(CalorieValue);
        Dialog.add(panel, BorderLayout.CENTER);
        Dialog.add(registerButton, BorderLayout.SOUTH);
    }
}
