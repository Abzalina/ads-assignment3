import java.util.*;

public class BST<K extends Comparable<K>, V> {
    private Node root;
    private int size;

    private class Node {
        private K key;
        private V val;
        private Node left, right;

        public Node(K key, V val) {
            this.key = key;
            this.val = val;
        }
    }

    public void put(K key, V val) {
        root = put(root, key, val);
    }

    private Node put(Node current, K key, V val) {
        if (current == null) {
            size++;
            return new Node(key, val);
        }
        int cmp = key.compareTo(current.key);
        if (cmp < 0)
            current.left = put(current.left, key, val);
        else if (cmp > 0)
            current.right = put(current.right, key, val);
        else
            current.val = val;
        return current;
    }

    public V get(K key) {
        Node current = root;
        while (current != null) {
            int cmp = key.compareTo(current.key);
            if (cmp < 0)
                current = current.left;
            else if (cmp > 0)
                current = current.right;
            else
                return current.val;
        }
        return null;
    }

    public void delete(K key) {
        root = delete(root, key);
    }

    private Node delete(Node current, K key) {
        if (current == null) return null;

        int cmp = key.compareTo(current.key);
        if (cmp < 0)
            current.left = delete(current.left, key);
        else if (cmp > 0)
            current.right = delete(current.right, key);
        else {
            if (current.right == null) return current.left;
            if (current.left == null) return current.right;
            Node t = current;
            current = min(t.right);
            current.right = deleteMin(t.right);
            current.left = t.left;
        }
        return current;
    }

    private Node min(Node current) {
        while (current.left != null)
            current = current.left;
        return current;
    }

    private Node deleteMin(Node current) {
        if (current.left == null) return current.right;
        current.left = deleteMin(current.left);
        return current;
    }

    public Iterable<K> iterator() {
        List<K> keys = new ArrayList<>();
        inorder(root, keys);
        return keys;
    }

    private void inorder(Node current, List<K> keys) {
        if (current == null) return;
        inorder(current.left, keys);
        keys.add(current.key);
        inorder(current.right, keys);
    }

    public int size() {
        return size;
    }
    public static void main(String[] args) {
        BST<Integer, String> binarytree = new BST<>();
        binarytree.put(5, "Five");
        binarytree.put(3, "Three");
        binarytree.put(15, "Fifteen");
        binarytree.put(12, "Twelve");
        binarytree.put(7, "Seven");
        binarytree.put(2, "Two");
        binarytree.put(8, "Eight");
        binarytree.put(10, "Ten");


        for (var elem : binarytree.iterator()) {
            System.out.println("key is " + elem.key + " and value is " + elem.val;
        }
    }
}
