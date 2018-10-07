package model;

import java.util.List;

public interface IHistoryData {
    void save();
    List<DataRecord> get();
    List<DataRecord> search(String word);
    void add(DataRecord record);
}