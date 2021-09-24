/**
 * @author K. Rege
 * @version 1.0 -- Experimentierkasten
 */
package ch.zhaw.ads;

import java.awt.*;
import java.io.*;
import java.awt.event.*;

public class ExBox {

    public static void main(String[] args) throws Exception {
        File file = new File("MainWindow.fxml");
        FileInputStream fileInputStream = new FileInputStream(file);
        ExBoxFrame f = new ExBoxFrame();
        f.setLocationRelativeTo(null);  
        f.setVisible(true);


        }

}
