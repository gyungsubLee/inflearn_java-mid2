package collection.link.node;

public class Node {

    public Object item;
    public Node next;

    public Node(Object item) {
        this.item = item;
    }

    //    @Override
//    public String toString() {
//        return "Node{" +
//                "item=" + item +
//                ", next=" + next +
//                '}';
//    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        Node x = this;
        sb.append("[");
        while (x != null) {
            sb.append(x.item);
            if (x.next != null) {
                sb.append("->");
            }
            x = x.next;
        }
        sb.append("]");
        return sb.toString();
    }
}
