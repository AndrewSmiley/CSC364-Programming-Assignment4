package csc364pa4;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class BST<E extends Comparable<E>> implements Tree<E> {
    protected TreeNode<E> root;
    protected int size = 0;

    /** Create a default binary tree */
    public BST() {
    }

    /** Create a binary tree from an array of objects */
    public BST(E[] objects) {
        for (int i = 0; i < objects.length; i++)
            add(objects[i]);
    }

    @Override /** Returns true if the element is in the tree */
    public boolean search(E e) {
        TreeNode<E> current = root; // Start from the root

        while (current != null) {
            if (e.compareTo(current.element) < 0) {
                current = current.left;
            }
            else if (e.compareTo(current.element) > 0) {
                current = current.right;
            }
            else // element matches current.element
                return true; // Element is found
        }

        return false;
    }


    public TreeSet<E> searchTrace(E e){
        TreeSet<E> set = new TreeSet<E>();
        TreeNode<E> current = root; // Start from the root
        set.add(root.element);
        while (current != null) {
            if (e.compareTo(current.element) < 0) {
                set.add(current.element);
                current = current.left;


            }
            else if (e.compareTo(current.element) > 0) {
                set.add(current.element);
                current = current.right;
            }
            else { // element matches current.element
                set.add(e);
                return set; // Element is found
            }
        }
        return set;
    }
    @Override /** Insert element o into the binary tree
     * Return true if the element is inserted successfully */
    public boolean insert(E e) {
        if (root == null)
            root = createNewNode(e); // Create a new root
        else {
            // Locate the parent node
            TreeNode<E> parent = null;
            TreeNode<E> current = root;
            while (current != null)
                if (e.compareTo(current.element) < 0) {
                    parent = current;
                    current = current.left;
                }
                else if (e.compareTo(current.element) > 0) {
                    parent = current;
                    current = current.right;
                }
                else
                    return false; // Duplicate node not inserted

            // Create the new node and attach it to the parent node
            if (e.compareTo(parent.element) < 0)
                parent.left = createNewNode(e);
                //parent.left = new TreeNode<>(e);
            else
                parent.right = createNewNode(e);
        }

        size++;
        return true; // Element inserted successfully
    }

    protected TreeNode<E> createNewNode(E e) {
        return new TreeNode<>(e);
    }

    @Override /** Inorder traversal from the root */
    public void inorder() {

        inorder(root);


    }
    public ArrayList<E> inorder(ArrayList<E> contents) {
        inorder(root,contents);
        return contents;

    }

    protected void inorder(TreeNode<E> root) {

        if (root == null) return;
        inorder(root.left);
        System.out.print(root.element + " ");

        inorder(root.right);
    }

    /** Inorder traversal from a subtree */
    protected void inorder(TreeNode<E> root, ArrayList<E> list) {

        if (root == null) return;
        inorder(root.left, list);
        System.out.print(root.element + " ");
        list.add(root.element);
        inorder(root.right,list);
    }

    @Override /** Postorder traversal from the root */
    public void postorder() {
        postorder(root);
    }
    public ArrayList<E> postorder(ArrayList<E>  contents) {
        postorder(root,contents);
        return contents;
    }

    /** Postorder traversal from a subtree */
    protected void postorder(TreeNode<E> root) {
        if (root == null) return;
        postorder(root.left);
        postorder(root.right);
        System.out.print(root.element + " ");
    }

    /** Postorder traversal from a subtree */
    protected void postorder(TreeNode<E> root, ArrayList<E> contents) {
        if (root == null) return;
        postorder(root.left,contents);
        postorder(root.right,contents);
        System.out.print(root.element + " ");
        contents.add(root.element);
    }

    @Override /** Preorder traversal from the root */
    public void preorder() {
        preorder(root);
    }

    public ArrayList<E> preorder(ArrayList<E> contents) {
        preorder(root,contents);
        return contents;

    }

    /** Preorder traversal from a subtree */
    protected void preorder(TreeNode<E> root) {
        if (root == null) return;
        System.out.print(root.element + " ");
        preorder(root.left);
        preorder(root.right);
    }
    protected void preorder(TreeNode<E> root,ArrayList<E> contents) {
        if (root == null) return;
        contents.add(root.element);
        System.out.print(root.element + " ");
        preorder(root.left,contents);
        preorder(root.right, contents);

    }

    protected ArrayList<E> breadthFirst(ArrayList<E> contents){
        contents.add(root.element);
        breadthFirst(root, contents);

        return contents;
    }
    protected void breadthFirst(TreeNode<E> root, ArrayList<E> contents){
        if(root==null) return;
        if(root.left != null){
            contents.add(root.left.element);
        }
        if(root.right !=null) {
            contents.add(root.right.element);
        }
        breadthFirst(root.left, contents);
        breadthFirst(root.right, contents);
//        contents.add(root.left.element);
//        contents.add(root.right.element);
    }

    protected Integer height(){
        Integer lheight = -1;

        lheight = height(root, lheight);
        return lheight;

    }
    protected int height(TreeNode<E> root, Integer l ){
        if(root == null) return l;
        System.out.println("Recursing: "+l.toString());
        return Math.max(height(root.left,l++),height(root.right,l++));


    }


    /** This inner class is static, because it does not access
     any instance members defined in its outer class */
    public static class TreeNode<E> {
        protected E element;
        protected TreeNode<E> left;
        protected TreeNode<E> right;

        public TreeNode(E e) {
            element = e;
        }
    }

    @Override /** Get the number of nodes in the tree */
    public int getSize() {
        return size;
    }

    /** Returns the root of the tree */
    public TreeNode<E> getRoot() {
        return root;
    }

    /** Returns a path from the root leading to the specified element */
    public java.util.ArrayList<TreeNode<E>> path(E e) {
        java.util.ArrayList<TreeNode<E>> list =
                new java.util.ArrayList<>();
        TreeNode<E> current = root; // Start from the root

        while (current != null) {
            list.add(current); // Add the node to the list
            if (e.compareTo(current.element) < 0) {
                current = current.left;
            }
            else if (e.compareTo(current.element) > 0) {
                current = current.right;
            }
            else
                break;
        }

        return list; // Return an array list of nodes
    }

    @Override /** Delete an element from the binary tree.
     * Return true if the element is deleted successfully
     * Return false if the element is not in the tree */
    public boolean delete(E e) {
        // Locate the node to be deleted and also locate its parent node
        TreeNode<E> parent = null;
        TreeNode<E> current = root;
        while (current != null) {
            if (e.compareTo(current.element) < 0) {
                parent = current;
                current = current.left;
            }
            else if (e.compareTo(current.element) > 0) {
                parent = current;
                current = current.right;
            }
            else
                break; // Element is in the tree pointed at by current
        }

        if (current == null)
            return false; // Element is not in the tree

        // Case 1: current has no left child
        if (current.left == null) {
            // Connect the parent with the right child of the current node
            if (parent == null) {
                root = current.right;
            }
            else {
                if (e.compareTo(parent.element) < 0)
                    parent.left = current.right;
                else
                    parent.right = current.right;
            }
        }
        else {
            // Case 2: The current node has a left child
            // Locate the rightmost node in the left subtree of
            // the current node and also its parent
            TreeNode<E> parentOfRightMost = current;
            TreeNode<E> rightMost = current.left;

            while (rightMost.right != null) {
                parentOfRightMost = rightMost;
                rightMost = rightMost.right; // Keep going to the right
            }

            // Replace the element in current by the element in rightMost
            current.element = rightMost.element;

            // Eliminate rightmost node
            if (parentOfRightMost.right == rightMost)
                parentOfRightMost.right = rightMost.left;
            else
                // Special case: parentOfRightMost == current
                parentOfRightMost.left = rightMost.left;
        }

        size--;
        return true; // Element deleted successfully
    }

    @Override /** Obtain an iterator. Use inorder. */
    public java.util.Iterator<E> iterator() {
        return new InorderIterator();
    }

    // Inner class InorderIterator
    private class InorderIterator implements java.util.Iterator<E> {
        // Store the elements in a list
        private java.util.ArrayList<E> list =
                new java.util.ArrayList<>();
        private int current = 0; // Index of next element in the iteration
        private boolean canRemove = false;

        public InorderIterator() {
            inorder(); // Traverse binary tree and store elements in list
        }

        /** Inorder traversal from the root*/
        private void inorder() {
            inorder(root);
        }

        /** Inorder traversal from a subtree */
        private void inorder(TreeNode<E> root) {
            if (root == null)return;
            inorder(root.left);
            list.add(root.element);
            inorder(root.right);
        }

        @Override /** More elements for traversing? */
        public boolean hasNext() {
            if (current < list.size())
                return true;

            return false;
        }

        @Override /** Get the current element and move to the next */
        public E next() {
            if (hasNext())
                canRemove = true;
            else
                throw new java.util.NoSuchElementException();
            return list.get(current++);
        }

        @Override /** Remove the current element */
        public void remove() {
            if (!canRemove)
                throw new IllegalStateException();
            else {
                delete(list.get(current-- - 1));
                canRemove = false;
            }

            list.clear(); // Clear the list
            inorder(); // Rebuild the list
        }
    }

    @Override /** Remove all elements from the tree */
    public void clear() {
        root = null;
        size = 0;
    }
}