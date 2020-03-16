package sort;

/**
 * @description: 冒泡排序算法，时间复杂度为O(n^2),空间复杂度为O(1)
 */
public class BubbleSort {
    public static void main(String[] args) {
       //测试
        int[] test = {3,2,4,1,5};
        BubbleSort ab = new BubbleSort(test);
        int[] res = ab.sort();
        System.out.println(res[0]+" "+res[1]+" "+res[2]+" "+res[3]+" "+res[4]);
    }
    //待排序的数组
    public int[] arr;

    public BubbleSort(int[] UnSorted){
        arr = UnSorted;
    }
    //排序方法
    public int[] sort(){
        int n = arr.length;
        //判断是否排序完毕，true表示已排完
        boolean flag = true;
        //外层循环表示遍历次数
        for(int i = 0;i < n-1;i ++){
            flag = true;
            //内层循环表示单个数组值判断
            for(int j = 0;j < n-1;j++){
                if(arr[j]>arr[j+1]){
                    //交换两个数的值
                    int temp = arr[j+1];
                    arr[j+1] = arr[j];
                    arr[j] = temp;
                    //交换完成表示排序，循环继续
                    flag = false;
                }
            }
            if(flag){
                break;
            }
        }
        return arr;
    }
}
