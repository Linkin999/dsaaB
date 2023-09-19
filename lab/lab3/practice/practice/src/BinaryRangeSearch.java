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
    public static int[] rangeSearch( int[] array, int fromIndex, int toIndex, int key )
    {
        // Write your code here.
        // the first step is to confirm whether
        /*if (fromIndex<toIndex){
            int count=0;
            int count1=0;
            int count2=0;
            int last=0;
            for(int i=fromIndex;i<toIndex;i++){
                if(array[i]==key){ //key is found in the specified part of array
                    count=1;
                    break;
                }
                else if (array[i]>key){
                    count1=1; // there  exists values that are greater than key
                }
                else
                    count2=1; // there don't exist values that are greater than key
            }
            if (count==1){
                return new int[]{fromIndex,toIndex-1};//有困惑
            }
            else if(count!=1 && count1==1){ //key is not found in the specified part of array and there  exists values that are greater than key
                for(int i=fromIndex;i<toIndex;i++){
                    if (array[i]>=key){
                        last=i;
                        break;
                    }
                }
                return new int[]{-1,last};
            }
            else {
                return new int[]{-1,toIndex};
            }
        }
        else
            return new int[]{-1,toIndex};*/


        if (fromIndex<toIndex){
            boolean haskey = false;
            int index=-1;
            for (int i=fromIndex;i<toIndex;i++){
                if (array[i]==key){
                    haskey=true;
                    break;
                }
                if (array[i]>=key&&index==-1){
                    index=i;
                }
            }
            if (haskey){
                return new int[]{fromIndex,toIndex-1};
            }else if (index!=-1){
                return new int[]{-1,index};
            }
            else
                return new int[]{-1,toIndex};
        }
        else
            return new int[]{-1,toIndex};




    }
}
