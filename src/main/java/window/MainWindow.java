package window;

import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;
import java.util.List;

import javax.swing.*;

import adapter.*;
import model.*;
import window.parts.*;

/**
 * メインウインドウ
 */
public class MainWindow implements IMainWindow{
    // データリスト
    private List<DataRecord> dataRecord = new ArrayList<DataRecord>();
    private JFrame frame;
    private JTable table;
    // データにアクセスするオブジェクト
    private DataAccess dataAccess;
    private ITableModelManager tableModelManager = TableModelManager.getInstance();

    /**
     * JFrameを返す
     */
    public JFrame getFrame(){
        return this.frame;
    }

    /**
     * JTableを返す
     */
    public JTable getTable(){
        return this.table;
    }

    /**
     * データリストを返す
     */
    public List<DataRecord> getDataRecords(){
        return this.dataRecord;
    }

    // コンストラクタ
    public MainWindow(){
        // データ初期化
        initializeTable();
        // Window作成
        createWindow();
    }

    /**
     * ダイアログで入力したデータをテーブルに追加する
     */
    public void SetRecord(DataRecord inputDataRecord){
        // データリストにデータを追加する
        this.dataRecord.add(inputDataRecord);

        // 合計を算出し、テーブルを更新する
        tableModelManager.CreateTableFromRecords(this.dataRecord);
        this.tableModelManager.UpdateTotal(dataRecord);

        // ファイルに出力する
        this.dataAccess.OutputFile(this.dataRecord);
    }

    /** 
     * ダイアログで入力したデータでテーブルを更新する
     */
    public void EditRecord(int index, DataRecord editDataRecord) {
        // データを更新する
        DataRecord record = this.dataRecord.get(index);
        record.ItemName = editDataRecord.ItemName;
        record.Protein = editDataRecord.Protein;
        record.Carbohydrate = editDataRecord.Carbohydrate;
        record.Lipid = editDataRecord.Lipid;
        record.Calorie = editDataRecord.Calorie;

        // 合計を算出し、テーブルを更新する
        tableModelManager.CreateTableFromRecords(this.dataRecord);
        this.tableModelManager.UpdateTotal(dataRecord);
        
        // ファイルに出力する
        this.dataAccess.OutputFile(this.dataRecord);
    }

    /**
     * テーブルから行を削除する
     */
    public void RemoveRecord(int index){
        this.tableModelManager.DeleteRow(index);
        this.dataRecord.remove(index - 1);
        this.tableModelManager.UpdateTotal(dataRecord);
        this.dataAccess.OutputFile(this.dataRecord);
    }

    /**
     * 基本データを更新したときにコールする
     * 合計行を更新する
     */
    public void UpdateBasicData(){
        // 合計を算出し、テーブルを更新する
        tableModelManager.UpdateTotal(this.dataRecord);
    }

    /**
     * データ初期化
     */
    private void initializeTable(){
        // 合計の行を追加する
        DataRecord columnCalc = new DataRecord("Total", 0.00, 0.00, 0.00, 0.00);
        this.tableModelManager.InsertTableFromRecord(columnCalc);
    }

