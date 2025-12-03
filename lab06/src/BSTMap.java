import org.apache.hc.core5.http.impl.nio.ClientHttp1IOEventHandlerFactory;

import java.util.*;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {

    private class Node {
        private K key;
        private V value;
        private Node left;
        private Node right;
        public Node(K k, V v) {
            this.key = k;
            this.value = v;
            this.left = null;
            this.right = null;
        }
    }

    private Node root;
    private int size;

    public BSTMap() {
        root = null;
        size = 0;
    }


    public int compareRoots(BSTMap<K, V> other) {
        return this.root.key.compareTo(other.root.key);
    }


    @Override
    public void put(K key, V value) {
        root = insert(key, value, root);
    }

    private Node insert(K key, V value, Node root) {
        if (root == null) {
            size++;
            return new Node(key, value);
        }
        if (root.key.compareTo(key) == 0) {
            root.value = value;
        }
        else if (root.key.compareTo(key) > 0)
            root.right = insert(key, value, root.right);
        else
            root.left = insert(key, value, root.left);
        return root;
    }


    @Override
    public V get(K key) {
        Node temp = find(key, root);
        if (temp == null) return null;
        else return temp.value;
    }

    private Node find(K key, Node root) {
        if (root == null) return null;
        else if (root.key.compareTo(key) == 0) return root;
        else if (root.key.compareTo(key) > 0) return find(key, root.right);
        else return find(key, root.left);
    }


    @Override
    public boolean containsKey(K key) {
        return find(key, root) != null;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public Set<K> keySet() {
        Set<K> result = new TreeSet<>();
        inorder(result, root);
        return result;
    }

    private void inorder(Set<K> result, Node root) {
        if (root == null) return;
        inorder(result, root.left);
        result.add(root.key);
        inorder(result, root.right);
    }

    private V deleteval;
    @Override
    public V remove(K key) {
        deleteval = null;
        root = removeHelper(root, key);
        return deleteval;
    }

    private Node removeHelper(Node root, K key) {
        if (root == null) return null;
        int cmp = key.compareTo(root.key);
        if (cmp > 0) {
            root.left = removeHelper(root.left, key);
            return root;
        }
        if (cmp < 0) {
            root.right = removeHelper(root.right, key);
            return root;
        }
        deleteval = root.value;
        size--;
        if (root.right == null && root.left == null) return null;
        if (root.left == null) return root.right;
        if (root.right == null) return root.left;

        Node presuc = findsuc(root.left);
        root.key = presuc.key;
        root.value = presuc.value;
        root.left = removeHelper(root.left, presuc.key);

        return root;

    }

    private Node findsuc(Node root) {
        while (root.right != null) root = root.right;
        return root;
    }


    @Override
    public Iterator<K> iterator() {
        return new BSTIterator(root);
    }

    private class BSTIterator implements Iterator<K> {
        private Stack<Node> stack = new Stack<>();

        public BSTIterator(Node root) {
            pushStack(root);
        }

        void pushStack(Node root) {
            while(root != null) {
                stack.push(root);
                root = root.left;
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.empty();
        }

        @Override
        public K next() {
            Node temp = stack.pop();
            if (temp.right != null) pushStack(temp.right);
            return temp.key;
        }
    }
}
