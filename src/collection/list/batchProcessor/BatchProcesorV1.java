package collection.list.batchProcessor;

import collection.list.MyLinkedList;

// Bad Case) 리펙토링 필요
public class BatchProcesorV1 {

    private final MyLinkedList<Integer> list = new MyLinkedList<>();

    public void logic(int size) {
        for (int i = 0; i < size; i++) {
            list.add(0, i);  // 앞에 추가
        }
    }
}
