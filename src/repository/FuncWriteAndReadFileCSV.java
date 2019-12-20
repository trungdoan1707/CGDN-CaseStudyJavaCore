package repository;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public interface FuncWriteAndReadFileCSV<T> {
    Set<String> getNameServiceFromFileCSV(String path);
    void writeCsvFile(T t);
    List<T> readCsvFile();
    TreeSet<T> readCsvFileNotDuplicate();
}
