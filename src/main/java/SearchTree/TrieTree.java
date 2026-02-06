package SearchTree;

import java.util.HashMap;
import java.util.Map;

public class TrieTree {
    TrieNode root = new TrieNode();

    public void insert(String prefix) {
        TrieNode node = root;
        for (Character c : prefix.toCharArray()) {
            if (!node.children.containsKey(c)) {
                TrieNode newNode = new TrieNode();
                node.children.put(c, newNode);
            }
            node = node.children.get(c);
            node.counter += 1;
        }
    }

    public int startsWith(String prefix) {
        TrieNode node = root;
        for (Character c : prefix.toCharArray()) {
            if (node.children.containsKey(c)) {
                node = node.children.get(c);
            } else {return 0;}
        }
        return node.counter;
    }

    private class TrieNode {
        private Map<Character, TrieNode> children = new HashMap<>();
        private int counter = 0;
    }
}