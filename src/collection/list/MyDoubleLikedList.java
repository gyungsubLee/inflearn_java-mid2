//package collection.list;
//
//public class MyDoubleLikedList<E> implements MyList<E> {
//    private Node<E> head;
//    private Node<E> tail;
//    private int size = 0;
//
//    @Override
//    public void add(E e) {
//        Node<E> newNode = new Node<>(e);
//
//        if (size == 0) {
//            head = newNode;
//            tail = newNode;
//        } else {
//            tail.next = newNode;   // 현재 tail의 next가 새 노드를 가르키게 함
//            newNode.prev = tail;   // 새 노드의 prev가 현재 tail을 가르킴
//            tail = newNode;        // tail을 새 노드로 갱신
//        }
//
//        size++;
//    }
//
//    @Override
//    public void add(int index, E e) {
//        Node<E> newNode = new Node<>(e);
//
//        if
//    }
//
//    @Override
//    public E set(int index, E elemenet) {
//        return null;
//    }
//
//    @Override
//    public E remove(int index) {
//        return null;
//    }
//
//    @Override
//    public int indexOf(E o) {
//        return 0;
//    }
//
//    @Override
//    public E get(int index) {
//        return null;
//    }
//
//    @Override
//    public int size() {
//        return size;
//    }
//
//    @Override
//    public String toString() {
//        return "MyLikedList{" +
//                "head=" + (head == null ? "[]" : head.forwardString()) +
//                ", tail=" + (tail == null ? "[]" : tail.backwardString()) +
//                ", size=" + size +
//                '}';
//    }
//
//    private static class Node<E> {
//        private E data;
//        private Node<E> next;
//        private Node<E> prev;
//
//        public Node(E data) {
//            this.data = data;
//        }
//
//        @Override
//        public String toString() {
//            return forwardString(); // 기본은 next 방향 출력
//        }
//
//        public String forwardString() {
//            StringBuilder sb = new StringBuilder();
//            Node<E> temp = this;
//            sb.append("[");
//            while (temp != null) {
//                sb.append(temp.data);
//                if (temp.next != null) {
//                    sb.append("->");
//                }
//                temp = temp.next;
//            }
//            sb.append("]");
//            return sb.toString();
//        }
//
//        public String backwardString() {
//            StringBuilder sb = new StringBuilder();
//            Node<E> temp = this;
//            sb.append("[");
//            while (temp != null) {
//                sb.append(temp.data);
//                if (temp.prev != null) {
//                    sb.append("->");
//                }
//                temp = temp.prev;
//            }
//            sb.append("]");
//            return sb.toString();
//        }
//    }
//}
