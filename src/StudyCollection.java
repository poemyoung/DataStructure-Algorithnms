import org.w3c.dom.stylesheets.LinkStyle;

import java.util.*;

public class StudyCollection {
    int modCount = 0;
   public class test{
       int expectedModCount = modCount;
       public boolean jud(){
           if(expectedModCount == modCount){
               return true;
           }else {
               return false;
           }
       }
   }
    public test getInstance(){
        return new test();
    }
   public void add(){
       modCount++;
   }

    public static void main(String[] args) {
       StudyCollection study = new StudyCollection();
       StudyCollection.test test = study.getInstance();
       study.add();
        System.out.println(test.jud());
        ArrayList<Integer> a = new ArrayList<>();
        a.add(1);
        Iterator<Integer> it = a.iterator();
        while (it.hasNext()) {
            it.remove();
            System.out.println(it.next());
        }
    }
}
