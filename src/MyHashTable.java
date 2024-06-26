
public class MyHashTable<K, V> {
    private class HashNode<K, V> {
        private K key;
        private V value;
        private HashNode<K, V> next;
        public HashNode(K key, V value) {
            this.key = key;
            this.value = value;
        }
        @Override
        public String toString() {
            return "{" + key + " " + value + "}";
        }

    }


    private HashNode<K, V>[] chainArray;
    private int M = 11;
    private int size;


    public MyHashTable() {
        chainArray = new HashNode[M];
    }


    public MyHashTable(int M) {
        this.M = M;
        chainArray = new HashNode[M];
    }


    private int hash(K key) {
        return Math.abs(key.hashCode() % M);
    }


    public int size(){
        return size;
    }


    public void put(K key, V value) {
        int index = hash(key);
        HashNode<K, V> node = chainArray[index];
        while (node != null) {
            if (node.key.equals(key)) {
                node.value = value;
                return;
            }
            node = node.next;
        }

        HashNode<K, V> newNode = new HashNode<>(key, value);
        newNode.next = chainArray[index];
        chainArray[index] = newNode;
        size++;
    }


    public V get(K key) {
        int index = hash(key);
        HashNode<K, V> node = chainArray[index];
        while (node != null) {
            if (node.key.equals(key)) {
                return node.value;
            }
            node = node.next;
        }
        return null;
    }


    public V remove(K key) {
        int index = hash(key);
        HashNode<K, V> node = chainArray[index];
        HashNode<K, V> prev = null;
        while (node != null) {
            if (node.key.equals(key)) {
                if (prev == null) {
                    chainArray[index] = node.next;
                }
                else {
                    prev.next = node.next;
                }
                size--;
                return node.value;
            }
            prev = node;
            node = node.next;
        }
        return null;
    }


    public boolean contains(V value) {
        for (HashNode<K, V> node : chainArray) {
            while (node != null) {
                if (node.value.equals(value)) {
                    return true;
                }
                node = node.next;
            }
        }
        return false;
    }


    public K getKey(V value) {
        for (HashNode<K, V> node : chainArray) {
            while (node != null) {
                if (node.value.equals(value)) {
                    return node.key;
                }
                node = node.next;
            }
        }
        return null;
    }
    public static void main(String[] args){
        MyHashTable table = new MyHashTable(10);
        table.put(1, "Ailand");
        table.put(2, "EXPO-2017");
        table.put(4, "Keruen");
        System.out.println(table.size());
        System.out.println(table.remove(2));
        System.out.println(table.remove(1));
        System.out.println(table.size());
        }

}