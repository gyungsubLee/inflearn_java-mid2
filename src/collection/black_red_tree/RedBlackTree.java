package collection.black_red_tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
        while (node != root && node.parent != null && node.parent.color == RED) {
            Node<E> parent = node.parent;
            Node<E> grandParent = parent.parent;

            if (grandParent == null) break;

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
        System.out.println("rotateLeft at node: " + node.value);
        Node<E> rightChild = node.right;
        node.right = rightChild.left;

        if (rightChild.left != null) {
            rightChild.left.parent = node;
        }

        Node<E> originalParent = node.parent; // 💡 회전 전 부모 저장
        rightChild.parent = originalParent;

        if (originalParent == null) {
            root = rightChild;
        } else if (node == originalParent.left) {
            originalParent.left = rightChild;
        } else {
            originalParent.right = rightChild;
        }

        rightChild.left = node;
        node.parent = rightChild;
    }

    private void rotateRight(Node<E> node) {
        System.out.println("rotateRight at node: " + node.value);
        Node<E> leftChild = node.left;
        node.left = leftChild.right;

        if (leftChild.right != null) {
            leftChild.right.parent = node;
        }

        Node<E> originalParent = node.parent;
        leftChild.parent = originalParent;

        if (originalParent == null) {
            root = leftChild;
        } else if (node == originalParent.right) {
            originalParent.right = leftChild;
        } else {
            originalParent.left = leftChild;
        }

        leftChild.right = node;
        node.parent = leftChild;
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

    public void printTree() {
        printTree(root, 0, "ROOT");
    }

    private void printTree(Node<E> node, int depth, String connector) {
        if (node == null) return;

        // 오른쪽 자식 먼저 출력 (위쪽으로 보이게)
        printTree(node.right, depth + 1, "/");

        // 현재 노드 출력
        String indent = "       ".repeat(depth); // depth에 따라 들여쓰기 조절
        String label = node.value + "(" + (node.color == RED ? "R" : "B") + ")";
        System.out.println(indent + connector + "── " + label);

        // 왼쪽 자식 출력
        printTree(node.left, depth + 1, "\\");
    }
}