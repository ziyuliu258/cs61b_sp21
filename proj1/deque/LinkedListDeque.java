package deque;

public class LinkedListDeque <T> {
    private DNode<T> sentinel;
    private DNode<T> last;
    public int Lsize;
    public LinkedListDeque(){
        Lsize=0;
        sentinel=new DNode<>(null,null,null);
        last=new DNode<>(null,null,null);
        sentinel.next=sentinel;
        sentinel.prev=sentinel;
        last=sentinel;
    }
    public int size(){
        return Lsize;
    }

    /*There might be something wrong with add methods,but I currently can't figure it out.*/
    public void addFirst(T elem){
        DNode<T> newnode=new DNode<>(elem,sentinel,sentinel.next);
        //if size()==0, newnode.prev=sentinel=null,newnode.next=sentinel
        if(size()==0) {
            last=newnode;
            last.next=sentinel;
            sentinel.prev=newnode;
            sentinel.next=newnode;//these 2 lines deal with the problem of sentinel node
        }
        else{
            sentinel.next.prev=newnode;
            sentinel.next=newnode;
        }
        Lsize++;
    }
    public void addLast(T elem){
        DNode<T> newnode=new DNode<>(elem,last,sentinel);
        if(size()==0){
            sentinel.prev=newnode;

        }
        Lsize++;
    }

    public T removeLast(){
        if(size()==0)
            return null;
        DNode<T> tmp=last;
        last=last.prev;
        last.next=sentinel;
        sentinel.prev=last;//that is an easy-to-forget point!
        Lsize--;
        return tmp.data;
    }
    public T removeFirst(){
        if(size()==0){
            return null;
        }
        else{
            DNode<T> tmp=sentinel.next;
            sentinel.next=sentinel.next.next;
            return tmp.data;
        }
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


}
class DNode<T> {
    T data;
    DNode<T> next;
    DNode<T> prev;
    public DNode(T elem,DNode<T> P,DNode<T> N){
        data=elem;
        prev=P;
        next=N;
    }
}//HELPER CLASS REQUIRED
