package Heap;

import java.util.Arrays;

/**
 * 二叉堆的实现(最小堆）
 */
public class Heap {
    //维护一个数组
    int[] array;
    //当前长度
    int currentSize = 0;
    //数组宗长度默认为40000，可以自己设置
    final int defaultLen = 20;
    //构造方法
    public  Heap(int[] arr){
        array = new int[defaultLen];
        buildHeap(arr);
    }
    public Heap(){
        array = new int[defaultLen];
    }
    public Heap(int n){
        array = new int[n];
    }

    /**
     * 新建堆
     */
    public void buildHeap(int[] arr){
        int i = 0;
        for (int num : arr){
            array[++i] = num;
            currentSize++;
        }
        //notice:叶子节点不用去遍历,只遍历叶子节点的上一层
        //最右边的叶节点对应的父节点是最后一个非叶子节点
        for (int j = currentSize/2;j>=1;j--) {
            percolateDown(j);
        }
    }
    /**
     * 插入堆中
     */
    public void insert(int a){
        //放置空穴
        int hole = currentSize+1;
        //超界扩容
        if(hole > array.length-1){
            int[] aTmp = Arrays.copyOf(array,array.length *2);
            array = aTmp;
        }
        //上滤
        for(;hole!=1;hole/=2){
            if(array[hole/2] <= a){
                break;
            }else {
                //赋值
                array[hole] = array[hole / 2];
            }
        }
        array[hole] = a;
    }
    /**
     * 下滤操作
     * @param i 从第i个元素开始下滤
     */
    private void percolateDown(int i){
        int tmp = array[i];
        int hole = i;
        //下滤
        //如果hole没有子节点，那么hole * 2一定大于currentSize
        //相反，如果hole有子节点,那么hole * 2一定小于或者等于currentSize
        int child;
       for (;hole * 2<= currentSize;hole = child){
            child  =hole * 2;
           //判断子节点哪个更小
           //如果等于currentSize则没有右子节点
           if(child!=currentSize && array[child] > array[child+1]){
               child++;
           }
           //判断最小的子节点是否比tmp值大,是的话记录该位置，下一次循环切换到该位置
           if(array[hole] > array[child]){
               array[hole] = array[child];
           }else {
               break;
           }
       }
    }
    /**
     * 删除最小元
     */
    public int deleteMin(){
        int min = 0;
        if(currentSize!=0){
            min = array[1];
            array[1] = array[currentSize--];
            percolateDown(1);
        }
        return min;
    }
    /**
     * 打印整个堆
     */
    public void printHeap(){
        System.out.println(Arrays.toString(array));
    }

    /**
     * 测试方法
     */
    public static void main(String[] args) {
        Heap test = new Heap();
        int[] a = {16,21,13,24,31,32,26,65,19,16,68};
        test.buildHeap(a);
        test.printHeap();
        test.insert(50);
        test.insert(1);
        test.printHeap();
        test.deleteMin();
        test.printHeap();
    }



}
