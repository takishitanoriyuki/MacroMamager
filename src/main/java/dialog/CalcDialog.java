package dialog;

import model.DataAccess;
import model.DataRecord;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.xml.crypto.Data;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalcDialog {
    private JDialog Dialog;
    private ITargetSettingDialog Target;

    private final int PARAM_ERR = -1;
    // 性別
    private final int SEX_MAN = 1;
    private final int SEX_WOMAN= 2;

    // アクティブ度
    private final int ACTIVITY_LOW = 1;
    private final int ACTIVITY_MIDDLE = 2;
    private final int ACTIVITY_HIGH = 3;

    // 目的
    private final int PURPOSE_WEIGHT_LOSS = 1;
    private final int PURPOSE_MAINTENANCE = 2;
    private final int PURPOSE_INCREASE = 3;

    /**
     * コンストラクタ
     * @param dialog
     */
    public CalcDialog(ITargetSettingDialog dialog){
        this.Target = dialog;
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

        // ボタン
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 2));
        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            /**
             * キャンセルボタン押下時の処理
             * @param e
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                Dialog.setVisible(false);
            }
        });
        JButton calcButton = new JButton("Calc");
        calcButton.addActionListener(new ActionListener() {
            /**
             * 計算ボタン押下時の処理
             * @param e
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                // 入力パラメータチェックと取得
                // 性別
                int sex = getSexInputParameter(manRadioButton, womanRadioButton);
                if(sex == PARAM_ERR){

                }
                // 身長
                double height = getInputParameter(heightText);
                if(height == PARAM_ERR){
                    return;
                }
                // 体重
                double weight = getInputParameter(weightText);
                if(weight == PARAM_ERR){
                    return;
                }
                // 年齢
                double age = getInputParameter(ageText);
                if(age == PARAM_ERR){
                    return;
                }
                // アクティブ度
                int activity = getActivityInputParameter(lowActiveRadioButton, middleActiveRadioButton, highActiveRadioButton);
                if(activity == PARAM_ERR){

                }
                // 目的
                int purpose = getPurposeInputParameter(weightLossRadioButton, maintenanceRadioButton, increaseRadioButton);
                if(purpose == PARAM_ERR){

                }

                // 栄養素を計算する
                DataRecord record = calcBasicData(sex, height, weight, age, activity, purpose);
                // 結果を反映
                Target.setBasicData(record);

                Dialog.setVisible(false);
            }
        });

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
        buttonPanel.add(calcButton);
        buttonPanel.add(cancelButton);
        Dialog.add(buttonPanel, BorderLayout.SOUTH);
    }

    /**
     * 性別を取得する
     * @param manButton
     * @param womanButton
     * @return
     */
    private int getSexInputParameter(JRadioButton manButton, JRadioButton womanButton){
        boolean man = manButton.isSelected();
        boolean woman = womanButton.isSelected();
        if(man == true){
            return SEX_MAN;
        }else if(woman == true){
            return SEX_WOMAN;
        }else{
            return PARAM_ERR;
        }
    }

    /**
     * テキストフィールドから値を取得する
     * @param textField
     * @return
     */
    private double getInputParameter(JTextField textField){
        try{
            double value = Float.parseFloat(textField.getText());
            return value;
        }catch (NumberFormatException e){
            return PARAM_ERR;
        }
    }

    /**
     * アクティブ度を取得する
     * @param lowButton
     * @param middleButton
     * @param highButton
     * @return
     */
    private int getActivityInputParameter(JRadioButton lowButton, JRadioButton middleButton, JRadioButton highButton){
        boolean low = lowButton.isSelected();
        boolean middle = middleButton.isSelected();
        boolean high = highButton.isSelected();
        if(low == true){
            return ACTIVITY_LOW;
        }else if(middle == true) {
            return ACTIVITY_MIDDLE;
        }else if(high == true){
            return ACTIVITY_HIGH;
        }else{
            return PARAM_ERR;
        }
    }

    /**
     * 目的を取得する
     * @param weightLossRadioButton
     * @param maintenanceRadioButton
     * @param increaseRadioButton
     * @return
     */
    private int getPurposeInputParameter(JRadioButton weightLossRadioButton, JRadioButton maintenanceRadioButton, JRadioButton increaseRadioButton){
        boolean weightLoss = weightLossRadioButton.isSelected();
        boolean maintenance = maintenanceRadioButton.isSelected();
        boolean increase = increaseRadioButton.isSelected();
        if(weightLoss == true){
            return PURPOSE_WEIGHT_LOSS;
        }else if(maintenance == true) {
            return PURPOSE_MAINTENANCE;
        }else if(increase == true){
            return PURPOSE_INCREASE;
        }else{
            return PARAM_ERR;
        }
    }

    /**
     * 栄養素を計算する
     * @param sex
     * @param height
     * @param weight
     * @param age
     * @param activity
     * @param perpose
     * @return
     */
    private DataRecord calcBasicData(int sex, double height, double weight, double age, int activity, int perpose){
        // 基礎代謝を計算する
        double basic;
        if(sex == SEX_MAN){
            basic = 10 * weight + 6.25 * height - 5 * age + 5;
        }else{
            basic = 10 * weight + 6.25 * height - 5 * age - 161;
        }

        if(activity == ACTIVITY_LOW){
            basic = basic * 1.2;
        }else if(activity == ACTIVITY_MIDDLE){
            basic = basic * 1.55;
        }else{
            basic = basic * 1.725;
        }

        if(perpose == PURPOSE_WEIGHT_LOSS){
            basic = basic * 0.8;
        }else if(perpose == PURPOSE_INCREASE){
            basic = basic * 1.2;
        }

        DataRecord record = new DataRecord();
        record.Protein = weight * 2;
        record.Lipid = (basic * 0.25) / 9;
        record.Carbohydrate = (basic - record.Protein * 4 - record.Lipid * 9) / 4;
        record.Calorie = basic;

        return record;
    }
}
