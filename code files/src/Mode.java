
import java.awt.event.KeyEvent;
import java.util.Locale;
import java.util.Scanner;

public class Mode {
    static boolean flag=false;
    static int foundedWords=0;
    static boolean isKeyPressed=false;
    class walkMode{
        private boolean status;
        public walkMode(boolean status)
        {
            this.status=status;
        }
        public void walk() throws Exception {
            if(status)
            {

                while(status)
                {
                    if(WordPuzzle.mousepr==1) {  // if mouse button pressed
                        WordPuzzle.cn.getTextWindow().output(WordPuzzle.mousex, WordPuzzle.mousey,'*');  // write a char to x,y position without changing cursor position
                        WordPuzzle.px= WordPuzzle.mousex; WordPuzzle.py= WordPuzzle.mousey;

                        WordPuzzle.mousepr=0;     // last action
                    }
                    if(WordPuzzle.keypr==1) {    // if keyboard button pressed
                        char rckey=(char) WordPuzzle.rkey;
                        if(WordPuzzle.rkey >= KeyEvent.VK_A && WordPuzzle.rkey <= KeyEvent.VK_Z)
                        {
                            WordPuzzle.print_message("Key is pressed please enter your first letter...");
                            this.status=false;
                        }
                        if(WordPuzzle.rkey== KeyEvent.VK_LEFT&& WordPuzzle.px>0)
                            WordPuzzle.px--;
                        if(WordPuzzle.rkey==KeyEvent.VK_RIGHT&& WordPuzzle.px<14)
                            WordPuzzle.px++;
                        if(WordPuzzle.rkey==KeyEvent.VK_UP&& WordPuzzle.py>0)
                            WordPuzzle.py--;
                        if(WordPuzzle.rkey==KeyEvent.VK_DOWN&& WordPuzzle.py<14)
                            WordPuzzle.py++;
                        if(WordPuzzle.rkey==KeyEvent.VK_NUMPAD0)
                        {
                            int py=10;
                            for (int i = 0; i < WordPuzzle.main_board[0].length ; i++)
                            {
                                WordPuzzle.cn.getTextWindow().setCursorPosition(20,py);
                                for (int j = 0; j <WordPuzzle.main_board[1].length ; j++) {
                                        System.out.print(WordPuzzle.main_board[i][j]);
                                }
                                py++;
                            }
                        }
                        if(WordPuzzle.rkey==KeyEvent.VK_NUMPAD1)
                        {

                            int py=10;
                            for (int i = 0; i < WordPuzzle.test_board[0].length ; i++)
                            {
                                WordPuzzle.cn.getTextWindow().setCursorPosition(20,py);
                                for (int j = 0; j <WordPuzzle.test_board[1].length ; j++) {
                                    System.out.print(WordPuzzle.test_board[i][j]);
                                }
                                py++;
                            }
                        }
                        WordPuzzle.keypr=0;    // last action
                    }
                    WordPuzzle.print_board();
                    Thread.sleep(20);
                }
            }
        }
        public boolean isStatus() {
            return status;
        }

        public void setStatus(boolean status) {
            this.status = status;
        }
    }
    class GuessMode{
        private boolean status;
        public GuessMode(boolean status)
        {
            this.status=status;
        }

        public boolean isStatus() {
            return status;
        }

        public void setStatus(boolean status) {
            this.status = status;
        }

