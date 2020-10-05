public class Node {
        protected String data;
        protected Node next, prev;

        public Node() {
            next = null;
            prev = null;
            data = null;
        }

        public Node(String d, Node n, Node p) {
            data = d;
            next = n;
            prev = p;
        }

        public void setLinkNext(Node n) {
            next = n;
        }

        public void setLinkPrev(Node p) {
            prev = p;
        }

        public Node getLinkNext() {
            return next;
        }

        public Node getLinkPrev() {
            return prev;
        }


    }

