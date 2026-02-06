package SearchTree;

import model.GroceryItem;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class TrieTree {
    TrieNode root = new TrieNode();

    public void insert(GroceryItem item) {
        TrieNode node = root;
        for (Character c : item.name().toCharArray()) {
            if (!node.children.containsKey(c)) {
                TrieNode newNode = new TrieNode();
                node.children.put(c, newNode);
            }
            node = node.children.get(c);
        }
        node.isItem = true;
        node.category = item.category();
    }

    public Set<GroceryItem> startsWith(String prefix) {
        TrieNode node = root;
        for (Character c : prefix.toCharArray()) {
            if (node.children.containsKey(c)) {
                node = node.children.get(c);
            } else {return new HashSet<>();}
        }
        return searchHits(prefix, node.children, new HashSet<>());
    }

    private Set<GroceryItem> searchHits(String prefix, Map<Character, TrieNode> children, Set<GroceryItem> results) {
        for (Map.Entry<Character, TrieNode> entry : children.entrySet()) {
            String newPrefix = prefix + entry.getKey();
            TrieNode currentNode = entry.getValue();

            if (currentNode.isItem) {
                results.add(new GroceryItem(newPrefix, currentNode.category));
            }
            searchHits(newPrefix, currentNode.children, results);
        }
        return results;
    }

    private static class TrieNode {
        private final Map<Character, TrieNode> children = new HashMap<>();
        private boolean isItem = false;
        private String category;
    }
}