import java.util.Random;

public class MyTestingClass {
    private int id;
    private String name;

    public MyTestingClass(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public int hashCode() {

        int hash = 7;
        hash = 31 * hash + id;
        hash = 31 * hash + (name == null ? 0 : name.charAt(0));
        return hash;
    }

    public static void main(String[] args) {

        MyHashTable<MyTestingClass, String> table = new MyHashTable<>();


        Random random = new Random();
        for (int i = 0; i < 10000; i++) {
            int id = random.nextInt(1000); // Генерируем случайное id
            String name = "Name" + i; // Генерируем случайное имя
            MyTestingClass key = new MyTestingClass(id, name);
            table.put(key, "Value" + i);
        }
    }
}
