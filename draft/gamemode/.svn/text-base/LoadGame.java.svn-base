/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


package draft.gamemode;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import draft.basis.Position;
import draft.logic.Report;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Class load game implements all the logic of loading state of game from xml file
 * 
 * @author xserec00@stud.fit.vutbr.cz
 *         xskriv01@stud.fit.vutbr.cz
 */
public class LoadGame {

    private File f;
    private String load;
    private Queue<TurnInNotation> record = new LinkedList<TurnInNotation>();
    ;
    private DataInputStream in = null;

    /**
     * COnstructor of the load game object
     */
    public LoadGame() {
        JFileChooser fileChooser = new JFileChooser();
        //do argumentu lze napsat default cestu pro otevirani
        FileFilter filter = new FileNameExtensionFilter("XML files", "xml");
        fileChooser.addChoosableFileFilter(filter);
        int returnValue = fileChooser.showOpenDialog(null);
        //final JFileChooser fc = new JFileChooser();
        //int returnVal = fc.showSaveDialog(aComponent); //parent component to JFileChooser

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            try {
                File file = fileChooser.getSelectedFile(); //get File selected by user

                ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
                String xml = (String) in.readObject();
                XStream xstream = new XStream(new DomDriver());
                load = (String) xstream.fromXML(xml);
                System.out.println("load ok");
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(LoadGame.class.getName()).log(Level.SEVERE, null, ex);
            }

            System.out.println("ok");
            //Queue<Report> test = (Queue<Report>)xstream.fromXML(xml);
        }
    }

    /**
     * Constructor of the load game object
     * @param i not used
     */
    public LoadGame(int i) {
        JFileChooser fileChooser = new JFileChooser();
        //do argumentu lze napsat default cestu pro otevirani
        FileFilter filter = new FileNameExtensionFilter("XML files", "xml");
        fileChooser.addChoosableFileFilter(filter);
        int returnValue = fileChooser.showOpenDialog(null);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            try {
                f = fileChooser.getSelectedFile(); //get File selected by user
                byte[] buffer = new byte[(int) f.length()];
                in = new DataInputStream(new FileInputStream(f));
                in.readFully(buffer);
                load = new String(buffer);
            } catch (IOException e) {
                throw new RuntimeException("IO problem in fileToString", e);
            } finally {
                try {
                    in.close();
                } catch (IOException e) { /* ignore it */

                }
            }
            //load = new Scanner(notF).useDelimiter("\\Z").next();
            System.out.println("load ok");


            System.out.println("ok");
            //Queue<Report> test = (Queue<Report>)xstream.fromXML(xml);
        }
    }

    /**
     * @return the test
     */
    public String getLoad() {
        return load;
    }

    /**
     * 
     * @return record of all turns loaded from file represented as the queue of turns
     */
    public Queue<TurnInNotation> getRecord() {
        int turn = 1;
        String sub;
        boolean jump = false;
        System.out.println(getLoad());
        System.out.println("-----------------");
        for (String line : getLoad().split("\n")) {

            if (line.indexOf(" ") != 0) {
                sub = line.substring(line.indexOf(" ") + 1, line.indexOf(" ") + 6);
                if (sub.charAt(1) == 'x') {
                    jump = true;
                } else {
                    jump = false;
                }
                System.out.println(sub);
                record.add(new TurnInNotation(sub.charAt(0), sub.charAt(1) - '0', sub.charAt(3), sub.charAt(4) - '0', jump, 1, turn));
                line = line.replaceAll(line.substring(0, line.indexOf(" ") + 7), "");
                if (line.length() > 4) {
                    record.add(new TurnInNotation(line.charAt(0), line.charAt(1) - '0', line.charAt(3), line.charAt(4) - '0', jump, 0, turn));
                }
            }
            turn++;
        }
        return record;
    }
    
    /**
     * Method which transforms a queue of turns into the string of turns made in 
     * the game
     * 
     * @param record queue of turns made during the game
     * @return string ready to save to the text file
     */
    public String getNotation(Queue<TurnInNotation> record) {
        String notation = "";
        if (record.isEmpty() != true) {
                for(TurnInNotation turn : record) {
                    if (turn.getColor() == 1) {
                        notation += (turn.getCfrom())+""+turn.getRfrom();
                        if (turn.isJump()) {
                            notation += "x";
                        }else {
                            notation += "-";
                        }
                        notation += (char)(turn.getCto())+""+ turn.getRto() + "@";
                    } else {
                        notation += (char)turn.getCfrom()+ "" + turn.getRfrom();
                        if (turn.isJump()) {
                            notation += "x";
                        }else {
                            notation += "-";
                        }
                        notation += (char)turn.getCto()+"" + turn.getRto() + "@";
                    }
                }
            }
        return notation;
    }
}
