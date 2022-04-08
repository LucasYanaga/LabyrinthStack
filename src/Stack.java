import java.lang.Math;

public class Stack{
    public Coordinate[] stack;
    public int index;
    public int max;

    public Stack(int lenght){
        this.index = -1;
        this.stack = new Coordinate[lenght];
    }

    public void push(Coordinate cord){
        System.out.println("Pushing: " + cord.stringCoordinate());

        if (isFull()) {
            this.increaseStack();
        }

        this.stack[++this.index] = cord;
    }

    public void pop(){
        if(!isEmpty()) {
            System.out.println("Poping: " + stack[this.index].stringCoordinate());
            this.stack[this.index] = null;
            this.index--;
        }
    }

    public Coordinate peek(){
        if(!isEmpty()){
            return this.stack[this.index];
        }else{
            return null;
        }
    }

    public boolean isEmpty(){
        boolean isEmpty = false;

        if(this.index == -1){
            isEmpty = true;
        }
        return isEmpty;
    }

    public boolean isFull(){
        boolean isFull = false;

        if(this.index + 1 == this.stack.length){
            isFull = true;
        }
        return isFull;
    }

    public void increaseStack(){
        Coordinate[] newStack = new Coordinate[this.stack.length + 1];

        for(int i = 0; i < this.stack.length; i++) {
            newStack[i] = this.stack[i];
        }

        this.stack = newStack;
    }

    public boolean contains(Coordinate cord){
        boolean contains = false;
        for (int i = 0; i < this.stack.length; i++) {
            if(this.stack[i] == cord){
                contains = true;
            }
        }

        return contains;
    }

    public void printStack(){
        this.max();

        for (int i = this.stack.length -1; i >= 0; i--) {
            if(this.stack[i] != null) {
                int houses = this.max - this.stack[i].stringCoordinate().length();
                System.out.print("|");
                for (int j = 0; j < houses/2; j++) {
                    System.out.print(" ");
                }
                System.out.print(this.stack[i].stringCoordinate());
                for (int j = 0; j < houses/2; j++) {
                    System.out.print(" ");
                }
                System.out.print("|");
            }else{
                System.out.print("|");
                for (int j = 0; j < this.max; j++) {
                    System.out.print(" ");
                }
                System.out.print("|");
            }
            System.out.println();
        }

        for (int i = 0; i < 2 + this.max; i++) {
            System.out.print("-");
        }

        System.out.println();
    }

    public void max(){
        this.max = 0;
        for (Coordinate j : this.stack) {
            if(j != null) {
                if (j.stringCoordinate().length() > this.max) {
                    this.max = j.stringCoordinate().length();
                }
            }
        }
    }
}