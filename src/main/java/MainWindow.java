import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * メインウインドウ
 */
public class MainWindow{
    // このWindow
    private MainWindow mainWindow;
    // データリスト
    private List<DataRecord> dataRecord = new ArrayList<DataRecord>();
    // テーブルモデル
    private DefaultTableModel tableModel;
    // データにアクセスするオブジェクト
    private DataAccess dataAccess;
    private final String DELETE = "Delete";
    private final String EDITITEM = "EditItem";

    // コンストラクタ
    public MainWindow(){
        // 自分自身を保持
        this.mainWindow = this;
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
        updateTable();

        // ファイルに出力する
        this.dataAccess.OutputFile(this.dataRecord);
    }

    /** 
     * ダイアログで入力したデータでテーブルを更新する
     */
    public void EditRecord(int index, DataRecord editDataRecord){
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
        updateTable();
        
        // ファイルに出力する
        this.dataAccess.OutputFile(this.dataRecord);
    }

    /**
     * データ初期化
     */
    private void initializeTable(){
        // カラムのタイトル
        String[] columnNames = {"Item", "Protein", "Carbohydrate", "Lipid", "Calorie"};

        // テーブルモデルの初期化
        this.tableModel = new DefaultTableModel(columnNames, 0);

        // 合計の行を追加する
        DataRecord columnCalc = new DataRecord("Total", 0.00, 0.00, 0.00, 0.00);
        setTableFromRecord(columnCalc);
    }

    /**
     * Window作成
     */
    private void createWindow(){
        JFrame frame = new JFrame();
        frame.setSize(640, 480);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container contentPane = frame.getContentPane();

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

        JPanel panel = new JPanel();
        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        JLabel label1 = new JLabel("Year");
        layout.setConstraints(label1, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 0.3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        String[] yearList = new String[] {String.format("%4d", year - 1), String.format("%4d", year), String.format("%4d", year + 1)};
        JComboBox<String> comboYear = new JComboBox<String>(yearList);
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
        String[] monthList = new String[12];
        for (int i = 1; i <= 12; i++) {
            monthList[i-1] = String.format("%2d", i);
        }
        JComboBox<String> comboMonth = new JComboBox<String>(monthList);
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
        String[] dayList = new String[31];
        for (int i = 1; i <= 31; i++) {
            dayList[i-1] = String.format("%2d", i);
        }
        JComboBox<String> comboDay = new JComboBox<String>(dayList);
        comboDay.setSelectedIndex(day - 1);
        layout.setConstraints(comboDay, gbc);


        // 追加ボタンの実装
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 6;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        JButton addButton = new JButton("ADD");
        addButton.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent event){
                InputDialog dialog = new InputDialog(frame, mainWindow);
                dialog.Show();
            }
        });
        layout.setConstraints(addButton, gbc);

        // テーブルの実装
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 6;
        gbc.weightx = 1;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        JTable table = new JTable(this.tableModel);
        table.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent event){
                // テーブルを右クリックしたときの処理
                if(event.getButton() == MouseEvent.BUTTON3){
                    int indexs[] = table.getSelectedRows();
                    // 選択している行がないときは何もしない
                    if(indexs.length == 0){
                        return;
                    }
                    // 選択している行が合計の行だった場合
                    for (int index : indexs) {
                        if(index == 0){
                            return;
                        }
                    }

                    JPopupMenu popupMenu = new JPopupMenu();
                    // 複数行選択している場合は編集メニューを表示しない
                    if(indexs.length == 1){
                        // 編集メニューの実装
                        JMenuItem menuItemEdit = new JMenuItem("EDIT");
                        menuItemEdit.setActionCommand(EDITITEM);
                        menuItemEdit.addActionListener(new ActionListener(){
    
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                // 削除メニュークリック時の処理
                                if(e.getActionCommand() == EDITITEM){
                                    int index = table.getSelectedRow() - 1;
                                    DataRecord record = dataRecord.get(index);
                                    EditDialog dialog = new EditDialog(frame, mainWindow, index, record);
                                    dialog.Show();
                                }
                            }
                        });
                        popupMenu.add(menuItemEdit);
                    }

                    // 削除メニューの実装
                    JMenuItem menuItemDelele = new JMenuItem("DELELE");
                    menuItemDelele.setActionCommand(DELETE);
                    menuItemDelele.addActionListener(new ActionListener(){

						@Override
						public void actionPerformed(ActionEvent e) {
                            // 削除メニュークリック時の処理
                            if(e.getActionCommand() == DELETE){
                                int indexs[] = table.getSelectedRows();
                                for (int index : indexs) {
                                    tableModel.removeRow(index);
                                    dataRecord.remove(index - 1);
                                    updateTable();
                                }
                            }
						}
                    });
                    popupMenu.add(menuItemDelele);

                    popupMenu.show(event.getComponent(), event.getX(), event.getY());
                }
            }
        });

        // 合計を算出し、テーブルを更新する
        updateTable();

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

        frame.setVisible(true);
    }

    /**
     * テーブル表示の更新
     */
    private void updateTable(){
        DataRecord columnCalc = new DataRecord("Total", 0.00, 0.00, 0.00, 0.00);
        // リストの全データの合計を求める
        for (DataRecord record : dataRecord) {
            columnCalc.Protein += record.Protein;
            columnCalc.Carbohydrate += record.Carbohydrate;
            columnCalc.Lipid += record.Lipid;
            columnCalc.Calorie += record.Calorie;
        }

        // 合計の行の値を更新する
        this.tableModel.setValueAt(String.format("%.2f", columnCalc.Protein), 0, 1);
        this.tableModel.setValueAt(String.format("%.2f", columnCalc.Carbohydrate), 0, 2);
        this.tableModel.setValueAt(String.format("%.2f", columnCalc.Lipid), 0, 3);
        this.tableModel.setValueAt(String.format("%.2f", columnCalc.Calorie), 0, 4);
    }

    /**
     * レコードリストからテーブルを作成する
     * @param records
     */
    private void createTableFromRecords(List<DataRecord> records){
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
