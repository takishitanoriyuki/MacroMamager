package window.parts;

import javax.swing.JComboBox;

public class DateComboBox {
    /**
     * 年コンボボックスを作成する
     * @param year
     * @return
     */
    public static JComboBox<String> CreateYearComboBox(int year){
        String[] yearList = new String[] {String.format("%4d", year - 1), String.format("%4d", year), String.format("%4d", year + 1)};
        JComboBox<String> combo = new JComboBox<String>(yearList);
        return combo;
    }

    /**
     * 月コンボボックスを作成する
     * @return
     */
    public static JComboBox<String> CreateMonthComboBox(){
        String[] monthList = new String[12];
        for (int i = 1; i <= 12; i++) {
            monthList[i-1] = String.format("%d", i);
        }
        JComboBox<String> combo = new JComboBox<String>(monthList);
        return combo;
    }

    /**
     * 日コンボボックスを作成する
     * @return
     */
    public static JComboBox<String> CreateDayComboBox(){
        String[] dayList = new String[31];
        for (int i = 1; i <= 31; i++) {
            dayList[i-1] = String.format("%d", i);
        }
        JComboBox<String> combo = new JComboBox<String>(dayList);
        return combo;
    }

    /**
     * コンボボックスから選択されている値を数値で取得する
     * @param combo
     * @return
     */
    public static int GetValueFromComboBox(JComboBox<String> combo){
        String text = (String)combo.getSelectedItem();
        int value = Integer.parseInt(text);
        return value;
    }
}