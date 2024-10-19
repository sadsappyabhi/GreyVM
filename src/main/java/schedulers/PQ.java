package schedulers;

import java.util.*;

public class PQ<E> extends AbstractQueue<E> {
    private List<E> elems;
    private final int childrenPerNode = 2;
    private Comparator<E> comp;

    public PQ(Comparator<E> comp) {
        elems = new ArrayList<>();
        this.comp = comp;
    }

    /**
     * Helper method to get the index of a given node's parent in the array.
     * Works for any K-ary heaps (not just binary)
     *
     * @param childPosition index of the node whose parent is to be found
     * @return the index of the parent node
     */
    private int parentPosition(int childPosition) {
        return (childPosition - 1) / childrenPerNode;
    }

    /**
     * Helper method to retrieve the index of a given child of a parent node.
     *
     * @param parentPosition the node whose children we are seeking.
     * @param childIndex which child's position are we seeking, left, right, 3rd etc.?
     * @return the index of the child node.
     */
    private int childPosition(int parentPosition, int childIndex) {
        return (childrenPerNode * parentPosition) + childIndex + 1;
    }

    /**
     * Inserts the specified element into this queue if it is possible to do so
     * immediately without violating capacity restrictions. When using a capacity-restricted
     * queue, this method is generally preferable to add(E), which can fail to insert an
     * element only by throwing an exception.
     *
     * @param elem the element to add
     * @return true if the element was added successfully
     */
    @Override
    public boolean offer(E elem) {
        int childPos = elems.size();
        int parentPos =  parentPosition(childPos);
        elems.add(childPos, elem);

        while (childPos != 0 && comp.compare(elems.get(childPos), elems.get(parentPos)) > 0) {
            Collections.swap(elems, childPos, parentPos);
            childPos = parentPos;
            parentPos = parentPosition(childPos);
        }
        return true;
    }

    /**
     * Retrieves, but does not remove, the head of this queue, or returns null if this queue is empty.
     *
     * @return the head of the queue or null if empty
     */
    @Override
    public E peek() {
        return elems.isEmpty() ? null : elems.getFirst();
    }

    /**
     * Retrieves and removes the head of this queue, or returns null if this queue is empty.
     *
     * @return The head of the queue or null if empty
     */
    public E poll() {
        if (elems.isEmpty()) {
            return null;
        }
        E head =  elems.getFirst();
        elems.set(0, elems.getLast());
        bubbleDown(0);
        elems.removeLast();
        return head;
    }

    /**
     * This method should work much like poll(), except that the removal
     * doesn't happen at the root of the tree. Once you find the element to be removed,
     * swap the last value into that position, and perform the same down-heap operation you
     * would in poll(), but starting from that position instead of the root.
     *
     * @param o The object to remove from the queue
     * @return true if the object was successfully removed, false if not.
     */
    @Override
    public boolean remove(Object o) {
        for (E nextElem : this) {
            if (nextElem.equals(o)) {
                int elemPos =  elems.indexOf(nextElem);
                elems.set(elemPos, elems.getLast());
                bubbleDown(elemPos);
                elems.removeLast();
                return true;
            }
        }
        return false;
    }

    /**
     * Helper method to restore heap property by "bubbling down" from the root.
     * Encapsulated here to avoid code duplication between poll() and remove(Object o).
     *
     * @param rootIndex the root position of the heap to bubble down from.
     */
    private void bubbleDown(int rootIndex) {
        int maxPos = rootIndex;
        int[] children =  new int[childrenPerNode];

        for (int i = 0; i < childrenPerNode; i++) {
            if (childPosition(rootIndex, i) >= elems.size()) {
                break;
            }
            children[i] = childPosition(rootIndex, i);
            if (comp.compare(elems.get(children[i]), elems.get(maxPos)) > 0) {
                maxPos = children[i];
            }
        }

        if (maxPos == rootIndex) {
            return;
        }
        else {
            Collections.swap(elems, maxPos, rootIndex);
            bubbleDown(maxPos);
        }
    }

    @Override
    public Iterator<E> iterator() {
        return elems.iterator();
    }

    @Override
    public int size() {
        return elems.size();
    }

}