        public void guess(Player player1,Player player2,boolean turn) throws Exception {
            if(status)
            {

                flag=false;

                int edge_left=left_edge();
                int edge_right=right_edge();
                int edge_up=up_edge();
                int edge_bottom=bottom_edge();
                while(status)
                {
                    if(WordPuzzle.keypr==1) {    // if keyboard button pressed
                        char rckey=(char) WordPuzzle.rkey;
                        if(WordPuzzle.rkey >= KeyEvent.VK_A && WordPuzzle.rkey <= KeyEvent.VK_Z)
                        {

                            char k=WordPuzzle.main_board[WordPuzzle.py][WordPuzzle.px].charAt(0);
                            if(!WordPuzzle.main_board[WordPuzzle.py][WordPuzzle.px].equals("#") && !(k>=97 && k<=122) &&checkLetter(String.valueOf(rckey).toLowerCase(Locale.ENGLISH)))
                            {
                                WordPuzzle.main_board[WordPuzzle.py][WordPuzzle.px]=String.valueOf(rckey).toLowerCase(Locale.ENGLISH);
                                WordPuzzle.addandPrintScore(player1,player2,1,turn);
                            }
                            else if(k>=97 && k<=122)
                            {
                                WordPuzzle.print_message("There is already a word located here...\nOther player's turn");
                                this.status=false;
                            }
                            else if(WordPuzzle.main_board[WordPuzzle.py][WordPuzzle.px].equals("#") )
                            {
                                WordPuzzle.print_message("You are not allowed to locate a letter here...");
                                this.status=false;
                            }
                            else
                            {
                                WordPuzzle.addandPrintScore(player1,player2,-2,turn);
                                WordPuzzle.print_message("You have entered wrong letter now other player's turn");
                                this.status=false;
                            }

                        }
                        if(WordPuzzle.rkey== KeyEvent.VK_LEFT&& WordPuzzle.px>edge_left&&flag)
                            WordPuzzle.px--;
                        if(WordPuzzle.rkey==KeyEvent.VK_RIGHT&& WordPuzzle.px<edge_right&&flag)
                            WordPuzzle.px++;
                        if(WordPuzzle.rkey==KeyEvent.VK_UP&& WordPuzzle.py>edge_up&&!flag)
                            WordPuzzle.py--;
                        if(WordPuzzle.rkey==KeyEvent.VK_DOWN&& WordPuzzle.py<edge_bottom&&!flag)
                            WordPuzzle.py++;

                        if(!(wordTracker_H(turn,player1,player2)&&wordTracker_V(turn,player1,player2)))
                        {
                            WordPuzzle.addandPrintScore(player1,player2,10,turn);
                            WordPuzzle.words.printWordlist();
                            this.status=false;
                        }
                        if(WordPuzzle.isPuzzleCompleted())
                        {
                            if(player1.getScore()>=player2.getScore())
                            {
                                WordPuzzle.print_message("Player-1 won the game");
                                WordPuzzle.print_table(WordPuzzle.table,player1);
                            }
                            else
                            {
                                WordPuzzle.print_message("Player-2 won the game");
                                WordPuzzle.print_table(WordPuzzle.table,player1);
                            }
                            WordPuzzle.wordList.printUnsigneds();
                            Thread.sleep(100000);
                        }

                        WordPuzzle.keypr=0;    // last action
                    }
                    WordPuzzle.print_board();
                    Thread.sleep(20);
                }
            }
        }

        public boolean wordTracker_H(boolean turn,Player player1,Player player2) throws InterruptedException {
            String word="";
            boolean flag=false;
            for (int i = 0; i <WordPuzzle.main_board[0].length ; i++)
            {
                for (int j = 0; j <WordPuzzle.main_board[1].length ; j++)
                {
                    if(!WordPuzzle.main_board[i][j].equals("#") &&!flag)
                        word+=WordPuzzle.main_board[i][j];
                    else
                        flag=true;
                    if(flag)
                    {
                        if(word.length()<3)
                            word="";
                        else
                        {
                            if(WordPuzzle.wordList.searchAndSign("[ ]"+word)&&WordPuzzle.words.search_Word("[ ]"+word))
                            {
                                foundedWords++;
                                WordPuzzle.cn.getTextWindow().setCursorPosition(6,1);
                                System.out.print(foundedWords);
                                askQuestion(word,turn,player1,player2);
                                return false;
                            }
                        }
                        word = "";
                        flag=false;
                    }
                }
                flag=true;
            }
            return true;
        }
        public boolean wordTracker_V( boolean turn, Player player1, Player player2) throws InterruptedException {
            String word="";
            boolean flag=false;
            for (int i = 0; i <WordPuzzle.main_board[0].length ; i++)
            {
                for (int j = 0; j < WordPuzzle.main_board[1].length ; j++)
                {
                    if(!WordPuzzle.main_board[j][i].equals("#"))
                        word+= WordPuzzle.main_board[j][i];
                    else
                        flag=true;
                    if(flag)
                    {
                        if(word.length()<3)
                            word="";
                        else
                        {
                            if(WordPuzzle.wordList.searchAndSign("[ ]"+word)&&WordPuzzle.words.search_Word("[ ]"+word))
                            {
                                foundedWords++;
                                WordPuzzle.cn.getTextWindow().setCursorPosition(6,1);
                                System.out.print(foundedWords);
                                askQuestion(word,turn,player1,player2);
                                return false;
                            }
                        }
                        word = "";
                        flag=false;
                    }
                }
            }
            return true;
        }
        void askQuestion(String word,boolean turn,Player player1,Player player2) throws InterruptedException {
            if(!turn){
                WordPuzzle.cn.getTextWindow().setCursorPosition(5,20);
                System.out.println("The word '"+word.toUpperCase()+"' is placed correctly.PLAYER 1 gets 10 point.\n What is " +
                        "the meaning of '"+word.toUpperCase()+"' in Turkish? Please enter your option.\n"+printAnswers(WordPuzzle.wordList,word)+"\nAnswer:");
                checkAnswer(player1,player2,turn);
                Thread.sleep(2000);
                clearQuestion();
            }
            if(turn){
                WordPuzzle.cn.getTextWindow().setCursorPosition(5,20);
                System.out.println("The word '"+word.toUpperCase()+"' is placed correctly.PLAYER 2 gets 10 point.\n What is " +
                        "the meaning of '"+word.toUpperCase()+"' in Turkish? Please enter your option.\n"+printAnswers(WordPuzzle.wordList,word)+"\nAnswer:");
                checkAnswer(player1,player2,turn);
                Thread.sleep(2000);
                clearQuestion();
            }
        }
        void clearQuestion()
        {
            WordPuzzle.cn.getTextWindow().setCursorPosition(5,20);
            WordPuzzle.cn.getTextWindow().output("                                                                                                            " +
                    "                                                                                                 " +
                    "                                                                                                 " +
                    "                                                                                                 " +
                    "                                                                                                 " +
                    "                                                                                                 ");
        }
        void checkAnswer(Player player1,Player player2,boolean turn) throws InterruptedException {
            Scanner scanner=new Scanner(System.in);
            String answer=scanner.next();
            if(answer.equals("a")||answer.equals("A"))
            {
                if(!turn)
                {
                    System.out.println("Answer is correct. Player-1 gets extra 10 points.");
                    player1.addScore(10);
                }
                else
                {
                    System.out.println("Answer is correct. Player-2 gets extra 10 points.");
                    player2.addScore(10);
                }
                WordPuzzle.cn.getTextWindow().setCursorPosition(46,1);
                System.out.println(player1.getScore());
                WordPuzzle.cn.getTextWindow().setCursorPosition(59,1);
                System.out.println(player2.getScore());
            }
            else
                System.out.println("You have entered wrong answer...");

        }
        String printAnswers(SingleLinkedList sll,String word)
        {
            wordNode temp = sll.head;
            String[] splittedWord;
            while(temp!=null){
                splittedWord=temp.getWord().split(",");
                if(splittedWord[0].equals("[x]"+word))
                    return "a)"+splittedWord[1]+" b) "+generateOption(sll)+" c) "+generateOption(sll);

                temp=temp.getNext();
            }
            return null;
        }

