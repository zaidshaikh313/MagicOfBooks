package dao;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
// to get formatted timestamp
public class Date {
    public String dateFormatter(){
        LocalDateTime current = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        return current.format(format);
    }
}
