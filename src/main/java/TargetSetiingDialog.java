import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.*;
import javax.swing.*;

public class TargetSetiingDialog {
    JDialog Dialog;
    /**
     * コンストラクタ
     */
    public TargetSetiingDialog(JFrame frame, MainWindow mainWindow){
        targetSetiingDialog(frame, mainWindow);
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
    private void targetSetiingDialog(JFrame frame,MainWindow mainWindow){
        IBasicData basicData = new BasicData();

        Dialog = new JDialog(frame);
        Dialog.setSize(320, 240);
        Dialog.setLocationRelativeTo(null);

        // コントロールの作成
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));
        JLabel label1 = new JLabel("Protein");
        JLabel label2 = new JLabel("Carbohydrate");
        JLabel label3 = new JLabel("Lipid");
        JLabel label4 = new JLabel("Calorie");
        JTextField ProteinValue = new JTextField(String.format("%.2f", basicData.getProtein()));
        JTextField CarbohydrateValue = new JTextField(String.format("%.2f", basicData.getCarbohydrate()));
        JTextField LipidValue = new JTextField(String.format("%.2f", basicData.getLipid()));
        JTextField CalorieValue = new JTextField(String.format("%.2f", basicData.getCalorie()));
        JButton registerButton = new JButton("SET");

        // 登録ボタン押下時の処理
        registerButton.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent event){
                float inputProteinValue;
                float inputCarbohydrateValue;
                float inputLipidValue;
                float inputCalorieValue;
                //パラメータチェック
                try {
                    inputProteinValue = Float.parseFloat(ProteinValue.getText());
                } catch (NumberFormatException e) {
                    JOptionPane joption = new JOptionPane();
                    joption.showMessageDialog(Dialog, "Please input proteins by numbers.");
                    return;
                }
                try{
                    inputCarbohydrateValue = Float.parseFloat(CarbohydrateValue.getText());
                } catch(NumberFormatException e){
                    JOptionPane joption = new JOptionPane();
                    joption.showMessageDialog(Dialog, "Please input carbohydrate by numbers.");
                    return;
                }
                try{
                    inputLipidValue = Float.parseFloat(LipidValue.getText());
                }catch(NumberFormatException e){
                    JOptionPane joption = new JOptionPane();
                    joption.showMessageDialog(Dialog, "Please input lipid by numbers.");
                    return;
                }
                try{
                    inputCalorieValue = Float.parseFloat(CalorieValue.getText());
                }catch(NumberFormatException e){
                    JOptionPane joption = new JOptionPane();
                    joption.showMessageDialog(Dialog, "Please input calorie by numbers.");
                    return;
                }
                // データの登録
                basicData.setProtein(inputProteinValue);
                basicData.setCarbohydrate(inputCarbohydrateValue);
                basicData.setLipid(inputLipidValue);
                basicData.setCalorie(inputCalorieValue);
                basicData.Save();

                // ダイアログを閉じる
                Dialog.setVisible(false);
            }
        });
        // コントロールの配置
        panel.add(label1);
        panel.add(ProteinValue);
        panel.add(label2);
        panel.add(CarbohydrateValue);
        panel.add(label3);
        panel.add(LipidValue);
        panel.add(label4);
        panel.add(CalorieValue);
        Dialog.add(panel, BorderLayout.CENTER);
        Dialog.add(registerButton, BorderLayout.SOUTH);
    }
}