package collection.array.v3;

public class MyArrayListV3Main {

    public static void main(String[] args) {
        MyArrayListV3 list = new MyArrayListV3();

        // 끝에 추가 // O(1)
        list.add("a");
        list.add("b");
        list.add("c");
        System.out.println(list);

        // 원하는 위치에 추가
        System.out.println("addLast");
        list.add(3, "addLast");  // O(1),  Why? index=3 맨 끝 위치
        System.out.println(list);

        System.out.println("addLast");
        list.add(0, "addLast");  // O(n),  Why? 첫번쨰 요소까지 연산 처리를 해야됨
        System.out.println(list);

        // 삭제
        Object removed1 = list.remove(4); // remove Last O(1)
        System.out.println("removed(4) = " + removed1);
        System.out.println(list);


        Object removed2 = list.remove(0); // remove First O(n)
        System.out.println("removed(0) = " + removed2);
        System.out.println(list);
    }
}
