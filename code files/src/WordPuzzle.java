import enigma.core.Enigma;
import enigma.event.TextMouseEvent;
import enigma.event.TextMouseListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import enigma.console.TextAttributes;

import javax.swing.table.JTableHeader;
import java.awt.Color;
import java.util.Random;

public class WordPuzzle {
   static int px=5 ,py=5;
   public static  String[][] main_board =new String[15][15];
   public static String[][] test_board=new String[15][15];
   public static MultiLinkedList words=new MultiLinkedList();
   public static SingleLinkedList wordList=new SingleLinkedList();
   public static DoubleLinkedList table=new DoubleLinkedList();
   // ------ Standard variables for mouse and keyboard -----
   public static enigma.console.Console cn = Enigma.getConsole("Mouse and Keyboard");
   public static TextMouseListener tmlis;
   public static KeyListener klis;
   // ------ Standard variables for mouse and keyboard ------
   public static int mousepr;          // mouse pressed?
   public static  int mousex, mousey;   // mouse text coords.
   public static int keypr;   // key pressed?
   public static int rkey;    // key   (for press/release)
   // ----------------------------------------------------

   WordPuzzle() throws Exception {   // --- Contructor
      // ------ Standard code for mouse and keyboard ------ Do not change
      tmlis=new TextMouseListener() {
         public void mouseClicked(TextMouseEvent arg0) {}
         public void mousePressed(TextMouseEvent arg0) {
            if(mousepr==0) {
               mousepr=1;
               mousex=arg0.getX();
               mousey=arg0.getY();
            }
         }
         public void mouseReleased(TextMouseEvent arg0) {}
      };
      cn.getTextWindow().addTextMouseListener(tmlis);
      klis=new KeyListener() {
         public void keyTyped(KeyEvent e) {}
         public void keyPressed(KeyEvent e) {
            if(keypr==0) {
               keypr=1;
               rkey=e.getKeyCode();
            }
         }
         public void keyReleased(KeyEvent e) {}
      };
      cn.getTextWindow().addKeyListener(klis);
      px=9;py=12;

      String path_puzzle = "C:/puzzle.txt";
      String path_solution="C:/solution.txt";
      String path_wordlist="C:/word_list.txt";
      String path_table="C:/high_score_table.txt";
      ReadFile reader=new ReadFile();
      reader.read_puzzle(path_puzzle,main_board);
      reader.read_puzzle(path_solution,test_board);

      reader.read_highscores(path_table,table);
     //table.display();
      Player player1=new Player("player1",0);
      Player player2=new Player("player2",0);

      boolean turn=true;
      Mode.walkMode walkMode=new Mode().new walkMode(true);
      Mode.GuessMode guessMode=new Mode().new GuessMode(false);
      MultiLinkedList mll=new MultiLinkedList();
      reader.read_WordList(path_wordlist,wordList);

      //wordList.printLinkedList();
      SLLtoMLL(wordList,mll);
      //mll.display();

      wordScanner_H(words);
      wordScanner_V(words);
     // words.display();
      print_board();
      print_constants(words);

      // ---------GAME FLOW--------------------------
      while(!isPuzzleCompleted())
      {
         if(turn)
         {
            if(!walkMode.isStatus())
            {
               guessMode.setStatus(true);
               guessMode.guess(player1,player2,turn);
            }
            if(!guessMode.isStatus())
            {
               walkMode.setStatus(true);
               turn=false;
               walkMode.walk();
            }
         }
         else
         {
            if(!walkMode.isStatus())
            {
               guessMode.setStatus(true);
               guessMode.guess(player1,player2,turn);
            }
            if(!guessMode.isStatus())
            {
               walkMode.setStatus(true);
               turn=true;
               walkMode.walk();
            }

         }
      }
   }
   public void print_constants(MultiLinkedList wordList)
   {
      int px1=1;
      int py1=5;
      int y=5;
      int x=2;
      int yy=0;
      int ey=4;
      int ex=23;
      cn.getTextWindow().setCursorPosition(1,1);
      System.out.print("WORD=0");
      cn.getTextWindow().setCursorPosition(30,1);
      System.out.print("SCORE: PLAYER-1=0");
      cn.getTextWindow().setCursorPosition(50,1);
      System.out.print("PLAYER-2=0");
      cn.getTextWindow().setCursorPosition(0,20);
      System.out.print("INFO:");
      for (int i = 0; i <15 ; i++)
      {
         cn.getTextWindow().setCursorPosition(x,4);
         if(i<=9)
            System.out.print(i);
         else if(i>9)
            System.out.print(yy);

         cn.getTextWindow().setCursorPosition(1,y);
         if(i<=9)
            System.out.print(i);
         else if(i>9) {
            System.out.print(yy);
            yy++;
         }
         cn.getTextWindow().setCursorPosition(23,ey);
         System.out.print("|");
         cn.getTextWindow().setCursorPosition(53,ey);
         System.out.print("|");

         py1++;
         y++;
         x++;
         ey++;
         letterNode tmp=wordList.getHead();
         wordNode tmp1;
         int wx=24;
         int wy=4;
         cn.getTextWindow().setCursorPosition(wx,wy);

         while(tmp!=null)
         {
            tmp1=tmp.getRight();

            if(wy>18)
            {
               wx+=18;
               wy=4;
            }
            while(tmp1 !=null)
            {
               cn.getTextWindow().setCursorPosition(wx,wy);
               System.out.print(tmp1.getWord());
               tmp1=tmp1.getNext();
               wy++;
            }
            tmp=tmp.getDown();
         }
      }
      for (int i = 0; i <30 ; i++) {
         cn.getTextWindow().setCursorPosition(ex,19);
         System.out.print("-");
         cn.getTextWindow().setCursorPosition(ex,3);
         System.out.print("-");
         ex++;
      }
      cn.getTextWindow().setCursorPosition(23,3);
      System.out.print("+-WORD-LIST");
      cn.getTextWindow().setCursorPosition(53,19);
      System.out.print("+");
      cn.getTextWindow().setCursorPosition(53,3);
      System.out.print("+");
      cn.getTextWindow().setCursorPosition(23,19);
      System.out.print("+");
   }
   public static void print_table(DoubleLinkedList table, Player winner)
   {
      Node temp=table.head;
      String[] savedPlayers;
      while(temp!=null)
      {
         savedPlayers=temp.data.split(";");
         if(temp==table.head)
         {
            Node newNode=new Node();
            Node tmp = temp.getLinkPrev();
            newNode.data=winner.getName();
            temp.setLinkPrev(newNode);
            newNode.setLinkNext(temp);
            table.head=newNode;
            break;
         }
         else
         {
            if(winner.getScore()>=Integer.parseInt(savedPlayers[1]))
            {
               Node newNode=new Node();
               Node tmp = temp.getLinkPrev();
               newNode.data=winner.getName();
               tmp.setLinkNext(newNode);
               newNode.setLinkPrev(tmp);
               newNode.setLinkNext(temp);
               temp.setLinkPrev(newNode);
               break;
            }

         }
         temp=temp.next;
      }
      WordPuzzle.cn.getTextWindow().setCursorPosition(60,2);
      System.out.println("HIGH SCORE TABLE");
      temp=table.head;
      int y=3;
      while(temp != null)
      {
         savedPlayers=temp.data.split(";");
         WordPuzzle.cn.getTextWindow().setCursorPosition(60,y);
         if(y-2<=10)
            System.out.println((y-2)+"-"+savedPlayers[0]);
         y++;
         temp=temp.next;
      }

   }
   public static void addandPrintScore(Player player1,Player player2,int val,boolean turn)
   {
      if(!turn)
         player1.addScore(val);
      else
         player2.addScore(val);
      cn.getTextWindow().setCursorPosition(46,1);
      System.out.println(player1.getScore());
      cn.getTextWindow().setCursorPosition(59,1);
      System.out.println(player2.getScore());
   }
   public static  void print_board() throws Exception {
      TextAttributes attrs1 = new TextAttributes(Color.black, Color.white);
      TextAttributes attrs = new TextAttributes(Color.black, Color.black);
      TextAttributes attrs3 = new TextAttributes(Color.white, Color.black);
      int px=2;
      int py=5;

      for (int i = 0; i < main_board[0].length ; i++)
      {
         cn.getTextWindow().setCursorPosition(px,py);
         for (int j = 0; j < main_board[1].length ; j++) {

            if(!main_board[i][j].equals(" ")&&main_board[i][j].equals("#"))
            {
               cn.setTextAttributes(attrs);
               System.out.print(main_board[i][j]);
            }
            else
            {
               cn.setTextAttributes(attrs1);
               System.out.print(main_board[i][j]);
            }
         }
         py++;

      }
      cn.setTextAttributes(attrs3);
      WordPuzzle.cn.getTextWindow().output(WordPuzzle.px+2, WordPuzzle.py+5,'^');
   }

