package deque;

import com.github.weisj.jsvg.E;

import java.util.Deque;
import java.util.Iterator;

/**Currently,this is the two-sentinel approach,mixing the two approaches up.
 * maybe sometimes I will use the circular approach.
 *
 */


public class LinkedListDeque<T>{
    private DNode<T> sentinel;
    private DNode<T> last;
    public int Lsize;
    public LinkedListDeque(){
        Lsize=0;
        sentinel=new DNode<>(null,null,null);
        last=sentinel;
        sentinel.next=last;
        sentinel.prev=last;

        /*revised the method. sentinel and last might be two separate node,
        which is better than two pointing to the same address.*/
    }
    public int size(){
        return Lsize;
    }
    /*I think this time maybe I will solve it.*/
    public void addFirst(T elem){
        DNode<T> newnode=new DNode<>(elem,sentinel,sentinel.next);
        if(size()==0){
            sentinel.next=newnode;
            last.prev=newnode;
            Lsize++;
        }
        else {
            sentinel.next.prev = newnode;
            sentinel.next = newnode;
            Lsize++;
        }
    }
    public void addLast(T elem){
        DNode<T> newnode=new DNode<>(elem,last.prev,last);
        if(size()==0){
            last.prev=newnode;
            sentinel.next=newnode;
            Lsize++;
        }
        else {
            last.prev.next = newnode;
            last.prev = newnode;
            Lsize++;
        }
    }

    public T removeLast(){
        if(size()==0)
            return null;

        /*DNode<T> tmp=last.prev;
        last.prev=last.prev.prev;
        Lsize--;
        return tmp.data;*/
        /* that may be wrong as java may automatically recycle the value of tmp when line
        * "last.prev=last.prev.prev" is executed.
        * So try removing the tmp form.*/

        T rtvalue=last.prev.data;
        if(size()==1){
            last.prev=sentinel;
            sentinel.next=last;
        }
        else {
            last.prev = last.prev.prev;
            last.prev.next = last;
        }//be cautious
        Lsize--;
        return rtvalue;
    }
    public T removeFirst(){
        if(size()==0){
            return null;
        }
        T rtValue=sentinel.next.data;
        if(size()==1){
            sentinel.next=last;
            last.prev=sentinel;
        }
        else {
            sentinel.next = sentinel.next.next;
            sentinel.next.prev = sentinel;
        }//be cautious
        //trash the variable tmp
        Lsize--;//last version didn't include this
        return rtValue;
    }

    public boolean isEmpty(){
        return (size()==0);//or (size()==0?true:false); a more complex version.
    }

    public void printDeque(){
        if(size()==0)
            return;
        for(DNode<T> i=sentinel.next;i!=sentinel;i=i.next){
            System.out.println(i.data.toString());
        }
    }

    public T get(int index){
        if(index<0||index>=size())
            return null;
        DNode<T> tmp=sentinel.next;
        for(int i=0;i<index;i++){
            tmp=tmp.next;
        }
        return tmp.data;
    }
    /*optional work.finish it someday.
    public Iterator<T> iterator(){}
    public boolean equals(Object o){}
    */

    //test module
    public static void main(String[] args) {
        LinkedListDeque<Integer> tlist = new LinkedListDeque<>();
        for (int i = 0; i < 8; i++) {
            tlist.addLast(i);
        }
        for (double i = 0; i < 4; i++) {
            System.out.print(i + " ");
            System.out.println((double) tlist.removeFirst());
        }
        for (double i = 7; i > 4; i--) {
            System.out.print(i + " ");
            System.out.println((double) tlist.removeLast());
        }
    }

    public T getRecursive(int index){//using recursion but not iteration
        if(index<0||index>=size())
            return null;
        else{
            int count=0;
            DNode<T> tmp=sentinel.next;
            return recurseHelper(tmp,count,index);
        }
    }
    private T recurseHelper(DNode<T> node,int current,int target){
        if(current==target)
            return node.data;
        else{
            return recurseHelper(node.next,current+1,target);
        }
    }


    private static class DNode<T> {//add that DNode is static and private
        T data;
        DNode<T> next;
        DNode<T> prev;
        public DNode(T elem,DNode<T> P,DNode<T> N){
            data=elem;
            prev=P;
            next=N;
        }
    }//HELPER CLASS REQUIRED
    //Put it inside the big class making the class file more integral
}


