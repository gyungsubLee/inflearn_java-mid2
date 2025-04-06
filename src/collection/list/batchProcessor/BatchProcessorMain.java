package collection.list.batchProcessor;

import collection.list.MyArrayList;
import collection.list.MyLinkedList;

public class BatchProcessorMain {
    public static void main(String[] args) {
//        MyArrayList<Integer> list = new MyArrayList<>();
         MyLinkedList<Integer> list = new MyLinkedList<>();

        BatchProcessorV2 processor = new BatchProcessorV2(list);
        processor.logic(50_000);
    }
}