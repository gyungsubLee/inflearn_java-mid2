package collection.black_red_tree;

public class RedBlackTreeMain {

    public static void main(String[] args) {
        RedBlackTree<Integer> tree = new RedBlackTree<>();
        int[] values = {10, 5, 15, 1, 6};

        for (int v : values) {
            System.out.println("\n[삽입] " + v);
            tree.insert(v);
            tree.printTree();
        }
    }
}
