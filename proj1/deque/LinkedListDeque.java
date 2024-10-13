package deque;

/**Currently,this is the two-sentinel approach,mixing the two approaches up.
 * maybe sometimes I will use the circular approach.
 *
 */


public class LinkedListDeque <T> {
    private DNode<T> sentinel;
    private DNode<T> last;
    public int Lsize;
    public LinkedListDeque(){
        Lsize=0;
        sentinel=new DNode<>(null,null,null);
        last=new DNode<>(null,null,null);
        sentinel.next=last;
        sentinel.prev=last;
        last.next=sentinel;
        last.prev=sentinel;
        /*revised the method. sentinel and last might be two separate node,
        which is better than two pointing to the same address.*/
    }
    public int size(){
        return Lsize;
    }

    /*I think this time maybe I will solve it.*/
    public void addFirst(T elem){
        DNode<T> newnode=new DNode<>(elem,sentinel,sentinel.next);
        sentinel.next.prev=newnode;
        sentinel.next=newnode;
        Lsize++;
    }
    public void addLast(T elem){
        DNode<T> newnode=new DNode<>(elem,last.prev,last);
        last.prev.next=newnode;
        last.prev=newnode;
        Lsize++;
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
        last.prev=last.prev.prev;
        last.prev.next=last;//be cautious
        Lsize--;
        /*
        The Key is that if size==(1) it will cause a fault as the line
        * "last.prev.next=last" might make a null pointer point at another node.
        * */

        //so im gonna add a judging branch to fix it.

        //lines above are changes
        return rtvalue;
    }
    public T removeFirst(){
        if(size()==0){
            return null;
        }
        T rtValue=sentinel.next.data;

        sentinel.next=sentinel.next.next;
        sentinel.next.prev=sentinel;//be cautious
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
        for(DNode<T> i=sentinel.next;i!=last;i=i.next){
            System.out.println(i.data.toString());
        }
    }

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


