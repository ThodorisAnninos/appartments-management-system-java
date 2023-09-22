package api;

import javax.swing.*;
import java.io.Serializable;

/**
 * Class that simulates errors with an errorCode and message.
 */
public class Error implements Serializable {
    private int errorCode;
    private String message;

    /**
     * Html tags used to change line automatically in gui.
     * @param errorCode
     * @param message
     */
    public Error(int errorCode, String message){
        this.errorCode = errorCode;
        this.message = "<html>" + message + "</html>";
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void checkError(int code, JLabel label) {
        if (this.getErrorCode() == code){
            label.setVisible(true);
            label.setText(this.getMessage());
        }
        else {
            label.setVisible(false);
        }
    }

}
