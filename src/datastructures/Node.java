package datastructures;

public class Node<T1,T2> {
    //Datos
    private T1 key;
    private T2 value;
    //Enlaces
    private Node< T1,T2> left;
    private Node <T1,T2>right;

    public Node(T1 icon,T2 value) {
        this.value=value;
        this.key = icon;
    }
    public Node <T1,T2> getLeft() {
        return left;
    }

    public void setLeft(Node <T1,T2> left) {
        this.left = left;
    }

    public Node <T1,T2> getRight() {
        return right;
    }

    public void setRight(Node <T1,T2> right) {
        this.right = right;
    }

    public T1 getKey() {
        return key;
    }

    public void setKey(T1 key) {
        this.key = key;
    }

    public void setIcon(T1 key) {
        this.key = key;
    }

    public T2 getValue() {
        return value;
    }

    public void setValue(T2 value) {
        this.value = value;
    }
}
