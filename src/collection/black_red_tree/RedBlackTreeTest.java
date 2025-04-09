package collection.black_red_tree;
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

        assertEquals(10, tree.getRootValue()); // 루트 값 확인
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
}