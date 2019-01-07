package dialog;

import model.DataRecord;

import javax.swing.*;

public interface ITargetSettingDialog {
    /**
     * JDialogを返す
     */
    JDialog getDialog();

    /**
     * 基本データを設定する
     * @param record
     */
    void setBasicData(DataRecord record);
}
