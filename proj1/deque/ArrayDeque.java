package deque;

/*
* addLast,addFirst,isEmpty,size,printDeque,removeFirst,removelast
* get(index)*/

/*For arrays of length 16 or more, your usage factor should always be at least 25%.
This means that before performing a remove operation that
will bring the number of elements in the array under 25% the length of the array,
you should resize the size of the array down.
For smaller arrays, your usage factor can be arbitrarily low.*/

public class ArrayDeque<T>{
    private T[] elems;
    private int front;
    private int rear;
    public ArrayDeque(){
        front=0;
        rear=-1;
        elems=(T[]) new Object[8];
        /*can't directly create a generic array,
        * so make an Object one and convert it to type T*/
    }
    public int size(){
        return rear-front+1;
    }
    public boolean isEmpty(){
        return (size()==0);
    }
    public void addLast(T item){
        elems[(rear=++rear%size())]=item;
    }
    public void addFirst(T item){
        elems[(--front<0?front+size():front)]=item;
    }
    public T removeFirst(){
        if(isEmpty())
            return null;
        T tmp=elems[front];
        elems[front]=null;
        front=(front+1)%size();
        return tmp;
    }
    public T removeLast(){
        if(isEmpty())
            return null;
        T tmp=elems[rear];
        elems[rear]=null;
        rear=(rear-1)%size();
        return tmp;
    }
    public T getFirst(){
        if(isEmpty())
            return null;
        else
            return elems[front];
    }
    public T getLast(){
        if(isEmpty())
            return null;
        else
            return elems[rear];
    }
    public void printDeque(){
        for(T i=elems[front];i!=null;i=elems[(front+1)%size()]){
            System.out.println(i.toString());
        }
    }
    public T get(int index){
        if(index>size())
            return null;
        else{
            return elems[(front+index)%size()];
        }
    }
}
