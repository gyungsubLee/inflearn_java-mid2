package collection.hashset.v1;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.stream.IntStream;

public class MyHashSetV1 {

    static final int DEFAULT_INITIAL_CAPACITY = 16;

    LinkedList<Integer>[] buckets;

    private int size = 0;
    private int capactity = DEFAULT_INITIAL_CAPACITY;

    public MyHashSetV1() {
        initBuckets();
    }

    public MyHashSetV1(int capactity) {
        this.capactity = capactity;
        initBuckets();
    }

    private void initBuckets() {
        buckets = new LinkedList[capactity];
        IntStream.range(0, capactity).forEach(i -> buckets[i] = new LinkedList<>());
    }

    public boolean add(int value) {
        int hashIndex = hashIndex(value);
        LinkedList<Integer> bucket = buckets[hashIndex];

        if (bucket.contains(value)) {
            return false;
        }
        bucket.add(value);
        size++;
        return true;
    }

    public boolean contains(int searchValue) {
        int hashIndex = hashIndex(searchValue);
        LinkedList<Integer> bucket = buckets[hashIndex];
        return bucket.contains(searchValue);
    }

    public boolean remove(int value) {
        int hashIndex = hashIndex(value);
        LinkedList<Integer> bucket = buckets[hashIndex];

        // 버킷에서 값을 제거하고 성공했다면 size 감소 및 true 반환
        if (bucket.remove(Integer.valueOf(value))) {
            size--;
            return true;
        }

        // 값이 존재하지 않아 제거 실패 시 false 반환
        return false;
    }

    private int hashIndex(int value) {
        return value % capactity;
    }

    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        return "MyHashSetV1{" +
                "buckets=" + Arrays.toString(buckets) +
                ", size=" + size +
                '}';
    }
}
