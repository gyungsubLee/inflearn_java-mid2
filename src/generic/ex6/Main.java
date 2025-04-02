package generic.ex6;

public class Main {
    public static void main(String[] args) {
        GenericBox<Integer> box = new GenericBox<>();
        box.set(10);
        Integer result = box.get();
    }
}
