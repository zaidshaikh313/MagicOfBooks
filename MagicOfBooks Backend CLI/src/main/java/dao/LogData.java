package dao;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

// For storing logs
public class LogData {
    Date date = new Date();
    String logDate = date.dateFormatter();

    public void storeData(String msg) {
        Path path = Paths.get("MyLogFile.txt");
        String result = logDate.concat("\t" + msg + "\n");
        boolean fileExist = Files.exists(path);
        try {
            if (fileExist) {
                Files.write(path, result.getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND);

            } else {
                Files.createFile(path);
                Files.write(path, result.getBytes(StandardCharsets.UTF_8));

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
