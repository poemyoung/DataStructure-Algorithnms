package Stack;

import java.lang.reflect.Array;

/**
 * 栈的实现类,简单实现，使用int
 */
public class Stack{
    //记录栈的数组
    private int[] array;
    //数组的大小
    private int maxSize;
    //栈顶指针
    private int top;
    //带参构造函数
    public Stack(int[] array,int maxSize){
        this.array = array;
        this.maxSize = maxSize;
        this.top = 0;
    }
    //无参构造函数
    public Stack(){
        array = new int[2];
        top = 0;
        this.maxSize = 2;
    }
    /**
     * 压栈
     */
    public void push(int elem){
        if(top == maxSize-1){
            //堆栈不够大，扩容
            maxSize = maxSize * 2;
            //新堆栈
            int[] aNew = new int[maxSize];
            //转移原堆栈
            for(int j = 1;j <= top;j++){
                aNew[j] = array[j];
            }
            array = aNew;
        }
        array[++top] = elem;
    }
    /**
     * 出栈
     */
    public int pop(){
        if(isEmpty()){
            throw new StackNotPopException("堆栈为空，无法pop!");
        }
        return array[top--];
    }
    /**
     * 获取栈顶元素
     */
    public int peek(){
        return array[top];
    }
    /**
     * 判断是否为空
     */
    public boolean isEmpty(){
        if(top == 0){
            return true;
        }
        return false;
    }

    /**
     * 输出堆栈中所有元素
     */
    public void traverse(){
        for(int i = 1;i <= top;i++){
            System.out.println(array[i]);
        }
    }

    public static void main(String[] args) {
        Stack test = new Stack();
        test.push(1);
        test.push(2);
        test.push(3);
        test.push(3);
        test.push(3);
        test.traverse();
    }
}
