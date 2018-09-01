import java.io.File;
import java.io.IOException;

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
        String filename = String.format("%4s-%2s-2S", year, month, day);
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
}