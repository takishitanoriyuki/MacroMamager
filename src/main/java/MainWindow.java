import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class MainWindow{
    // このWindow
    private MainWindow mainWindow;
    // データリスト
    private List<DataRecord> dataRecord = new ArrayList<DataRecord>();
    // テーブルモデル
    private DefaultTableModel tableModel;

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
        Object[] row = new Object[5];
        row[0] = inputDataRecord.ItemName;
        row[1] = inputDataRecord.Protein;
        row[2] = inputDataRecord.Carbohydrate;
        row[3] = inputDataRecord.Lipid;
        row[4] = inputDataRecord.Calorie;
        this.tableModel.insertRow(this.tableModel.getRowCount(), row);

        // 合計を算出し、テーブルを更新する
        updateTable();
}

    /**
     * データ初期化
     */
    private void initializeTable(){
        // カラムのタイトル
        String[] columnNames = {"品目", "タンパク質", "炭水化物", "脂質", "カロリー"};

        // テーブルモデルの初期化
        this.tableModel = new DefaultTableModel(columnNames, 0);

        // 合計の行を追加する
        DataRecord columnCalc = new DataRecord("合計", 0.00, 0.00, 0.00, 0.00);
        Object[] row = new Object[5];
        row[0] = columnCalc.ItemName;
        row[1] = columnCalc.Protein;
        row[2] = columnCalc.Carbohydrate;
        row[3] = columnCalc.Lipid;
        row[4] = columnCalc.Calorie;
        this.tableModel.insertRow(0, row);
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

        // 追加コマンドの実装
        JButton addButton = new JButton("追加");
        addButton.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent event){
                InputDialog dialog = new InputDialog(frame, mainWindow);
                dialog.Show();
            }
        });
        contentPane.add(addButton, BorderLayout.CENTER);

        JScrollPane sp = new JScrollPane(new JTable(this.tableModel));
        contentPane.add(sp, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    /**
     * テーブル表示の更新
     */
    private void updateTable(){
        DataRecord columnCalc = new DataRecord("合計", 0.00, 0.00, 0.00, 0.00);
        // リストの全データの合計を求める
        for (DataRecord record : dataRecord) {
            columnCalc.Protein += record.Protein;
            columnCalc.Carbohydrate += record.Carbohydrate;
            columnCalc.Lipid += record.Lipid;
            columnCalc.Calorie += record.Calorie;
        }

        // 合計の行の値を更新する
        this.tableModel.setValueAt(columnCalc.Protein, 0, 1);
        this.tableModel.setValueAt(columnCalc.Carbohydrate, 0, 2);
        this.tableModel.setValueAt(columnCalc.Lipid, 0, 3);
        this.tableModel.setValueAt(columnCalc.Calorie, 0, 4);
    }
}
