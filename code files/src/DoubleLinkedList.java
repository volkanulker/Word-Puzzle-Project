public class DoubleLinkedList {
    protected Node head;
    protected Node tail;
    public int size;

    public DoubleLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public int getSize() {
        return size;
    }
    // insert at start
    public void insertAtStart(String data) {
        Node nptr = new Node(data, null, null);
        if (head == null) {
            head = nptr;
            tail = head;
        } else {
            head.setLinkPrev(nptr);
            nptr.setLinkNext(head);
            head = nptr;
        }
        size++;
    }
    // insert at last
    public void addNode(String data) {
        Node nptr = new Node(data, null, null);
        if (head == null) {
            head = nptr;
            tail = head;
        } else {
            nptr.setLinkPrev(tail);
            tail.setLinkNext(nptr);
            tail = nptr;
        }
        size++;
    }

    public void insertAtPos(String data, int pos) {
        Node nptr = new Node(data, null, null);
        if (pos == 1) {
            addNode(data);
            return;
        }
        Node ptr = head;
        for (int i = 2; i <= size; i++) {
            if (i == pos) {
                Node tmp = ptr.getLinkNext();
                ptr.setLinkNext(nptr);
                nptr.setLinkPrev(ptr);
                nptr.setLinkNext(tmp);
                tmp.setLinkPrev(nptr);
            }
            ptr = ptr.getLinkNext();
        }
        size++;
    }

    public void deleteAtPos(int pos) {
        if (pos == 1) {
            if (size == 1) {
                head = null;
                tail = null;
                size = 0;
                return;
            }
            head = head.getLinkNext();
            head.setLinkPrev(null);
            size--;
            return;
        }
        if (pos == size) {
            tail =tail.getLinkPrev();
            tail.setLinkNext(null);
            size--;
        }
        Node ptr = head.getLinkNext();
        for (int i = 2; i <= size; i++) {
            if (i == pos) {
                Node p = ptr.getLinkPrev();
                Node n = ptr.getLinkNext();

                p.setLinkNext(n);
                n.setLinkPrev(p);
                size--;
                return;
            }
            ptr = ptr.getLinkNext();
        }
    }

    public Node getHead() {
        return head;
    }

    public void setHead(Node head) {
        this.head = head;
    }

    public Node getTail() {
        return tail;
    }

    public void setTail(Node tail) {
        this.tail = tail;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void printLinkedList()
    {
        Node temp = head;
        while(temp!=null)
        {
            System.out.print(temp.data+"<->");
         temp = temp.next;
        }
    }
    public int size()
    {
        int size=0;
        Node temp=head;
        while(temp!=null)
        {
            temp=temp.next;
            size++;
        }
        return size;
    }
    public void display()
    {
        Node temp=head;
        while(temp!=null)
        {
            if(temp.data.length()>3)
                System.out.print(temp.data+"<-->");
            temp=temp.next;
        }
    }
}