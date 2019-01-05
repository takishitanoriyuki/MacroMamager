package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class BasicData implements IBasicData {
    // データフォルダ名
    private final String DATA_DIR = "data";
    // 基本データファイル名
    private final String DATA_FILE = "basic";
    // データファイル
    private File DataFile;

    //タンパク質
    private double Protein;
    //炭水化物
    private double Carbohydrate;
    //脂質
    private double Lipid;
    //カロリー
    private double Calorie;
    //ファイルを読み込めたかどうか
    private boolean exist;

    private static IBasicData basicData = new BasicData();

    /**
     * インスタンス取得
     * @return
     */
    public static IBasicData getInstanse(){
        return basicData;
    }

    /**
     * コンストラクタ
     */
    private BasicData(){
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
            this.Protein = 0.0;
            this.Carbohydrate = 0.0;
            this.Lipid = 0.0;
            this.Calorie = 0.0;
            try {
                PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(this.DataFile)));
                pw.println(this.Protein + "," + this.Carbohydrate + "," + this.Lipid + "," + this.Calorie);
                pw.close();
                this.exist = true;
            } catch (IOException e) {
                this.exist = false;
            }
        }else{
            // ファイルを開く
            try {
                BufferedReader br = new BufferedReader(new FileReader(this.DataFile));
                try {
                    String line = br.readLine();
                    String[] words = line.split(",");
                    this.Protein = Float.parseFloat(words[0]);
                    this.Carbohydrate = Float.parseFloat(words[1]);
                    this.Lipid = Float.parseFloat(words[2]);
                    this.Calorie = Float.parseFloat(words[3]);
                    this.exist = true;
                } catch (IOException e) {
                    this.exist = false;
                }finally{
                    try {
                        br.close();
                    } catch (IOException e) {
                    }
                }
            } catch (FileNotFoundException e) {
                this.exist = false;
			}
        }
    }

    public boolean Save(){
        // ファイルを開く
        try {
            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(this.DataFile)));
            pw.println(this.Protein + "," + this.Carbohydrate + "," + this.Lipid + "," + this.Calorie);
            pw.close();
        } catch (IOException e) {
            return false;
        }
        return true;        
    }

    /**
     * ファイルを正常に開けたか？
     */
    public boolean isExist(){
        return this.exist;
    }

    /**
     * @return the protein
     */
    public double getProtein() {
        return Protein;
    }

    /**
     * @return the calorie
     */
    public double getCalorie() {
        return Calorie;
    }

    /**
     * @param calorie the calorie to set
     */
    public void setCalorie(double calorie) {
        this.Calorie = calorie;
    }

    /**
     * @return the lipid
     */
    public double getLipid() {
        return Lipid;
    }

    /**
     * @param lipid the lipid to set
     */
    public void setLipid(double lipid) {
        this.Lipid = lipid;
    }

    /**
     * @return the carbohydrate
     */
    public double getCarbohydrate() {
        return Carbohydrate;
    }

    /**
     * @param carbohydrate the carbohydrate to set
     */
    public void setCarbohydrate(double carbohydrate) {
        this.Carbohydrate = carbohydrate;
    }

    /**
     * @param protein the protein to set
     */
    public void setProtein(double protein) {
        this.Protein = protein;
    }

}