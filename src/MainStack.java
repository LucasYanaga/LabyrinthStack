public class MainStack {

    public static void main(String[] args) {
        Stack stack = new Stack(5);

        stack.push(new Coordinate(1,1));
        stack.push(new Coordinate(22,22));
        stack.push(new Coordinate(333,333));

        stack.printStack();

        stack.push(new Coordinate(4444,4444));
        stack.push(new Coordinate(333,333));

        stack.printStack();

        System.out.println("isFull? " + stack.isFull());
        System.out.println("isEmpty? " + stack.isEmpty());
        System.out.println("Peek? " + stack.peek().stringCoordinate());
        //Stack increase
        stack.push(new Coordinate(55555,55555));
        stack.push(new Coordinate(1,1));

        stack.printStack();

        System.out.println("isFull? " + stack.isFull());
        System.out.println("isEmpty? " + stack.isEmpty());
        System.out.println("Peek? " + stack.peek().stringCoordinate());

        stack.pop();
        stack.pop();

        stack.printStack();

        System.out.println("isFull? " + stack.isFull());
        System.out.println("isEmpty? " + stack.isEmpty());
        System.out.println("Peek? " + stack.peek().stringCoordinate());
    }
}