public class SingleLinkedList {
    wordNode head=null;
    wordNode tail=null;
    /*
     * Adds node the current list
     */
    public void addNode(String dataToAdd){
        if(head==null){ // new node is added to head
            wordNode temp = new wordNode(dataToAdd);
            head = temp;
            tail = temp;
        }else{ // new node is added to between nodes or end of the single linked list

            wordNode newnode=new wordNode("[ ]"+dataToAdd);
            if(dataToAdd.compareTo(head.getWord())<0)// when new node has high priority on the single linked list
            {
                newnode.setNext(head);
                head=newnode;
            }
            else
            {
                wordNode temp=head;
                while(temp.getNext() != null && dataToAdd.compareTo(temp.getNext().getWord())>0)// search on the linked list until new node has high priority to next node
                    temp=temp.getNext();
                if(temp.getNext() != null)
                {
                    wordNode node_saver=temp.getNext();
                    temp.setNext(newnode);
                    temp=temp.getNext();
                    temp.setNext(node_saver);
                }
                else
                    temp.setNext(newnode);
            }
          //  tail = tail.getNext();
        }
    }

    /*
     * Adds node at the start of the current list
     */
    public void addNodeAtStart(String str){
        if(head==null){
            wordNode temp = new wordNode(str);
            head = temp;
            tail = temp;
        }else{
            wordNode temp = new wordNode(str);
            temp.setNext(head);
            head = temp;
        }
    }


    /*
     * Removes the last node in the given list and updates tail node
     */
    public void removeNode(){
        wordNode temp = head;
        while(temp.getNext()!=null && temp.getNext().getNext()!=null){
            temp = temp.getNext();
        }
        temp.setNext(null);
        tail = temp;
    }

    /*
     * Removes the first node in the given list and updates head node
     *
     */
    public wordNode removeNodeAtStart(){
        //The first node would become zombie and should be garbage collected after the below operation
        wordNode save=head;
        head = head.getNext();
        return save;
    }

    /*
     * Removes the node at the given index in the given list and updates head node
     *
     */
    public void removeNodeAtCertainIndex(int index){
        wordNode temp = head;
        int count = 0;
        while(temp!=null && ++count!=index)
            temp = temp.getNext();
        temp.setWord( temp.getNext().getWord());
        temp.setNext(temp.getNext().getNext());
    }

    /*
     * Checks if a node with the given value exist in the list, returns true or false.
     * Alternatively you can return the index too.
     */
    public boolean search(String target){
        wordNode temp = head;
        while(temp!=null){
            if(temp.getWord().equals(target))
                return true;
        }
        return false;

    }

    /*
     * Checks if a node with the given value exist in the list, returns the index of the given value in the list.
     */
    public boolean searchAndSign(String word){
        wordNode temp = head;
        int index;
        while(temp!=null){
            index=temp.getWord().indexOf(",");
            if(temp.getWord().substring(0,index).equals(word))
            {
                word =word.replace(word.charAt(1),'x');
                temp.setWord(word+temp.getWord().substring(index));
                return true;
            }
            temp=temp.getNext();
        }
        return false;
    }
    public void printUnsigneds()
    {
        WordPuzzle.cn.getTextWindow().setCursorPosition(0,26);
       System.out.println("Uncompleted words -->");
        wordNode temp=head;
        int k;
        while(temp!=null)
        {
            k=temp.getWord().indexOf(",");
            if(!temp.getWord().substring(1, 2).equals("x"))
                System.out.print(temp.getWord().substring(0,k)+"-->");

            temp=temp.getNext();
        }

    }

    /*
     * Prints the current list
     */

    public void display(){
        wordNode temp = head;
        while(temp!=null){
            System.out.print(temp.getWord()+"->");
            temp = temp.getNext();
        }
    }
    public int size()
    {
        int size=0;
        wordNode temp=head;
        while(temp!=null)
        {
            temp=temp.getNext();
            size++;
        }
        return size;
    }


}
