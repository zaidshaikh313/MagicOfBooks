package entities;

import javax.persistence.*;
// Logger POJO Class
@Entity
@Table(name = "Logs")
public class Logger {
    @Id
    @Column(name = "logId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int logId;
    @Column(name = "timeStamp")
    String time;
    @Column(name = "message")
    String logMessage;
    public Logger(){
        super();
    }
    public Logger(String time, String logMessage) {
        this.time = time;
        this.logMessage = logMessage;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLogMessage() {
        return logMessage;
    }

    public void setLogMessage(String logMessage) {
        this.logMessage = logMessage;
    }
}
