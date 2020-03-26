package sort;

import java.util.Arrays;

/**
 * 插入排序算法，把待排数字前面序列看作已排序列
 * 时间复杂度o(n*n)，适用于小范围的排序
 */
public class InsertSort {
    //待排数组
    private int[] unSort;

    public InsertSort(int[] unSort) {
        this.unSort = unSort;
    }
    //排序算法
    public int[] sort(){
        int n = unSort.length;
        //外层循环i，用来逐个遍历数组元素进行插入
        for (int i = 1;i < n;i++){
            //内层循环j，逐个遍历已排数组，进行插入
            for (int j = 0;j < i;j++){
                //当找到比待插元素大的元素时，插入该元素前面
                if(unSort[j]>unSort[i]){
                    int temp = unSort[i];
                    //所有元素向后移动一位,反向移
                    for(int k = i-1;k >= j;k--){
                        unSort[k+1] = unSort[k];
                    }
                    //将temp插入
                    unSort[j] = temp;
                    //插入成功开始检测下一个元素
                    break;
                }
            }
        }
        return unSort;
    }
    //测试函数
    public static void main(String[] args) {
        int[] a = {5,4,3,2,1};
        int[] b = {10,26,39,63,2,3,1,98};
        int[] c = {1,2,3,4,5,6,7,8};
        InsertSort test = new InsertSort(a);
        System.out.println(Arrays.toString(test.sort()));
        InsertSort test1 = new InsertSort(b);
        System.out.println(Arrays.toString(test1.sort()));
        InsertSort test2 = new InsertSort(c);
        System.out.println(Arrays.toString(test2.sort()));
    }
}
