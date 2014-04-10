/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package draft.gamemode;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import draft.logic.Report;
import draft.swingui.GameUI;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Queue;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Class save game implements all logic of game saving actions
 * 
 * @author xserec00@stud.fit.vutbr.cz
 *         xskriv01@stud.fit.vutbr.cz
 */
public class SaveGame {

    
    private String notation;
    
    /**
     * Constructor of the save game object
     * 
     * @param record record to save made while playing the game
     */
    public SaveGame(Queue<Report> record) {

        JFileChooser fileChooser = new JFileChooser();
        //do argumentu lze napsat default cestu pro otevirani
        FileFilter filter = new FileNameExtensionFilter("XML files", "xml");
        fileChooser.addChoosableFileFilter(filter);
        int returnValue = fileChooser.showSaveDialog(fileChooser);
        //final JFileChooser fc = new JFileChooser();
        //int returnVal = fc.showSaveDialog(aComponent); //parent component to JFileChooser
        //Player pl = (Player) win;
        if (returnValue == JFileChooser.APPROVE_OPTION) { //OK button pressed by user
            File file = fileChooser.getSelectedFile(); //get File selected by user
            XStream xstream = new XStream(new DomDriver());
            String xml = xstream.toXML(this.getNotation(record));
            try {
                ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
                out.writeObject(xml);
                out.close();

                //ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
                //String newString = (String) in.readObject();
                //assert str.equals(newString);
                System.out.println("SAVE ok");
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            System.out.println("ok");
            //Queue<Report> test = (Queue<Report>)xstream.fromXML(xml);
        }
    }
    
    /**
     * Constructor of the save game object when the different type of notation is defined
     * 
     * @param record record to save made while playing the game
     * @param notation number of natotaion selected
     */
    public SaveGame(Queue<Report> record, int notation) {
        JFileChooser fileChooser = new JFileChooser();
        FileFilter filter = new FileNameExtensionFilter("TXT files", "txt");
        fileChooser.addChoosableFileFilter(filter);
        int returnValue = fileChooser.showSaveDialog(fileChooser);
        //Player pl = (Player) win;
        if (returnValue == JFileChooser.APPROVE_OPTION) { //OK button pressed by user
            File file = fileChooser.getSelectedFile(); //get File selected by user
          
            try {
                System.out.println(this.getNotation(record));
                ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
                //out.writeObject(txt);
                //out.writeChars(txt);
                out.writeBytes(this.getNotation(record));
                out.close();

                //ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
                //String newString = (String) in.readObject();
                //assert str.equals(newString);
                System.out.println("SAVE ok");
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            System.out.println("ok");
        }
    }
    
    /**
     * Method which transforms a queue of turns into the string of turns made in 
     * the game
     * 
     * @param record queue of turns made during the game
     * @return string ready to save to the text file
     */
    private String getNotation(Queue<Report> record) {
        notation = "";
        if (record.isEmpty() != true) {
                for(Report turn : record) {
                    if (turn.getColor() == 1) {
                        notation += turn.getTurn() + ". " + (turn.getFrom().getColumn())+""+turn.getFrom().getRow();
                        if (turn.isJump()) {
                            notation += "x";
                        }else {
                            notation += "-";
                        }
                        notation += (char)(turn.getTo().getColumn())+""+ turn.getTo().getRow() + " ";
                    } else {
                        notation += (char)turn.getFrom().getColumn()+ "" + turn.getFrom().getRow();
                        if (turn.isJump()) {
                            notation += "x";
                        }else {
                            notation += "-";
                        }
                        notation += (char)turn.getTo().getColumn()+"" + turn.getTo().getRow() + "\n";
                    }
                }
            }
        return notation;
    }
}
