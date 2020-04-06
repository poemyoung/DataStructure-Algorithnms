package Stack;

/**
 * 改进后的栈
 */
public class StackTwo{
    /**
     * 伪双向链表类，即改用链表来表示栈
     */
    private static class StackLink{
        //链表节点
        public Object node;
        //链表指针，指向下一个
        public StackLink next;
        //链表指针，指向前一个
        public StackLink prev;
        //构造函数，构造新节点
        public StackLink(Object node){
            this.node = node;
        }
    }
    //栈底部指针，记录何时栈为空
    private StackLink bottom;
   //栈顶指针
    private StackLink top;
    //构造函数
    public StackTwo(){
        bottom =new StackLink(new Object());
        top = bottom;
    }

    /**
     * 压栈
     */
    public void push(Object o){
        //链表节点加一
        top.next = new StackLink(o);
        top.next.prev = top;
        //改变栈顶元素
        top = top.next;

    }
    /**
     * 出栈
     */
    public Object pop(){
        if(top == bottom){
            throw new StackNotPopException("堆栈为空");
        }
       //记录要返回的数据
        Object a = top.node;
        //栈顶指针退一步
        top = top.prev;
        //将下一个节点的引用清空
        top.next = null;
        return a;
    }
    /**
     * 判断是否为空
     */
    public boolean isEmpty(){
        return top == bottom;
    }
    /**
     * 返回栈顶元素
     */
    public Object peek(){
        return top.node;
    }
    /**
     * 输出堆栈中所有元素
     */
    public void traverse(){
        StackLink temp = bottom;
        while (temp.next!=null){
            temp = temp.next;
            System.out.println(temp.node.toString());
        }
    }

    public static void main(String[] args) {
        Random a = new Random(1,"Xu");
        Random b = new Random(2,"Ze");
        Random c = new Random(3,"Pen");
        Random d = new Random(4,"Hello");
        StackTwo st = new StackTwo();
        st.push(a);
        st.push(b);
        st.push(c);
        st.push(d);
        st.traverse();
    }
}
