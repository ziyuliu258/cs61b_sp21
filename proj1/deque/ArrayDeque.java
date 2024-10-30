package deque;

/*
* addLast,addFirst,isEmpty,size,printDeque,removeFirst,removeLast
* get(index)*/

/*For arrays of length 16 or more, your usage factor should always be at least 25%.
This means that before performing a remove operation that
will bring the number of elements in the array under 25% the length of the array,
you should resize the size of the array down.
For smaller arrays, your usage factor can be arbitrarily low.*/

//when realising the circular way, %elems.length instead of %size()!!!

public class ArrayDeque<T>{
    private T[] elems;
    private int front;
    private int rear;
    private int size;

    public ArrayDeque(){
        front=0;
        rear=0;
        size=0;
        elems=(T[]) new Object[8];
        /*can't directly create a generic array,
        * so make an Object one and convert it to type T*/
        /*don't have to make rear plus -1 as it will make it difficult to first addfirst*/
    }
    public int size(){
        return size;
    }
    public boolean isEmpty(){
        return (size()==0);
    }
    public void addLast(T item){
        if(size()==0){
            elems[rear]=item;
            size++;
        }
        else {
            resize();
            elems[(rear = ++rear % elems.length)] = item;
            size++;
        }
    }
    public void addFirst(T item){
        if(size()==0){
            elems[front]=item;
            size++;
        }
        else {
            resize();
            elems[(--front < 0 ? front + elems.length : front)] = item;
            front=front<0?front+elems.length:front;
            size++;
        }
    }
    public T removeFirst(){
        if(isEmpty())
            return null;
        T tmp=elems[front];
        elems[front]=null;
        if(size()>1){
            front=(front+1)% elems.length;
        }
        size--;
        resize();
        return tmp;
    }
    public T removeLast(){
        if(isEmpty())
            return null;
        T tmp=elems[rear];
        elems[rear]=null;
        if(size()>1)
            rear=(--rear+ elems.length)% elems.length;
        size--;
        resize();
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
        for(int index=front;index!=(rear+1+ elems.length)% elems.length;index=(++index+ elems.length)% elems.length){
            System.out.println(elems[index].toString());
        }
    }
    public T get(int index){
        if(index>size())
            return null;
        else{
            return elems[(front+index)% elems.length];
        }
    }
    private void resize(){
        if(elems.length>=16&&size()*4<elems.length){
            T[] newElems=(T[])new Object[size()*2];
            for(int i=0;i<size();i++){
                newElems[i]=get(i);
            }
            elems=newElems;
            front=0;
            rear= (front+size()-1+ elems.length)% elems.length;
        }
        if(size()== elems.length){
            T[] newElems=(T[])new Object[size()*2];
            for(int i=0;i<size();i++){
                newElems[i]=get(i);
            }
            elems=newElems;
            front=0;
            rear= (front+size()-1+ elems.length)% elems.length;
        }
    }
}