    /**
     * Window作成
     */
    private void createWindow(){
        this.frame = new JFrame();
        this.frame.setSize(800, 480);
        this.frame.setLocationRelativeTo(null);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // メニュー
        JMenuBar menubar = CreateMenubar.Create(this);
        this.frame.setJMenuBar(menubar);

        Container contentPane = this.frame.getContentPane();

        // 現在の日付を取得
        Date today = new Date();
        SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy");
        int year = Integer.parseInt(dateFormat1.format(today));
        SimpleDateFormat dateFormat2 = new SimpleDateFormat("MM");
        int month = Integer.parseInt(dateFormat2.format(today));
        SimpleDateFormat dateFormat3 = new SimpleDateFormat("dd");
        int day = Integer.parseInt(dateFormat3.format(today));

        // ファイルを開く
        this.dataAccess = new DataAccess(year, month, day);
        this.dataRecord = this.dataAccess.OpenFile();
        this.tableModelManager.CreateTableFromRecords(this.dataRecord);

        // Yearラベル
        JPanel panel = new JPanel();
        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        JLabel label1 = new JLabel("Year");
        layout.setConstraints(label1, gbc);

        // 年コンボボックス
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 0.3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        JComboBox<String> comboYear = DateComboBox.CreateYearComboBox(year);
        comboYear.setSelectedIndex(1);
        layout.setConstraints(comboYear, gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.weightx = 0.1;
        JLabel label2 = new JLabel("Month");
        layout.setConstraints(label2, gbc);

        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.weightx = 0.2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        JComboBox<String> comboMonth = DateComboBox.CreateMonthComboBox();
        comboMonth.setSelectedIndex(month - 1);
        layout.setConstraints(comboMonth, gbc);

        gbc.gridx = 4;
        gbc.gridy = 0;
        gbc.weightx = 0.1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        JLabel label3 = new JLabel("Day");
        layout.setConstraints(label3, gbc);

        gbc.gridx = 5;
        gbc.gridy = 0;
        gbc.weightx = 0.2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        JComboBox<String> comboDay = DateComboBox.CreateDayComboBox();
        comboDay.setSelectedIndex(day - 1);
        layout.setConstraints(comboDay, gbc);

        // ComboBoxを選択したときのリスナーイベント
        ActionListener eventListner = new ActionListener(){
        
            @Override
            public void actionPerformed(ActionEvent e) {
                int year = DateComboBox.GetValueFromComboBox(comboYear);
                int month = DateComboBox.GetValueFromComboBox(comboMonth);
                int day = DateComboBox.GetValueFromComboBox(comboDay);
                dataAccess = new DataAccess(year, month, day);
                dataRecord = dataAccess.OpenFile();
                tableModelManager.CreateTableFromRecords(dataRecord);
            }
        };
        comboYear.addActionListener(eventListner);
        comboMonth.addActionListener(eventListner);
        comboDay.addActionListener(eventListner);

        // 追加ボタンの実装
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 6;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        JPanel panel2 = new JPanel();
        layout.setConstraints(panel2, gbc);

        GridBagLayout layout2 = new GridBagLayout();
        GridBagConstraints gbc2 = new GridBagConstraints();
        gbc2.gridx = 0;
        gbc2.gridy = 0;
        gbc2.weightx = 0.2;
        gbc2.fill = GridBagConstraints.HORIZONTAL;
        JButton addButton = ButtonControl.getAddButtonInstanse();
        addButton.addMouseListener(new AddButtonClickAdapter(this));
        layout2.setConstraints(addButton, gbc2);

        gbc2.gridx = 1;
        gbc2.gridy = 0;
        gbc2.fill = GridBagConstraints.HORIZONTAL;
        JButton editButton = ButtonControl.getEditButtonInstanse();
        editButton.addMouseListener(new EditButtonClickAdapter(this));
        layout2.setConstraints(editButton, gbc2);
 
        gbc2.gridx = 2;
        gbc2.fill = GridBagConstraints.HORIZONTAL;
        JButton copyButton = ButtonControl.getCopyButtonInstanse();
        copyButton.addMouseListener(new CopyButtonClickAdapter(this));
        layout2.setConstraints(copyButton, gbc2);

        gbc2.gridx = 3;
        gbc2.fill = GridBagConstraints.HORIZONTAL;
        JButton pasteButton = ButtonControl.getPasteButtonInstanse();
        pasteButton.addMouseListener(new PasteButtonClickAdapter(this));
        layout2.setConstraints(pasteButton, gbc2);

        gbc2.gridx = 4;
        gbc2.fill = GridBagConstraints.HORIZONTAL;
        JButton deleteButton = ButtonControl.getDeleteButtonInstanse();
        deleteButton.addMouseListener(new DeleteButtonClickAdapter(this));
        layout2.setConstraints(deleteButton, gbc2);

        panel2.add(addButton);
        panel2.add(editButton);
        panel2.add(copyButton);
        panel2.add(pasteButton);
        panel2.add(deleteButton);

        // テーブルの実装
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 6;
        gbc.weightx = 1;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        this.table = this.tableModelManager.GetTable();
        TableClickAdapter tableClickAdapter = new TableClickAdapter(this, this.dataRecord);
        this.table.addMouseListener(tableClickAdapter);

        // 合計を算出し、テーブルを更新する
        tableModelManager.UpdateTotal(this.dataRecord);

        JScrollPane pane = new JScrollPane(table);
        layout.setConstraints(pane, gbc);

        panel.setLayout(layout);
        panel.add(label1);
        panel.add(comboYear);
        panel.add(label2);
        panel.add(comboMonth);
        panel.add(label3);
        panel.add(comboDay);
        //panel.add(addButton);
        panel.add(panel2);
        panel.add(pane);

        JLabel statusBar = StatusBarControl.getStatusBarInstanse();
        contentPane.add(panel);
        this.frame.add(statusBar, BorderLayout.SOUTH);

        this.frame.setVisible(true);
    }
}
