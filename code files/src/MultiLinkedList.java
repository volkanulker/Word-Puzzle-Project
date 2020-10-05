public class MultiLinkedList {

    private letterNode head;
    private letterNode down;
    private wordNode right;

    public MultiLinkedList() {
        head = null;
        down = null;
        right = null;
    }


    public letterNode getDown() {
        return down;
    }

    public void setDown(letterNode down) {
        this.down = down;
    }

    public wordNode getRight() {
        return right;
    }

    public void setRight(wordNode right) {
        this.right = right;
    }

    public letterNode getHead() {
        return head;
    }

    public void setHead(letterNode head) {
        this.head = head;
    }

    public void addLetter(char dataToAdd) {
        if (head == null) {
            letterNode newnode = new letterNode(dataToAdd);
            head=newnode;
        }
        else {
            letterNode temp = head;
            while (temp.getDown() != null)
                temp = temp.getDown();
            letterNode newnode = new letterNode(dataToAdd);
            temp.setDown(newnode);
        }
    }

    public int rowSize() {
        int count = 0;
        if (head == null)
            System.out.println("linked list is empty");
        else {
            letterNode temp = head;
            while (temp != null) {
                count++;
                temp = temp.getDown();
            }
        }
        return count;
    }

    public void displayLetters() {
        if (head == null)
            System.out.println("multi linked list is empty");
        else {
            letterNode temp = head;
            while (temp != null) {
                System.out.println(temp.getData());
                temp = temp.getDown();
            }

        }
    }

    public void addWord(wordNode word)
    {
        letterNode tmp=head;

        while(tmp!=null)
        {
            if(word.getWord().substring(0,1).equals(String.valueOf(tmp.getData())))
            {
                word.setHead(tmp);
                if(tmp.getRight()==null)
                {
                    wordNode newnode = new wordNode(word.getWord());
                    tmp.setRight(newnode);
                }
                else
                {
                    wordNode tmp1 =tmp.getRight();

                    if(tmp1 == null)
                        tmp1.setWord(word.getWord());
                    else
                    {
                        while(tmp1.getNext() != null )
                            tmp1=tmp1.getNext();
                        wordNode newnode = new wordNode(word.getWord());
                        tmp1.setNext(newnode);
                    }
                }
               break;
            }
            else
                tmp=tmp.getDown();
        }

    }
    public void addWordwithBracket(wordNode word)
    {
        letterNode tmp=head;

        while(tmp!=null)
        {
            if(word.getWord().substring(3,4).equals(String.valueOf(tmp.getData())))
            {
                word.setHead(tmp);
                if(tmp.getRight()==null)
                {
                    wordNode newnode = new wordNode(word.getWord());
                    tmp.setRight(newnode);
                }
                else
                {
                    wordNode tmp1 =tmp.getRight();

                    if(tmp1 == null)
                        tmp1.setWord(word.getWord());
                    else
                    {
                        while(tmp1.getNext() != null )
                            tmp1=tmp1.getNext();
                        wordNode newnode = new wordNode(word.getWord());
                        tmp1.setNext(newnode);
                    }
                }
                break;
            }
            else
                tmp=tmp.getDown();
        }

    }
    public  void printWordlist()
    {
        letterNode tmp=head;
        wordNode tmp1;
        int wx=24;
        int wy=4;
        WordPuzzle.cn.getTextWindow().setCursorPosition(wx,wy);

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
                WordPuzzle.cn.getTextWindow().setCursorPosition(wx,wy);
                System.out.print(tmp1.getWord());
                tmp1=tmp1.getNext();
                wy++;
            }
            tmp=tmp.getDown();
        }
    }
    public void display()
    {
        if(head==null)
            System.out.println("multi linked list is empty...");
        else
        {
            letterNode tmp=head;
            while(tmp != null)
            {
                wordNode tmp1=tmp.getRight();
                System.out.print(tmp.getData()+"-->");
                while(tmp1 != null)
                {
                    System.out.print(tmp1.getWord()+"-->");
                    tmp1=tmp1.getNext();
                }
                tmp=tmp.getDown();
                System.out.println();
            }
        }
    }
    public boolean search_Word(String word)
    {
        boolean flag=false;
        if(head==null)
            System.out.println("multi linked list is empty...");
        else
        {
            letterNode tmp=head;

                while(tmp != null)
                {
                    if(word.substring(3,4).equals(String.valueOf(tmp.getData())))
                    {
                        wordNode tmp1=tmp.getRight();
                        while(tmp1 != null)
                        {
                            if(tmp1.getWord().equals(word))
                            {
                                word =word.replace(word.charAt(1),'x');
                                tmp1.setWord(word);
                                WordPuzzle.words.printWordlist();
                                flag=true;
                                return flag;
                            }
                            tmp1=tmp1.getNext();
                        }
                    }
                    tmp=tmp.getDown();
                }
        }
        return flag;

    }

}
