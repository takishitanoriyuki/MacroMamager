package model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class HistoryData implements IHistoryData {
    // データフォルダ名
    private final String DATA_DIR = "data";
    // 履歴データファイル名
    private final String DATA_FILE = "history";
    // データファイル
    private File DataFile;
    // 履歴データ
    private List<DataRecord> HistoryRecord;

    private static IHistoryData HistoryData = new HistoryData();

    /**
     * インスタンス取得
     * @return
     */
    public static IHistoryData getInstanse(){
        return HistoryData;
    }

    /**
     * コンストラクタ
     */
    private HistoryData(){
        // データフォルダがあることを確認する
        String currentDirName = new File(".").getAbsoluteFile().getParent();
        File dataDir = new File(currentDirName + "/" + this.DATA_DIR);
        if(dataDir.exists() != true){
            // ディレクトリを作成する
            dataDir.mkdir();
        }

        // ファイル名を作成する
        this.DataFile = new File(dataDir.getPath() + "/" + this.DATA_FILE);

        // ファイルの存在を確認する
        if(this.DataFile.exists() != true){
            // データ初期化
            HistoryRecord = new ArrayList<DataRecord>();
            try {
                this.DataFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            // ファイルを開く
            HistoryRecord = new ArrayList<DataRecord>();
            try {
                BufferedReader br = new BufferedReader(new FileReader(this.DataFile));
                try {
                    String line = br.readLine();
                    while(line != null){
                        String[] words = line.split(",");
                        DataRecord record = new DataRecord();
                        record.ItemName = words[0];
                        record.Protein = Float.parseFloat(words[1]);
                        record.Carbohydrate = Float.parseFloat(words[2]);
                        record.Lipid = Float.parseFloat(words[3]);
                        record.Calorie = Float.parseFloat(words[4]);
                        this.HistoryRecord.add(record);
                        line = br.readLine();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }finally{
                    try {
                        br.close();
                    } catch (IOException e) {
                    }
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
			}
        }
    }

    @Override
    public void save() {
        try{
            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(this.DataFile)));
            for (DataRecord record : this.HistoryRecord) {
                pw.println(record.ItemName + "," + record.Protein + "," + record.Carbohydrate + "," + record.Lipid + "," + record.Calorie);
            }
            pw.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public List<DataRecord> get() {
        return this.HistoryRecord;
    }

    /**
     * 履歴データ追加
     */
    @Override
    public void add(DataRecord record) {
        // 同じアイテムがあれば追加しない
        for (DataRecord hist : this.HistoryRecord) {
            if(hist.ItemName.indexOf(record.ItemName) == 0){
                return;
            }
        }
        this.HistoryRecord.add(record);
	}

    @Override
    public List<DataRecord> search(String word) {
        List<DataRecord> result = new ArrayList<DataRecord>();
        for (DataRecord record : this.HistoryRecord) {
            int ret = record.ItemName.indexOf(word);
            if(record.ItemName.indexOf(word) >= 0){
                result.add(record);
            }             
        }
        return result;
    }
}