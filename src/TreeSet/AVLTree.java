package TreeSet;

/**
 * AVL树的一些方法的实现
 */
public class AVLTree<T extends Comparable<? super T>>{
    /**
     * 树节点类
     */
    private class AvlNode<T extends Comparable<? super T>>{
        int height;
        AvlNode<T> left;
        AvlNode<T> right;
        T value;

        public AvlNode(AvlNode<T> left, AvlNode<T> right, T value) {
            this.left = left;
            this.right = right;
            this.value = value;
           this.height = 0;
        }
    }
    //域,根的高度为-1
    AvlNode<T> root;
    /**
     * 构造方法
     */
    public AVLTree() {

    }
    /**
     * 求高度方法
     */
    public int height(AvlNode<T> a){
        return a==null? -1 : a.height;
    }
    /**
     * 插入算法
     */
    public boolean insert(T v){
        if(root == null){
            root = new AvlNode<T>(null,null,v);
            root.height = -1;
            return true;
        }

        root= insert(v, root) ;
        return root!=null;
    }
    private AvlNode<T> insert(T v,AvlNode<T> top){
        //基准条件
        if(top == null){
            return new AvlNode<>(null,null,v);
        }
        //递归情况
        if(v.compareTo(top.value) > 0){
            top.right = insert(v,top.right);
            //检查高度
            if(height(top.right) - height(top.left) >= 2){
                if(v.compareTo(top.right.value) > 0){
                    top = rotateRightRight(top);
                }else {
                    top = rotateRightLeft(top);
                }
            }
        }else if (v.compareTo(top.value) < 0){
            top.left = insert(v,top.left);
            if(height(top.left) - height(top.right) == 2){
                if(v.compareTo(top.left.value) > 0){
                    top = rotateLeftRight(top);
                }else {
                    top = rotateLeftLeft(top);
                }
            }
        }else {

        }
        top.height = Math.max(height(top.left),height(top.right)) + 1;
        return top;
    }
    /**
     * 旋转：右右单旋转
     */
    private AvlNode<T> rotateRightRight(AvlNode<T> center){
        AvlNode<T> temp  = center.right;
        center.right = temp.left;
        temp.left = center;
        //重新计算高度
        center.height = Math.max(height(center.right),height(center.left))+1;
        temp.height  = Math.max(height(temp.right),height(temp.left))+1;
        return temp;
    }
    /**
     * 旋转：左左单旋转
     */
    private AvlNode<T> rotateLeftLeft(AvlNode<T> center){
        AvlNode<T>temp = center.left;
        center.left = temp.right;
        temp.right = center;
        center.height = Math.max(height(center.right),height(center.left))+1;
        temp.height  = Math.max(height(temp.right),height(temp.left))+1;
        return temp;

    }
    /**
     * 旋转：右左双旋转
     */
    private AvlNode<T> rotateRightLeft(AvlNode<T> center){
       center.right = rotateLeftLeft(center.right);
        return rotateRightRight(center);
    }
    /**
     * 旋转：左右双旋转
     */
    private AvlNode<T> rotateLeftRight(AvlNode<T> center){
        center.left = rotateRightRight(center.left);
        return rotateLeftLeft(center);
    }
    /**
     * 打印例程
     */
    public void printTree(){
        if(root == null){
            return;
        }else {
            printTree(root,0);
        }
    }
    private void printTree(AvlNode<T> top,int height){
        //基准跳件
        if(top == null){
            return;
        }
        //递归条件
        if(top.right!=null){
            printTree(top.right,height + 1);
        }
        for (int i = 0;i < height;i++){
            System.out.print("  ");
        }
        System.out.println(top.value);
    if(top.left !=null) {
        printTree(top.left, height + 1);
    }
    }
    /**
     * 测试AvL树
     */
    public static void main(String[] args) {
        JayChouSongs jay10 = new JayChouSongs(10,"回到过去");
        JayChouSongs jay5 = new JayChouSongs(5,"回到过去");
        JayChouSongs jay20 = new JayChouSongs(20,"回到过去");
        JayChouSongs jay30 = new JayChouSongs(30,"回到过去");
        JayChouSongs jay40 = new JayChouSongs(40,"回到过去");
        JayChouSongs jay50 = new JayChouSongs(50,"回到过去");
        AVLTree<JayChouSongs>test = new AVLTree<>();
        test.insert(jay20);
        test.insert(jay5);
        test.insert(jay10);
        test.insert(jay40);
        test.insert(jay30);
        test.insert(jay50);
        test.printTree();
    }
}
