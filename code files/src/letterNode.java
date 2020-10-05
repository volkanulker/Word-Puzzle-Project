public class letterNode {
    private char data;
    private letterNode head;
    private letterNode down;
    private wordNode right;
    public letterNode(char dataToAdd)
    {
        data=dataToAdd;
        head=null;
        down=null;
        right=null;
    }

    public char getData() {
        return data;
    }

    public void setData(char data) {
        this.data = data;
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

    public void addLetter(char dataToAdd)
    {
        if(head==null)
        {
            letterNode newnode=new letterNode(dataToAdd);
        }
        else
        {
            letterNode temp=head;
            while(temp.getDown()!=null)
                temp=temp.getDown();
            letterNode newnode=new letterNode(dataToAdd);
            temp.setDown(newnode);
        }
    }
    public int size()
    {
        int count=0;
        if(head==null)
            System.out.println("linked list is empty");
        else
        {
            letterNode temp=head;
            while(temp!=null)
            {
                count++;
                temp=temp.getDown();
            }
        }
        return count;
    }
    public void displayLetters()
    {
        if(head==null)
            System.out.println("multi linked list is empty");
        else
        {
            letterNode temp=head;
            while(temp!=null)
            {
                System.out.println(temp.getData());
                if(temp.down!=null)
                    temp=temp.getDown();
            }

        }
    }

}
