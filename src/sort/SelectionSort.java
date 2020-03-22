package sort;

import java.sql.SQLSyntaxErrorException;
import java.util.Arrays;

/**
 * 选择排序算法
 */
public class SelectionSort {
    private int[] unSort;

    public SelectionSort(int[] unSort){
        this.unSort = unSort;
    }
    /**
     * 排序算法核心
     */
    public int[] sort(){
        int n = unSort.length;
        //外层循环，检查n-1次
        for(int i = 0;i < n-1;i++){
            //最大值暂时赋值为待交换的值，即n-i-1(下标）
            int max = unSort[n-i-1];
            //记录待交换元素的数组下标，即n-i-1
            int pos = n-i-1;
            //每次排好一个，每次循环少一个元素,每次只走到n-i-2
            for(int j = 0;j < n-i-1;j++){
                if(unSort[j]>max){
                    //记录下标
                    pos = j;
                    //更新max
                    max = unSort[j];
                }
            }
            //最大的元素已在最后
            if(pos == n-i-1){
                continue;
            }
            //循环完成，开始交换数组元素,最后一位为n-i-1（下标）
            int temp = unSort[pos]; //最大
            //i = 0跟下标n-1换，第i次和n-i-1换
            unSort[pos] = unSort[n-i-1];
            unSort[n-i-1] = temp;
        }
        return unSort;
    }
    /**
     * test
     */
    public static void main(String[] args) {
        int[] a = {1,2,3,4,5};
        int[] b = {2,5,8,1,0};
        int[] c = {38,7,6,5,4,3,2,1,0};
        new SelectionSort(b).sort();
        System.out.print(Arrays.toString(new SelectionSort(a).sort()));
        System.out.print(Arrays.toString(new SelectionSort(b).sort()));
        System.out.print(Arrays.toString(new SelectionSort(c).sort()));
    }
}
