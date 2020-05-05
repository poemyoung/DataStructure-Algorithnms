package sort;

import java.util.Arrays;

/**
 * 快速排序
 */
public class QuickSort {
    /**
     * 递归进行排序
     * @param l 数组最左边
     * @param r 数组最右边
     */
    public void sort(int[] arr,int l,int r){
        //基准条件
        if(r - l == 1){
            return;
        }
        //记录左右边界
        int i = l;
        int j = r;
        int pos = (l+r)/2;
        int cmpPoint = arr[pos];
        while (i <= j){
            //找到比支点大的数
            while (arr[i] < cmpPoint){
                i++;
            }
            //找到比支点小的数
            while (arr[j] > cmpPoint){
                j--;
            }
            //i位置比支点大，j位置比支点小，如果i在左j在右,交换
            if(i <=j){
                int tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;
                //交换完毕继续搜索下一个
                i++;
                j--;
            }
        }
        //对左边和右边进行该操作
        if(j > l){
            sort(arr,l,j);
        }
        if(i < r){
            sort(arr,i,r);
        }
    }
    public static void main(String[] args) {
      int[] array = {4,5,3,2,1,4,0,9};
      QuickSort test = new QuickSort();
      test.sort(array,0,7);
      System.out.println(Arrays.toString(array));
    }
}
