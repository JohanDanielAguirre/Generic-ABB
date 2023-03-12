package datastructures;

public interface IBinarySearchTree<T1, T2> {

    // For testing purposes only in this exercise.
    public T2 getRoot();
    public T2 search(T1 key);
    public void insert(T1 key, T2 value);
    public T2 delete(T1 key);
    public String inOrder();
}

