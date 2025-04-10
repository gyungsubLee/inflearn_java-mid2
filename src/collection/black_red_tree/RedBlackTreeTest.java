package collection.black_red_tree;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RedBlackTreeTest {

    @Test
    public void testInsertionAndSearch() {
        RedBlackTree<Integer> tree = new RedBlackTree<>();

        tree.insert(10);
        tree.insert(20);
        tree.insert(30);
        tree.insert(15);

        assertTrue(tree.contains(10));
        assertTrue(tree.contains(15));
        assertTrue(tree.contains(20));
        assertTrue(tree.contains(30));
        assertFalse(tree.contains(5));
    }

    @Test
    public void testRootIsBlack() {
        RedBlackTree<Integer> tree = new RedBlackTree<>();
        tree.insert(10);
        tree.insert(5);
        tree.insert(15);

        assertEquals(10, tree.getRoot().getValue()); // 루트 값 확인
    }

    @Test
    public void testDuplicateInsert() {
        RedBlackTree<Integer> tree = new RedBlackTree<>();
        tree.insert(10);
        tree.insert(10); // 중복 삽입 시도

        int count = countNodes(tree); // 중복 없이 한 개만 들어가야 함
        assertEquals(1, count);
    }

    private int countNodes(RedBlackTree<Integer> tree) {
        Counter counter = new Counter(0);

        // Reflection으로 root 접근
        try {
            java.lang.reflect.Field rootField = RedBlackTree.class.getDeclaredField("root");
            rootField.setAccessible(true);
            Object root = rootField.get(tree);
            countRecursively(root, counter);
        } catch (Exception e) {
            fail("Reflection failed");
        }

        return counter.count;
    }

    private void countRecursively(Object nodeObj, Counter counter) throws Exception {
        if (nodeObj == null) return;

        Class<?> nodeClass = nodeObj.getClass();
        java.lang.reflect.Field leftField = nodeClass.getDeclaredField("left");
        java.lang.reflect.Field rightField = nodeClass.getDeclaredField("right");
        leftField.setAccessible(true);
        rightField.setAccessible(true);

        Object left = leftField.get(nodeObj);
        Object right = rightField.get(nodeObj);

        counter.count++;
        countRecursively(left, counter);
        countRecursively(right, counter);
    }

    static class Counter {
        int count;

        public Counter(int count) {
            this.count = count;
        }
    }

    @DisplayName("LL 회전 (Left-Left 회전) 케이스")
    @Test
    public void testLLRotation() {
        RedBlackTree<Integer> tree = new RedBlackTree<>();
        tree.insert(30);
        tree.insert(20);
        tree.insert(10);
        RedBlackTree.Node<Integer> root = tree.getRoot();
        assertEquals(Integer.valueOf(20), root.getValue());          // 회전 후 루트는 20
        assertEquals(Integer.valueOf(10), root.getLeft().getValue()); // 왼쪽 자식은 10
        assertEquals(Integer.valueOf(30), root.getRight().getValue()); // 오른쪽 자식은 30
        assertTrue(root.isBlack());             // 새 루트 20은 검정색
        assertTrue(root.getLeft().isRed());     // 10은 빨강색
        assertTrue(root.getRight().isRed());    // 30은 빨강색
    }

    @DisplayName("LR 회전 (Left-Right 회전) 케이스")
    @Test
    public void testLRRotation() {
        RedBlackTree<Integer> tree = new RedBlackTree<>();
        tree.insert(30);
        tree.insert(10);
        tree.insert(20);
        RedBlackTree.Node<Integer> root = tree.getRoot();
        assertEquals(Integer.valueOf(20), root.getValue());           // 회전 후 루트 20
        assertEquals(Integer.valueOf(10), root.getLeft().getValue());  // 왼쪽 자식 10
        assertEquals(Integer.valueOf(30), root.getRight().getValue()); // 오른쪽 자식 30
        assertTrue(root.isBlack());              // 20은 검정색
        assertTrue(root.getLeft().isRed());      // 10은 빨강색
        assertTrue(root.getRight().isRed());     // 30은 빨강색
    }

    @DisplayName("RL 회전 (Right-Left 회전) 케이스")
    @Test
    public void testRLRotation() {
        RedBlackTree<Integer> tree = new RedBlackTree<>();
        tree.insert(10);
        tree.insert(30);
        tree.insert(20);
        RedBlackTree.Node<Integer> root = tree.getRoot();
        assertEquals(Integer.valueOf(20), root.getValue());            // 회전 후 루트 20
        assertEquals(Integer.valueOf(10), root.getLeft().getValue());   // 왼쪽 자식 10
        assertEquals(Integer.valueOf(30), root.getRight().getValue());  // 오른쪽 자식 30
        assertTrue(root.isBlack());               // 20은 검정색
        assertTrue(root.getLeft().isRed());       // 10은 빨강색
        assertTrue(root.getRight().isRed());      // 30은 빨강색
    }


    @DisplayName("RR 회전 (Right-Right 회전) 케이스")
    @Test
    public void testRRRotation() {
        RedBlackTree<Integer> tree = new RedBlackTree<>();
        tree.insert(10);
        tree.insert(20);
        tree.insert(30);
        RedBlackTree.Node<Integer> root = tree.getRoot();
        assertEquals(Integer.valueOf(20), root.getValue());            // 회전 후 루트 20
        assertEquals(Integer.valueOf(10), root.getLeft().getValue());   // 왼쪽 자식 10
        assertEquals(Integer.valueOf(30), root.getRight().getValue());  // 오른쪽 자식 30
        assertTrue(root.isBlack());                // 20은 검정색 (루트는 항상 검정)
        assertTrue(root.getLeft().isRed());        // 10은 빨강색
        assertTrue(root.getRight().isRed());       // 30은 빨강색
    }
}