        String generateOption(SingleLinkedList sll)
        {
            String[] splitted;
            int rnd=WordPuzzle.random_generator(1,sll.size()-1);
            wordNode tmp=sll.head;
            for (int i = 0; i <rnd ; i++) {
                tmp=tmp.getNext();
            }
            splitted=tmp.getWord().split(",");
            return splitted[1];
        }
        public int left_edge()
        {
            int left_edge=WordPuzzle.px;
            if(left_edge>1)
            {
                if(left_edge+1>=15)
                {
                    while(!WordPuzzle.main_board[WordPuzzle.py][left_edge - 1].equals("#") &&left_edge>1)
                        left_edge--;
                }
                if(!WordPuzzle.main_board[WordPuzzle.py][left_edge - 1].equals("#")&& !WordPuzzle.main_board[WordPuzzle.py][left_edge + 1].equals("#"))
                {
                    while(!WordPuzzle.main_board[WordPuzzle.py][left_edge - 1].equals("#") &&left_edge>1)
                        left_edge--;
                    flag=true;
                }

            }
            return left_edge;
        }
        public int right_edge()
        {
            int right_edge=WordPuzzle.px;
            if(right_edge+1>=15)
                return WordPuzzle.px;

            else
            {
                if(!WordPuzzle.main_board[WordPuzzle.py][right_edge+1].equals("#")&&!WordPuzzle.main_board[WordPuzzle.py][right_edge-1].equals("#"))
                {
                    while(!WordPuzzle.main_board[WordPuzzle.py][right_edge].equals("#") &&right_edge<14)
                        right_edge++;
                    if(WordPuzzle.main_board[WordPuzzle.py][right_edge].equals("#"))
                        right_edge--;
                    flag=true;

                }
            }
            return right_edge;
        }
        public int up_edge()
        {
            int up_edge=WordPuzzle.py;
            if(up_edge>1)
            {
                if(!WordPuzzle.main_board[up_edge - 1][WordPuzzle.px].equals("#") /* && !WordPuzzle.main_board[up_edge + 1][WordPuzzle.px].equals("#")*/)
                {
                    while(!WordPuzzle.main_board[up_edge - 1][WordPuzzle.px].equals("#") &&up_edge>1)
                        up_edge--;
                }
            }
            return up_edge;
        }
        public int bottom_edge()
        {
            int bottom_edge=WordPuzzle.py;

            if(bottom_edge<14)
            {
                if(bottom_edge-1<0)
                {
                    while(!WordPuzzle.main_board[bottom_edge + 1][WordPuzzle.px].equals("#") &&bottom_edge<14)
                        bottom_edge++;
                }
                else if(!WordPuzzle.main_board[bottom_edge - 1][WordPuzzle.px].equals("#")/* && !WordPuzzle.main_board[bottom_edge + 1][WordPuzzle.px].equals("#")*/)
                {
                    while(!WordPuzzle.main_board[bottom_edge + 1][WordPuzzle.px].equals("#") &&bottom_edge<15)
                        bottom_edge++;
                }

            }
            return bottom_edge;
        }
        public boolean checkLetter(String letter)
        {
            if(WordPuzzle.test_board[WordPuzzle.py][WordPuzzle.px].equals(letter))
                return true;
            else
                return false;
        }
    }

}
