import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader;
import enigma.console.TextAttributes;

import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ReadFile {
    public void read_puzzle(String fileName, String[][] board) throws IOException {

        File file = new File(fileName);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String line;

        int k = 0;
        if (file.exists()) {
            while ((line = br.readLine()) != null) {
                String[] str = line.split("\t");
                for (int i = 0; i < board[0].length; i++)
                {
                    if (str[i].equals("0"))
                        board[k][i] = "#";
                    else if (str[i].equals("1"))
                        board[k][i] = " ";
                    else
                        board[k][i] = str[i];
                }
                k++;
            }
            br.close();
            fr.close();

        } else {
            System.out.println("File named puzzle Does Not Exists");
        }
    }

    public void read_WordList(String fileName,SingleLinkedList words) throws IOException {

        File file = new File(fileName);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String line;

        if (file.exists()) {
            while ((line = br.readLine()) != null)
               words.addNode(line);
            br.close();
            fr.close();

        } else {
            System.out.println("File named wordlist Does Not Exists...");
        }

    }
    public void read_highscores(String fileName,DoubleLinkedList table) throws IOException {

        File file = new File(fileName);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String line;

        if (file.exists()) {
            while ((line = br.readLine()) != null)
                table.addNode(line);

            br.close();
            fr.close();

        } else {
            System.out.println("File named wordlist Does Not Exists...");
        }

    }

}