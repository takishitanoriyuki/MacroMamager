package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * データにアクセスするクラス
 */
public class DataAccess{
    // データフォルダ名
    private final String DATA_DIR = "data";
    // データファイル
    private File DataFile;

    /**
     * コンストラクタ
     */
    public DataAccess(int year, int month, int day){
        // データフォルダがあることを確認する
        String currentDirName = new File(".").getAbsoluteFile().getParent();
        File dataDir = new File(currentDirName + "/" + this.DATA_DIR);
        if(dataDir.exists() != true){
            // ディレクトリを作成する
            dataDir.mkdir();
        }

        // ファイル名を作成する
        String filename = String.format("%4d-%02d-%02d", year, month, day);
        this.DataFile = new File(dataDir.getPath() + "/" + filename);

        // ファイルの存在を確認する
        if(this.DataFile.exists() != true){
            try {
				this.DataFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
    }

    /**
     * ファイルにデータを出力する
     * @param records
     */
    public boolean OutputFile(List<DataRecord> records){
        // ファイルを開く
        try {
            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(this.DataFile)));
            for (DataRecord record : records) {
                pw.println(record.ItemName + "," + record.Protein + "," + record.Carbohydrate + "," + record.Lipid + "," + record.Calorie);
            }
            pw.close();
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    /**
     * ファイルを開いてデータを取り出す
     * @return
     */
    public List<DataRecord> OpenFile() {
        List<DataRecord> records = new ArrayList<DataRecord>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(this.DataFile));
            try {
                String line = br.readLine();
                while(line != null){
                    DataRecord record = new DataRecord();
                    String[] words = line.split(",");
                    record.ItemName = words[0];
                    record.Protein = Float.parseFloat(words[1]);
                    record.Carbohydrate = Float.parseFloat(words[2]);
                    record.Lipid = Float.parseFloat(words[3]);
                    record.Calorie = Float.parseFloat(words[4]);
                    records.add(record);

                    line = br.readLine();
                }
            } catch (IOException e) {
            }finally{
                try {
                    br.close();
                } catch (IOException e) {
                }
            }
        } catch (FileNotFoundException e) {
            return null;
        }
        return records;
    }
}