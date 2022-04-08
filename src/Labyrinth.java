import static java.util.concurrent.TimeUnit.MILLISECONDS;

public class Labyrinth {
    public String[][] laby = new String[11][11];
    public Stack stack = new Stack(10);
    public Coordinate entry = new Coordinate(1, 1);
    public Coordinate exit = new Coordinate(1, 3);
    public Coordinate current;

    public Labyrinth() {
        this.current = entry;
        this.createLaby();
        this.stack.push(entry);
    }

    public void createLaby(){
        for (int i = 0; i < this.laby.length; i++) {
            for (int j = 0; j < this.laby.length; j++) {

                //Edges
                if(i == 0 || i == 10 || j == 0 || j == 10){
                    this.laby[i][j] = "#";
                }

                //Drawing...
                if(i == 8 && j >= 2  && j <= 8){
                    this.laby[i][j] = "#";
                }

                if(j == 2 && i >= 1  && i <= 8 && i != 3){
                    this.laby[i][j] = "#";
                }

                if(i == 6 && j != 1  && j != 3 && j != 9){
                    this.laby[i][j] = "#";
                }

                if(i == 4 && j != 1  && j != 5 && j != 9){
                    this.laby[i][j] = "#";
                }

                if(i == 2 && j != 1  && j != 5 && j != 7 && j != 9){
                    this.laby[i][j] = "#";
                }

                if(i == 3 && j == 3 || i == 3 && j == 6){
                    this.laby[i][j] = "#";
                }

                if(j == 8 && i != 3 && i != 7 && i != 9){
                    this.laby[i][j] = "#";
                }

                if(i == 1 && j == 6){
                    this.laby[i][j] = "#";
                }
            }
        }

        //Entrada e Saída
        this.laby[1][1] = "E";
        this.laby[1][3] = "S";
    }

    public void printLaby(){
        for (int i = 0; i < this.laby.length; i++) {
            for (int j = 0; j < this.laby.length; j++) {
                if(this.laby[i][j] == null){
                    System.out.print(" ");
                }else {
                    System.out.print(this.laby[i][j]);
                }

                //Space between columns
                System.out.print(" ");
            }

            //Space between lines
            System.out.println();
        }
    }

    public void solve() throws InterruptedException {
        while(!this.gg(this.current)){
            move();
            this.printLaby();
            MILLISECONDS.sleep(1000);

            System.out.println(this.stack.peek().stringCoordinate() + " == " + this.exit.stringCoordinate());
        }

        System.out.println("GG!");
        this.stack.printStack();
    }

    public void move(){
        Coordinate up       = new Coordinate(this.current.x - 1, this.current.y);
        Coordinate down     = new Coordinate(this.current.x + 1, this.current.y);
        Coordinate left     = new Coordinate(this.current.x, this.current.y - 1);
        Coordinate right    = new Coordinate(this.current.x, this.current.y + 1);

        if(this.laby[up.x][up.y] == null || this.laby[up.x][up.y].equals("S")){
            this.laby[up.x][up.y] = "-";
            this.current = up;
            this.stack.push(up);
            System.out.println("Go UP!");
        }else if(this.laby[down.x][down.y] == null || this.laby[down.x][down.y].equals("S")){
            this.laby[down.x][down.y] = "-";
            this.current = down;
            this.stack.push(down);
            System.out.println("Go DOWN!");
        }else if(this.laby[left.x][left.y] == null || this.laby[left.x][left.y].equals("S")){
            this.laby[left.x][left.y] = "-";
            this.current = left;
            this.stack.push(left);
            System.out.println("Go LEFT!");
        }else if(this.laby[right.x][right.y] == null || this.laby[right.x][right.y].equals("S")){
            this.laby[right.x][right.y] = "-";
            this.current = right;
            this.stack.push(right);
            System.out.println("Go RIGHT!");
        }else{
            this.laby[this.current.x][this.current.y] = "X";
            this.stack.pop();
            this.current = this.stack.peek();
            System.out.println("DEAD END!");
            System.out.println("Back to " + this.stack.peek().stringCoordinate());
        }

        //this.laby[current.x][current.y] = "@";
    }

    public boolean gg(Coordinate cord){
        if(cord.x == this.exit.x && cord.y == exit.y){
            return true;
        }else{
            return false;
        }
    }
}
