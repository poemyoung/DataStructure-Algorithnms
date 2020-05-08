package sort;

import java.util.Arrays;

/**
 * 归并排序
 */
public class MergeSort {
    public void sort(int[] array){
        mergeSort(array,0,array.length-1);
    }
    /**
     * 分
     * @param array 待排序数组
     * @param L 最左元素
     * @param R 最右元素
     */
    public void mergeSort(int[] array,int L,int R){
        //基准条件
        //一个元素必定是已经排好序的，可以直接合并
        if(R - L == 1){
            return;
        }
        //将数组拆成排好序的两部分
        int m = (L+R)/2;
        //左边排序
        mergeSort(array,L,m);
        //右边排序
        mergeSort(array,m,R);
        //合并
        merge(array,L,m,R);
    }

    /**
     * 治
     * @param array 待排数组
     * @param L 最左元素
     * @param M 分隔位置
     * @param R 最右元素
     */
    private void merge(int[] array,int L, int M,int R){
        //新建左边元素的拷贝
        int[] lCopy = Arrays.copyOfRange(array,L,M);
        //新建右边元素的拷贝
        int[] rCopy = Arrays.copyOfRange(array,M,R);
        //遍历合并
        //遍历起始位置为L,结束位置为R
        int l = 0;
        int r = 0;
        //原数组待插入位置
        int k  =L;
        //只要有一个指针还未走到边界
        while (l!=M-L||r!=R-M){
            //某个指针走出了边界
            if(l == M-L){
                for (;r<R-M;r++){
                    array[k++] = rCopy[r];
                }
            }else if(r == R-M){
                for (;l<M-L;l++){
                    array[k++] = lCopy[l];
                }
            } else if(rCopy[r] >= lCopy[l]){
                //都未走出边界
                //右边比左边大,把左边放进去
                array[k++] = lCopy[l++];
            }else if(rCopy[r] < lCopy[l]){
                //左边比右边大
                array[k++] = rCopy[r++];
            }
        }
    }

    public static void main(String[] args) {
        int[] array = {4,5,3,2,1,4,0,9};
        MergeSort test = new MergeSort();
        test.sort(array);
        System.out.println(Arrays.toString(array));
    }
}
