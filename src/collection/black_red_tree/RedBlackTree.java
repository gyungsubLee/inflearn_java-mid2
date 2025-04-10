package collection.black_red_tree;

import java.util.Objects;

public class RedBlackTree<E extends Comparable<E>> {

    private static final int RED = 1;
    private static final int BLACK = 0;

    private Node<E> root;

    public static class Node<E> {
        E value;
        int color;
        Node<E> left, right, parent;

        Node(E value) {
            this.value = value;
            this.color = RED;
        }

        public E getValue() {
            return value;
        }

        public Node<E> getLeft() {
            return this.left;
        }

        public Node<E> getRight() {
            return this.right;
        }

        public boolean isBlack() {
            return color == BLACK;
        }

        public boolean isRed() {
            return color == RED;
        }
    }

    public void insert(E value) {
        Node<E> newNode = new Node<>(value);
        if (root == null) {
            root = newNode;
            root.color = BLACK;
        } else {
            if (!insertNode(root, newNode)) {
                return; // 중복된 값이면 삽입하지 않음
            }
            fixInsert(newNode);
        }
    }

    private boolean insertNode(Node<E> current, Node<E> newNode) {
        int cmp = newNode.value.compareTo(current.value);
        if (cmp < 0) {
            if (current.left == null) {
                current.left = newNode;
                newNode.parent = current;
                return true;
            }
            return insertNode(current.left, newNode);
        } else if (cmp > 0) {
            if (current.right == null) {
                current.right = newNode;
                newNode.parent = current;
                return true;
            }
            return insertNode(current.right, newNode);
        } else {
            return false; // 중복
        }
    }

    private void fixInsert(Node<E> node) {
        while (node != root && node.parent.color == RED && node.parent.parent != null) {
            Node<E> parent = node.parent;
            Node<E> grandParent = parent.parent;

            if (parent == grandParent.left) {
                Node<E> uncle = grandParent.right;

                if (uncle != null && uncle.color == RED) {
                    parent.color = BLACK;
                    uncle.color = BLACK;
                    grandParent.color = RED;
                    node = grandParent;
                } else {
                    if (node == parent.right) {
                        node = parent;
                        rotateLeft(node);
                    }
                    node.parent.color = BLACK;
                    grandParent.color = RED;
                    rotateRight(grandParent);
                }
            } else {
                Node<E> uncle = grandParent.left;

                if (uncle != null && uncle.color == RED) {
                    parent.color = BLACK;
                    uncle.color = BLACK;
                    grandParent.color = RED;
                    node = grandParent;
                } else {
                    if (node == parent.left) {
                        node = parent;
                        rotateRight(node);
                    }
                    node.parent.color = BLACK;
                    grandParent.color = RED;
                    rotateLeft(grandParent);
                }
            }
        }

        while (node.parent != null) {
            node = node.parent;
        }
        root = node;
        root.color = BLACK;
    }

    private void rotateLeft(Node<E> node) {
        Node<E> rightChild = node.right;
        node.right = rightChild.left;

        if (rightChild.left != null) {
            rightChild.left.parent = node;
        }

        rightChild.parent = node.parent;

        if (node.parent == null) {
            root = rightChild;
        } else if (node == node.parent.left) {
            node.parent.left = rightChild;
        } else {
            node.parent.right = rightChild;
        }

        rightChild.left = node;
        node.parent = rightChild;
    }

    private void rotateRight(Node<E> node) {
        Node<E> leftChild = node.left;
        node.left = leftChild.right;

        if (leftChild.right != null) {
            leftChild.right.parent = node;
        }

        leftChild.parent = node.parent;

        if (node.parent == null) {
            root = leftChild;
        } else if (node == node.parent.right) {
            node.parent.right = leftChild;
        } else {
            node.parent.left = leftChild;
        }

        leftChild.right = node;
        node.parent = leftChild;
    }

    public void printTree() {
        printTree(root, "", true);
    }

    private void printTree(Node<E> current, String indent, boolean isLast) {
        if (current != null) {
            System.out.println(indent + (isLast ? "└── " : "├── ") + current.value + "(" + (current.color == RED ? "RED" : "BLACK") + ")");
            printTree(current.left, indent + (isLast ? "    " : "│   "), false);
            printTree(current.right, indent + (isLast ? "    " : "│   "), true);
        }
    }

    public Node<E> getRoot() {
        return root;
    }

    public boolean contains(E value) {
        Node<E> current = root;
        while (current != null) {
            int cmp = value.compareTo(current.value);
            if (cmp < 0) current = current.left;
            else if (cmp > 0) current = current.right;
            else return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        RedBlackTree<?> that = (RedBlackTree<?>) object;
        return Objects.equals(root, that.root);
    }

    @Override
    public int hashCode() {
        return Objects.hash(root);
    }
}