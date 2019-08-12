package epi.test_framework;

public class BinaryTreeWithLock {
    BinaryTreeWithLock left, right, parent;
    boolean isLocked;
    int numOfLockedNodesInSubtree;

    BinaryTreeWithLock(BinaryTreeWithLock parent) {
        this.parent = parent;
        isLocked = false;
        numOfLockedNodesInSubtree = 0;
    }

    boolean isLocked() {
        return isLocked;
    }

    // O(h)
    boolean lock() {
        if (isLocked || numOfLockedNodesInSubtree>0) {
            return false;
        }

        for (BinaryTreeWithLock iter = parent; iter != null; iter = iter.parent) {
            if (iter.isLocked) return false;
        }

        isLocked = true;
        for (BinaryTreeWithLock iter = parent; iter != null; iter=iter.parent) {
            iter.numOfLockedNodesInSubtree ++;
        }
        return true;
    }

    // O(h)
    void unLock() {
        if (isLocked) {
            isLocked = false;
            for (BinaryTreeWithLock iter = parent; iter!=null ; iter=iter.parent) {
                iter.numOfLockedNodesInSubtree--;
            }
        }
    }
}
