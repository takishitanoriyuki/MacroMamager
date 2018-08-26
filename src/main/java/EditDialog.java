import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * 入力ダイアログ
 */
public class EditDialog{
    JDialog Dialog;

    /**
     * コンストラクタ
     */
    public EditDialog(JFrame frame, MainWindow mainWindow, int index, DataRecord record){
        editDialog(frame, mainWindow, index, record);
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
    private void editDialog(JFrame frame, MainWindow mainWindow, int index, DataRecord record){
        Dialog = new JDialog(frame);
        Dialog.setSize(320, 240);
        Dialog.setLocationRelativeTo(null);

        // コントロールの作成
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2));
        JLabel label1 = new JLabel("品名");
        JLabel label2 = new JLabel("タンパク質");
        JLabel label3 = new JLabel("炭水化物");
        JLabel label4 = new JLabel("脂質");
        JLabel label5 = new JLabel("カロリー");
        JTextField ItemName = new JTextField(record.ItemName);
        JTextField ProteinValue = new JTextField(String.format("%.2f", record.Protein));
        JTextField CarbohydrateValue = new JTextField(String.format("%.2f", record.Carbohydrate));
        JTextField LipidValue = new JTextField(String.format("%.2f", record.Lipid));
        JTextField CalorieValue = new JTextField(String.format("%.2f", record.Calorie));
        JButton registerButton = new JButton("修正");

        // 登録ボタン押下時の処理
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
                } catch (NumberFormatException e) {
                    JOptionPane joption = new JOptionPane();
                    joption.showMessageDialog(Dialog, "タンパク質は数字で入力してください。");
                    return;
                }
                try{
                    inputCarbohydrateValue = Float.parseFloat(CarbohydrateValue.getText());
                } catch(NumberFormatException e){
                    JOptionPane joption = new JOptionPane();
                    joption.showMessageDialog(Dialog, "炭水化物は数字で入力してください。");
                    return;
                }
                try{
                    inputLipidValue = Float.parseFloat(LipidValue.getText());
                }catch(NumberFormatException e){
                    JOptionPane joption = new JOptionPane();
                    joption.showMessageDialog(Dialog, "脂質は数字で入力してください。");
                    return;
                }
                try{
                    inputCalorieValue = Float.parseFloat(CalorieValue.getText());
                }catch(NumberFormatException e){
                    JOptionPane joption = new JOptionPane();
                    joption.showMessageDialog(Dialog, "カロリーは数字で入力してください。");
                    return;
                }
                // データの登録
                DataRecord record = new DataRecord(inputItemName, inputProteinValue, inputCarbohydrateValue, inputLipidValue, inputCalorieValue);
                mainWindow.EditRecord(index, record);

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
