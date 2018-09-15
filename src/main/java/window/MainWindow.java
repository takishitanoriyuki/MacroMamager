package window;

import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;
import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

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
    // テーブルモデル
    private DefaultTableModel tableModel;
    // データにアクセスするオブジェクト
    private DataAccess dataAccess;

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
     * TableModelを返す
     */
    public DefaultTableModel getTableModel(){
        return this.tableModel;
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

        // テーブルにデータを追加する
        setTableFromRecord(inputDataRecord);

        // 合計を算出し、テーブルを更新する
        TableAccess.UpdateTable(this.dataRecord, this.tableModel);

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

        // テーブルを更新する
        this.tableModel.setValueAt(record.ItemName, index + 1, 0);
        this.tableModel.setValueAt(String.format("%.2f", record.Protein), index + 1, 1);
        this.tableModel.setValueAt(String.format("%.2f", record.Carbohydrate), index + 1, 2);
        this.tableModel.setValueAt(String.format("%.2f", record.Lipid), index + 1, 3);
        this.tableModel.setValueAt(String.format("%.2f", record.Calorie), index + 1, 4);

        // 合計を算出し、テーブルを更新する
        TableAccess.UpdateTable(this.dataRecord, this.tableModel);
        
        // ファイルに出力する
        this.dataAccess.OutputFile(this.dataRecord);
    }

    public void UpdateBasicData(){
        // 合計を算出し、テーブルを更新する
        TableAccess.UpdateTable(this.dataRecord, this.tableModel);
    }

    /**
     * データ初期化
     */
    private void initializeTable(){
        // カラムのタイトル
        String[] columnNames = {"Item", "Protein", "Carbohydrate", "Lipid", "Calorie"};

        // テーブルモデルの初期化
        this.tableModel = new DefaultTableModel(columnNames, 0){

            // セルを編集できないようにする
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }};

        // 合計の行を追加する
        DataRecord columnCalc = new DataRecord("Total", 0.00, 0.00, 0.00, 0.00);
        setTableFromRecord(columnCalc);
    }

    /**
     * Window作成
     */
    private void createWindow(){
        frame = new JFrame();
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
        createTableFromRecords(this.dataRecord);

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
                createTableFromRecords(dataRecord);
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
        JButton addButton = new JButton("ADD");
        AddButtonClickAdapter addButtonClickAdapter = new AddButtonClickAdapter(this);
        addButton.addMouseListener(addButtonClickAdapter);
        layout.setConstraints(addButton, gbc);

        // テーブルの実装
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 6;
        gbc.weightx = 1;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        this.table = new JTable(this.tableModel);
        TableClickAdapter tableClickAdapter = new TableClickAdapter(this, this.dataRecord);
        this.table.addMouseListener(tableClickAdapter);

        // 合計を算出し、テーブルを更新する
        TableAccess.UpdateTable(this.dataRecord, this.tableModel);

        layout.setConstraints(table, gbc);

        panel.setLayout(layout);
        panel.add(label1);
        panel.add(comboYear);
        panel.add(label2);
        panel.add(comboMonth);
        panel.add(label3);
        panel.add(comboDay);
        panel.add(addButton);
        panel.add(table);

        contentPane.add(panel);

        this.frame.setVisible(true);
    }

    /**
     * レコードリストからテーブルを作成する
     * @param records
     */
    private void createTableFromRecords(List<DataRecord> records){
        if(this.tableModel.getRowCount() > 1){
            while(this.tableModel.getRowCount() > 1)
            this.tableModel.removeRow(1);            
        }
        for (DataRecord record : records) {
            setTableFromRecord(record);
        }
    }

    /**
     * レコードからテーブルに設定する
     * @param record
     */
    private void setTableFromRecord(DataRecord record){
        Object[] row = new Object[5];
        row[0] = record.ItemName;
        row[1] = String.format("%.2f", record.Protein);
        row[2] = String.format("%.2f", record.Carbohydrate);
        row[3] = String.format("%.2f", record.Lipid);
        row[4] = String.format("%.2f", record.Calorie);
        this.tableModel.insertRow(this.tableModel.getRowCount(), row);
    }
}
