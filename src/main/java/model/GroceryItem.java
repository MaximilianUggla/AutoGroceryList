package model;

public record GroceryItem(String name, String category) {

    @Override
    public String toString() {
        return name;
    }
}
