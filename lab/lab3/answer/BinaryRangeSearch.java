package exer;

public class BinaryRangeSearch {
    /**
     * Search a key in a part of the array (which is [fromIndex, toIndex-1]) using binary search. The range [fromIndex, toIndex-1] must be sorted before searching.
     * If array is not sorted on the interval [fromIndex, toIndex-1], calling rangeSearch(array, fromIndex, toIndex, key) results in undefined behavior.
     * @param array the array to be searched
     * @param fromIndex the index of the first element(inclusive) to be searched
     * @param toIndex the index of the last element(exclusive) to be searched
     * @param key the key to be searched
     * @return an array of length two: [first, last]. If key is found in the specified part of the array, then "first" is the index of the first found element, "last" is the index of the last found element.
     * If key is not found in the specified part of the array, then "first" should be -1, and "last" should be the first element in interval [fromIndex, toIndex-1] that is greater than key.
     * If all elements in the interval are less than key, return [-1, toIndex].
     */
    public static int[] rangeSearch( int[] array, int fromIndex, int toIndex, int key ) {
        int lo = fromIndex;
        int hi = toIndex-1;

        int left = toIndex;
        // find the first element
        while( lo <= hi ) {
            int mid = lo + (hi-lo)/2;
            if( array[mid] < key )
                lo = mid + 1;
            else if( array[mid] == key ) {
                if( mid == fromIndex || array[mid-1] < key) {
                    left = mid;
                    break;
                }
                hi = mid - 1;
            } else
                hi = mid - 1;
        }
        if( lo > hi )
            return new int[]{-1, lo};
        lo = left;
        hi = toIndex-1;
        while( lo <= hi ) {
            int mid = lo + (hi-lo)/2;
            if( array[mid] > key )
                hi = mid-1;
            else {
                if( mid == toIndex-1 || array[mid+1] > key)
                    return new int[]{left, mid};
                lo = mid+1;
            }
        }
        return new int[]{left, toIndex-1};
    }
}
