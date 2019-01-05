package dialog.parts;

import dialog.ITargetSettingDialog;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class CalcDialog {
    JDialog Dialog;

    /**
     * コンストラクタ
     * @param dialog
     */
    public CalcDialog(ITargetSettingDialog dialog){
        calcDialog(dialog);
    }

    /**
     * ダイアログを表示する
     */
    public void Show(){
        Dialog.setVisible(true);
    }

    /**
     * ダイアログを作成する
     * @param dialog
     */
    private void calcDialog(ITargetSettingDialog dialog){
        Dialog = new JDialog(dialog.getDialog(), true);
        Dialog.setSize(400, 440);
        Dialog.setLocationRelativeTo(null);

        // ベースのパネル
        JPanel basePanel = new JPanel();
        basePanel.setLayout(new GridLayout(3, 1));

        // 基礎代謝のパネル
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(4, 2));
        LineBorder line = new LineBorder(Color.BLACK, 1);
        TitledBorder titleBorder1 = new TitledBorder(line, "BMR");
        topPanel.setBorder(titleBorder1);

        JLabel label1 = new JLabel("Sex");
        JLabel label2 = new JLabel("Height");
        JLabel label3 = new JLabel("Weight");
        JLabel label4 = new JLabel("Age");
        JPanel sexPanel = new JPanel(new GridLayout(1, 2));
        ButtonGroup buttonGroup1 = new ButtonGroup();
        JRadioButton manRadioButton = new JRadioButton("Man");
        JRadioButton womanRadioButton = new JRadioButton("Woman");
        buttonGroup1.add(manRadioButton);
        buttonGroup1.add(womanRadioButton);
        manRadioButton.setSelected(true);
        JTextField heightText = new JTextField();
        JTextField weightText = new JTextField();
        JTextField ageText = new JTextField();

        // アクティブ度のパネル
        JPanel middlePanel = new JPanel();
        middlePanel.setLayout(new GridLayout(3, 1));
        TitledBorder titleBorder2 = new TitledBorder(line, "Activity");
        middlePanel.setBorder(titleBorder2);

        ButtonGroup buttonGroup2 = new ButtonGroup();
        JRadioButton lowActiveRadioButton = new JRadioButton("Low Active");
        JRadioButton middleActiveRadioButton = new JRadioButton("Middle Active");
        JRadioButton highActiveRadioButton = new JRadioButton("High Active");
        buttonGroup2.add(lowActiveRadioButton);
        buttonGroup2.add(middleActiveRadioButton);
        buttonGroup2.add(highActiveRadioButton);
        middleActiveRadioButton.setSelected(true);

        // アクティブ度のパネル
        JPanel underPanel = new JPanel();
        underPanel.setLayout(new GridLayout(3, 1));
        TitledBorder titleBorder3 = new TitledBorder(line, "Purpose");
        underPanel.setBorder(titleBorder3);

        ButtonGroup buttonGroup3 = new ButtonGroup();
        JRadioButton weightLossRadioButton = new JRadioButton("Weight loss");
        JRadioButton maintenanceRadioButton = new JRadioButton("Maintenance");
        JRadioButton increaseRadioButton = new JRadioButton("Increase");
        buttonGroup3.add(weightLossRadioButton);
        buttonGroup3.add(maintenanceRadioButton);
        buttonGroup3.add(increaseRadioButton);
        maintenanceRadioButton.setSelected(true);

        // 計算ボタン
        JButton calcButton = new JButton("Calc");

        // コントロールの配置
        sexPanel.add(manRadioButton);
        sexPanel.add(womanRadioButton);
        topPanel.add(label1);
        topPanel.add(sexPanel);
        topPanel.add(label2);
        topPanel.add(heightText);
        topPanel.add(label3);
        topPanel.add(weightText);
        topPanel.add(label4);
        topPanel.add(ageText);
        basePanel.add(topPanel);
        middlePanel.add(lowActiveRadioButton);
        middlePanel.add(middleActiveRadioButton);
        middlePanel.add(highActiveRadioButton);
        basePanel.add(middlePanel);
        underPanel.add(weightLossRadioButton);
        underPanel.add(maintenanceRadioButton);
        underPanel.add(increaseRadioButton);
        basePanel.add(underPanel);
        Dialog.add(basePanel, BorderLayout.CENTER);
        Dialog.add(calcButton, BorderLayout.SOUTH);
    }
}
