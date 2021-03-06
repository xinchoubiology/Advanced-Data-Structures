
import java.util.NoSuchElementException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Dimitar
 *
 * Data type that represents a min-heap implementation of Skew heap data
 * structure
 */
public class SkewHeap {

    /**
     * Reference to the root for the current skew heap
     */
    private Node root;

    /**
     * Data type that represents a node in the Skew heap
     */
    private static class Node {

        int value; // data value
        Node left; // left subtree
        Node right; // right subtree

        Node(int value) {
            this.value = value;
        }
    }

    public SkewHeap() {
    }

    /**
     * Method that adds a node with data provided by @value in the current skew
     * heap.
     *
     * @param value: value to be added in the heap
     */
    public void add(int value) {
        this.root = merge(this.root, new Node(value));
    }

    /**
     * This method removes and returns the smallest element in the current skew
     * heap.
     *
     * @return the removed element
     *
     * @throws NoSuchElementException if there are no elements, but minimum was
     * tried to be removed
     */
    public int removeMin() {
        if (!empty()) {
            int min = this.root.value;
            this.root = merge(root.left, root.right);
            return min;
        } else {
            throw new NoSuchElementException("The heap is empty!");
        }
    }

    /**
     * Tests whether there are any elements in the current heap.
     *
     * @return true, if there aren't any elements and false, otherwise
     */
    public boolean empty() {
        return root == null;
    }

    /**
     * Method that merges the current skew heap with the given by @other. This
     * method destructs the @other skew heap while merging it.
     *
     * @param other : reference to the skew heap data structure that will be
     * merged with the current one
     */
    public void merge(SkewHeap other) {
        if (other != null) {
            this.root = merge(this.root, other.root);
            other.root = null;
        }
        //  I would usually throw NullPointerException here, but is not explicitly
        //  said to do so.
    }

    /**
     * Method that merges two skew heap data structures referenced to their
     * roots by @root1 and @root2
     *
     * @param root1: reference to the root of the first skew heap
     *
     * @param root2: reference to the root of the second skew heap
     *
     * @return a reference to the root of the merged data structure
     */
    private Node merge(Node root1, Node root2) {

        Node firstRoot = root1;
        Node secondRoot = root2;

        if (firstRoot == null) {
            return secondRoot;
        } else if (secondRoot == null) {
            return firstRoot;
        }

        if (firstRoot.value <= secondRoot.value) {
            Node temp = firstRoot.right;
            firstRoot.right = firstRoot.left;
            firstRoot.left = merge(secondRoot, temp);
            return firstRoot;
        } else {
            return merge(secondRoot, firstRoot);
        }
    }
}
