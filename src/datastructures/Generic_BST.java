package datastructures;


public class Generic_BST<T1 extends Comparable<T1>,T2> implements IBinarySearchTree<T1,T2>{
    private Node<T1,T2> root;
    @Override
    public T2 getRoot() {
        if(root == null) {
            return null;
        }
        return root.getValue();
    }

    @Override
    public void insert(T1 key,T2 value) {
        if (root == null) { // arbol vacio
            root = new Node<T1,T2>(key,value);;
        } else {
            Node<T1,T2> node = new Node(key,value);
            insert(root,node);
        }
    }
    private void insert(Node <T1,T2> pointer, Node<T1,T2> newNode) {
        if (pointer.getKey().compareTo(newNode.getKey())<0) { // ir hacia el subarbol izquierdo
            if (pointer.getLeft() == null) {
                pointer.setLeft(newNode);
            } else {
                insert(pointer.getLeft(), newNode); // nueva recursion hacia el nivel inferior del left
            }
        } else if(pointer.getKey().compareTo(newNode.getKey())>0){ // ir hacia el subarbol derecho
            if (pointer.getRight() == null) {
                pointer.setRight(newNode);
            } else {
                insert(pointer.getRight(), newNode); // nueva recursion hacia el nivel inferior del right
            }
        } else if (pointer.getKey().compareTo(newNode.getKey())==0) {
            return;
        }
    }
    @Override

    public String inOrder(){
        return inorder(root);
    }
    private String inorder(Node<T1,T2> pointer) {
        String s = "";
        if (pointer != null) {
            s = s + inorder(pointer.getRight())+" ";
            s = s + (pointer.getKey() + " ");
            s = s + inorder(pointer.getLeft());
        }
        return s.trim();
    }

    @Override
    public T2 search(T1 val) {
        return search(root,val);
    }
    private T2 search(Node<T1,T2> pointer, T1 val){
        if(pointer==null){ // el nodo no es encontro
            return null;
        } else if (pointer.getKey().compareTo((val))==0) { // encontramos el nodo
            return pointer.getValue();
        } else if (pointer.getKey().compareTo((val))<0) { // debemos buscar a la izquierda
            return search(pointer.getLeft(), val);
        } else { // debemos buscar a la derecha
            return search(pointer.getRight(), val);
        }
    }
    @Override
    public T2 delete(T1 value){
        String s="";
        return delete(null, root, value);

    }

    private T2 delete(Node<T1,T2> parent, Node<T1,T2> pointer, T1 value){
        if(pointer==null){ // no lo encontramos
            return null;
        }
        if (pointer.getKey().compareTo(value)==0) { // encontramos el nodo
            if(pointer == root){
                if(pointer.getRight() == null && pointer.getLeft() == null){
                    root = null;
                }
                else if(pointer.getRight()!= null && pointer.getLeft() == null){
                    root = root.getRight();
                }
                else if(pointer.getRight() == null && pointer.getLeft() != null){
                    root = root.getLeft();
                }
                //Tiene ambos hijos
                else if(pointer.getRight() != null && pointer.getRight() != null){
                    Node<T1,T2> leftOverTree = root.getRight();
                    root = root.getLeft();
                    insert(leftOverTree.getKey(), leftOverTree.getValue());
                }
            }
            // 1. es hoja
            else if (pointer.getLeft()==null && pointer.getRight()==null){
                if(parent.getLeft()==pointer){
                    parent.setLeft(null);
                    return pointer.getValue();
                } else {
                    parent.setRight(null);
                    return pointer.getValue();
                }
            }
            // 2. tiene solo hijo izquierdo
            else if(pointer.getRight()==null){
                pointer.setRight(pointer.getLeft());
                T2 p= pointer.getLeft().getValue();
                pointer.setLeft(null);
                return p;
            }
            // 3. tiene solo un hijo derecho
            else if (pointer.getLeft()==null) {
                parent.setLeft(pointer.getRight());
                T2 p= pointer.getRight().getValue();
                pointer.setRight(null);
                return p;
            }
            // 4. tiene dos hijos
            else {
                Node<T1,T2> sucesor = getSucesor(pointer); // el siguiente del puntero
                pointer.setValue((sucesor.getValue())); // transferencia del contenido, no de referencias
                delete(pointer, pointer.getRight(),sucesor.getKey()); // eliminacion del sucesdor desde el subarbol derecho
                return pointer.getValue();
            }
            return pointer.getValue();

        } else if (pointer.getKey().compareTo(value)<0) { // buscamos a la izquierda
            delete(pointer, pointer.getLeft(), value);
        } else { // buscamos a la derecha
            delete(pointer, pointer.getRight(), value);
        }
        return pointer.getValue();
    }
    private Node getMin(Node<T1,T2> pointer){
        if(pointer.getLeft()==null){
            return pointer;
        } else {
            return getMin(pointer.getLeft());
        }
    }
    private Node getSucesor(Node<T1,T2> pointer) {
        return getMin(pointer.getRight());
    }
}