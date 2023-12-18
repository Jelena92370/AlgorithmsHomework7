import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class SearchTree {


        private Node root;

        static private class Node {
            String key;
            Integer value;
            //List<Node> children;
            Node left;
            Node right;

            public Node(String key, Integer value) {
                this.key = key;
                this.value = value;
                this.left = null;
                this.right = null;
            }
        }

        public Integer get(String key) {
            Node current = root;

            while (current != null) {
                int result = key.compareTo(current.key);
                if (result == 0) return current.value;
                else if (result < 0) current = current.left;
                else current = current.right;

            }
            return null;
        }

        public void add(String key, Integer value) {
            root = add(root, key, value);
        }

        private Node add(Node current, String key, Integer value) {
            if (current == null) {
                current = new Node(key, value);
            } else {
                int result = key.compareTo(current.key);
                if (result == 0) current.value = value;
                else if (result < 0) {
                    current.left = add(current.left, key, value);
                } else current.right = add(current.right, key, value);
            }
            return current;
        }

        public Iterable<String> getAllKeys() {
            Queue<String> queue = new LinkedList<>();
            inorder(queue, root);
            return queue;
        }

        private void inorder(Queue<String> queue, Node current) {
            if (current.left != null) inorder(queue, current.left);
            queue.add(current.key);

            if (current.right != null) inorder(queue, current.right);
        }

        public static String[] sortWithTree(String[] data) {
            SearchTree tree = new SearchTree();
            for (String s : data) {
                tree.add(s, 0);
            }
            Iterable<String> keys = tree.getAllKeys();
            String[] sortedData = new String[data.length];
            int i = 0;
            for (String s : keys) {
                sortedData[i++] = s;

            }
            return sortedData;

        }


        public String getMaxKey() {
            if (root == null) {
                return null;
            }
            Node current = root;
            while (current.right != null) {
                current = current.right;
            }

            return current.key;
        }

        public String searchByValueBFS(Integer value) {
            if (root == null) return null;
            Node current = root;
            Queue<Node> queue = new LinkedList<>();
            queue.add(root);
            while (!queue.isEmpty()) {
                current = queue.remove();
                if(current.value.equals(value)) return current.key;
                if(current.left != null) queue.add(current.left);
                if(current.right != null) queue.add(current.right);
            }
            return null;
        }

    public String searchByValueDFS(Integer value) {
        return searchByValueDFS(root, value);
    }

    private String searchByValueDFS(Node node, Integer value) {
        if (node == null) return null;

        if (node.value.equals(value)) return node.key;

        String targetKey = searchByValueDFS(node.left, value);
        if (targetKey != null) return targetKey;

        targetKey = searchByValueDFS(node.right, value);
        if (targetKey != null) return targetKey;

        return null;
    }

    public int estimateHeight() {
        return estimateHeight(root);
    }

    private int estimateHeight(Node node) {
        if (node == null) {
            return -1;
        }

        int leftHeight = estimateHeight(node.left);
        int rightHeight = estimateHeight(node.right);

        return Math.max(leftHeight, rightHeight) + 1;
        //Временная сложность метода O(n), так как метод должен пройтись по каждому узлу дерева
    }


        public static void main(String[] args) {
            SearchTree tree = new SearchTree();
            tree.add("G", 90);
            tree.add("C", 10);
            tree.add("A", 20);
            tree.add("B", 30);
            tree.add("D", 40);
            tree.add("G", 90);

            System.out.println(tree.get("A"));
            System.out.println(tree.get("B"));
            System.out.println(tree.get("C"));
            System.out.println(tree.get("D"));
            System.out.println(tree.get("G"));
            System.out.println(tree.get("X"));

            System.out.println(tree.getAllKeys());

            String[] sorted = sortWithTree(new String[]{"Hello", "World", "C", "D", "A"});
            System.out.println(Arrays.toString(sorted));
            System.out.println(tree.getMaxKey());

            String key = tree.searchByValueDFS(30);
            System.out.println("Key for value 30: " + key);

            System.out.println("Estimated tree height = " + tree.estimateHeight());


        }
    }

