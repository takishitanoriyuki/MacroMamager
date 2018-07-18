import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class InputDialog{
    JDialog Dialog;

    public InputDialog(JFrame frame, MainWindow mainWindow){
        Dialog = new JDialog(frame);
        Dialog.setSize(320, 240);
        Dialog.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2));
        JLabel label1 = new JLabel("品名");
        JLabel label2 = new JLabel("タンパク質");
        JLabel label3 = new JLabel("炭水化物");
        JLabel label4 = new JLabel("脂質");
        JLabel label5 = new JLabel("カロリー");
        JTextField ItemName = new JTextField();
        JTextField ProteinValue = new JTextField();
        JTextField CarbohydrateValue = new JTextField();
        JTextField LipidValue = new JTextField();
        JTextField CalorieValue = new JTextField();
        JButton registerButton = new JButton("登録");

        //登録ボタン押下時の処理
        registerButton.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent event){
                String inputItemName = ItemName.getText();
                float inputProteinValue;
                float inputCarbohydrateValue;
                float inputLipidValue;
                float inputCalorieValue;
                //パラメータチェック
                try {
                    inputProteinValue = Float.parseFloat(ProteinValue.getText());
                    inputCarbohydrateValue = Float.parseFloat(CarbohydrateValue.getText());
                    inputLipidValue = Float.parseFloat(LipidValue.getText());
                    inputCalorieValue = Float.parseFloat(CalorieValue.getText());
                    DataRecord record = new DataRecord(inputItemName, inputProteinValue, inputCarbohydrateValue, inputLipidValue, inputCalorieValue);
                    mainWindow.SetRecord(record);
                    Dialog.setVisible(false);
                } catch (NumberFormatException e) {
                    //Todo
                }
            }
        });
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

    public void Show(){
        Dialog.setVisible(true);
    }
} 