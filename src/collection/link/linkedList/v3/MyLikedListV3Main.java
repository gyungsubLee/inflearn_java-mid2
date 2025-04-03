package collection.link.linkedList.v3;

public class MyLikedListV3Main {
    public static void main(String[] args) {
        MyLikedListV3<String> stringList = new MyLikedListV3<>();
        stringList.add("a");
        stringList.add("b");
        stringList.add("c");
        String string = stringList.get(0);
        System.out.println("string = " + string);

        MyLikedListV3<Integer> intList = new MyLikedListV3<>();
        intList.add(1);
        intList.add(2);
        intList.add(3);
        Integer integer = intList.get(0);
        System.out.println("integer = " + integer);
    }
}
