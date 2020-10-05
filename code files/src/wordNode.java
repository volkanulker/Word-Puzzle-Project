public class wordNode {
    private String word;
    private letterNode head;
    private wordNode next;
    public wordNode(String dataToAdd)
    {
        word=dataToAdd;
        next=null;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public letterNode getHead() {
        return head;
    }

    public void setHead(letterNode head) {
        this.head = head;
    }

    public wordNode getNext() {
        return next;
    }

    public void setNext(wordNode next) {
        this.next = next;
    }
    public void addWord(String letter, String wordToAdd)
    {
        if(head==null)
            System.out.println("Add a row before number");
        else{
            letterNode temp=head;
            while(temp != null)
            {
                if(letter.equals(temp.getData())){
                    wordNode temp2=temp.getRight();
                    if(temp2==null){
                        wordNode newnode=new wordNode(wordToAdd);
                        temp.setRight(newnode);
                    }
                    else
                    {
                        while(temp2.getNext() != null)
                            temp2=temp2.getNext();
                        wordNode newnode = new wordNode(word);
                        temp2.setNext(newnode);
                    }
                }
                temp=temp.getDown();
            }
        }
    }
    public void display()
    {
        if(head==null)
            System.out.println("linked list is empty");
        else{
            letterNode temp=head;
            while(temp != null)
            {
                System.out.println(temp.getData()+ " -->");
                wordNode temp2=temp.getRight();
                while( temp2 != null)
                {
                    System.out.println(temp2.getWord()+" ");
                    temp2=temp2.getNext();
                }
                temp=temp.getDown();
                System.out.println();
            }
        }

    }
}
