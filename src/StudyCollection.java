import java.util.*;

class StudyCollection{
    public static void main(String[] args) {
        int[] a = {1,2,3,4,5};
        int[] b = Arrays.copyOfRange(a,0,3);
        System.out.println(Arrays.toString(b));
        b[0] = 10;
        System.out.println(Arrays.toString(a));

    }
}