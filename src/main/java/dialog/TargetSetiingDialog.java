package dialog;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import dialog.parts.CalcDialog;
import model.*;
import window.*;

public class TargetSetiingDialog implements ITargetSettingDialog{
    JDialog Dialog;
    TargetSetiingDialog TargetSetiingDialog;
    /**
     * コンストラクタ
     */
    public TargetSetiingDialog(IMainWindow mainWindow){
        TargetSetiingDialog = this;
        targetSetiingDialog(mainWindow);
    }
    
    /**
     * ダイアログを表示する
     */
    public void Show(){
        Dialog.setVisible(true);
    }

    public JDialog getDialog(){
        return Dialog;
    }

    /**
     * ダイアログ画面を作成する
     */
    private void targetSetiingDialog(IMainWindow mainWindow){
        IBasicData basicData = BasicData.getInstanse();

        Dialog = new JDialog(mainWindow.getFrame(), true);
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
        JButton calcButton = new JButton("CALC");
        JButton registerButton = new JButton("SET");

        // 計算ボタン押下時の処理
        calcButton.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent event) {
                CalcDialog calsDialog = new CalcDialog(TargetSetiingDialog);
                calsDialog.Show();
            }
        });

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
                mainWindow.UpdateBasicData();

                // ダイアログを閉じる
                Dialog.setVisible(false);
            }
        });
        // コントロールの配置
        Dialog.add(calcButton, BorderLayout.NORTH);
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