package collection.black_red_tree;

public class RedBlackTreeMain {

    public static void main(String[] args) {
        RedBlackTree<Integer> tree = new RedBlackTree<>();
        tree.insert(10);
        tree.insert(5);
        tree.insert(20);

        System.out.println(tree.contains(5));  // true
        System.out.println(tree.getRoot().getValue());  // 10
    }
}
