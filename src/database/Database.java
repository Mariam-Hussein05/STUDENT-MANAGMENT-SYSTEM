/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public abstract class Database<T extends Representation>{
    protected ArrayList<T> records;
    protected final String fileName;

    public Database(String fileName) {
        this.fileName = fileName;
        this.records = new ArrayList<>();
    }

    // ----------- ABSTRACT METHODS (implemented by subclasses) -----------
    protected abstract T createRecordFrom(String line);

    public void readFromFile() { // Reads all records from the file into memory.
        String line;// to read a line as a string from the file.
        records.clear();
        try {
            File file = new File(fileName);
            Scanner scan = new Scanner(file);
            if (scan.hasNextLine()) {
                while (scan.hasNextLine()) // While the file has a next line (will read until no new line is found).
                {
                    line = scan.nextLine();
                    if (null == createRecordFrom(line)) {
                        break;
                    } else {
                        records.add(createRecordFrom(line));
                    }

                }
            }
            scan.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<T> returnAllRecords() {
        return records;
    }

    public boolean containsName(String key) {
        return getRecord(key) != null;
    }
    public T getRecord(String key) {
        for (T record : records) {
            if (record.getSearchKey().equals(key))
                return record;
        }
        return null;
    }


    public void saveToFile() throws IOException {
        Path path = Paths.get(fileName);
        if (Files.notExists(path))
            Files.createFile(path);

        List<String> lines = new ArrayList<>();
        for (T record : records) {
            lines.add(record.lineRepresentation());
        }

        Files.write(path, lines, StandardCharsets.UTF_8, StandardOpenOption.TRUNCATE_EXISTING);
    }
}