   public void SLLtoMLL(SingleLinkedList wordList,MultiLinkedList mll)
   {
      wordNode temp=wordList.head;
      int counter=0;
      for (int i = 97; i <123; i++) {
         mll.addLetter((char) i);
      }
      if(temp==null)
         System.out.println("wordlist is empty..");
      else
      {
         while(temp != null)
         {
            mll.addWordwithBracket(temp);
            temp=temp.getNext();
         }
      }
   }
   public static void print_message(String info) throws InterruptedException {
      cn.getTextWindow().setCursorPosition(5,20);
      cn.getTextWindow().output(info);
      Thread.sleep(2500);
      cn.getTextWindow().setCursorPosition(5,20);
      cn.getTextWindow().output("                                                       ");
   }
   public void wordScanner_H(MultiLinkedList words)
   {
      for (int p = 97; p <123; p++)
      {
         words.addLetter((char) p);
      }
      String word="";
      boolean flag=false;
      for (int i = 0; i <test_board[0].length ; i++)
      {
         for (int j = 0; j <test_board[1].length ; j++)
         {
            if(!test_board[i][j].equals("#") &&!flag)
            {
               word+=test_board[i][j];
            }
            else
               flag=true;
            if(flag)
            {
               if(word.length()<3)
                  word="";
               else
               {
                  wordNode newWord = new wordNode("[ ]"+word);
                  words.addWordwithBracket(newWord);
               }
               word = "";
               flag=false;
            }
         }
         flag=true;
      }
   }
   public void wordScanner_V(MultiLinkedList words)
   {
      String word="";
      boolean flag=false;
      for (int i = 0; i <test_board[0].length ; i++)
      {
         for (int j = 0; j <test_board[1].length ; j++)
         {
            if(!test_board[j][i].equals("#"))
               word+=test_board[j][i];
            else
               flag=true;
            if(flag)
            {
               if(word.length()<3)
                  word="";
               else
               {
                  wordNode newWord = new wordNode("[ ]"+word);
                  words.addWordwithBracket(newWord);
               }
               word = "";
               flag=false;
            }
         }
      }
   }
   ///////////////////Random Generator Function for easy to use//////////////////
   public static int random_generator(int bottom, int up)
   {
      Random rnd = new Random();
      int randomnumb1 = rnd.nextInt(up + 1 - bottom) + bottom;
      return randomnumb1;
   }
   public static boolean isPuzzleCompleted()
   {
      for (int i = 0; i <WordPuzzle.main_board[0].length ; i++)
      {
         for (int j = 0; j <WordPuzzle.main_board[1].length ; j++)
         {
            if(WordPuzzle.main_board[i][j].equals(" "))
               return false;
         }
      }
      return true;
   }

}
