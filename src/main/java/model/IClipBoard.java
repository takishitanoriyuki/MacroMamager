package model;

public interface IClipBoard{
    /**
     * データを保存
     */
    void Store(DataRecord data);
    /**
     * データ取得
     * @return
     */
    DataRecord Pull();
    /**
     * データクリア
     */
    void Clear();
}