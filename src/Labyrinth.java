import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;


import static java.util.concurrent.TimeUnit.MILLISECONDS;

public class Labyrinth {
    public String[][] laby;
    public Stack stack = new Stack(10);
    public Coordinate entry;
    public Coordinate exit;
    public Coordinate current;
    public Labyrinth() throws IOException {
        //this.createLaby();
        this.readLaby();
        this.current = entry;
        this.stack.push(entry);
    }

    public void readLaby() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("file3.txt"));
        String line = br.readLine();

        try {
            StringBuilder sb = new StringBuilder();
            int nCol = 0;
            while (true) {
                if(line == null){
                    break;
                }
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();

                if(line != null){
                    nCol = line.split("").length;
                }
            }

            String all = sb.toString();
            int nRow = all.split("\n").length;
            String[][] labyAux= new String[nRow][nCol];

            System.out.println(nRow);
            System.out.println(nCol);

            String[] lines = all.split(System.lineSeparator());

            for (int i = 0; i < lines.length; i++) {
                String[] items = lines[i].split("");
                for (int j = 0; j < items.length; j++) {
                    labyAux[i][j] = items[j];
                }
            }

            this.laby = labyAux;

            for (int i = 0; i < this.laby.length; i++) {
                for (int j = 0; j < this.laby[i].length; j++) {
                    if(Objects.equals(this.laby[i][j], "E")){
                        entry = new Coordinate(i, j);
                    }
                    if(Objects.equals(this.laby[i][j], "S")){
                        exit = new Coordinate(i, j);
                    }
                }
            }

        } finally {
            br.close();
        }
    }

    public void createLaby(){
        this.laby = new String[11][11];
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

        for (int i = 0; i < this.laby.length; i++) {
            for (int j = 0; j < this.laby.length; j++) {
                if(this.laby[i][j] == null){
                    this.laby[i][j] = " ";
                }
            }
        }
    }

    public void printLaby(){
        for (int i = 0; i < this.laby.length; i++) {
            for (int j = 0; j < this.laby[i].length; j++) {
                if(this.laby[i][j] == null){
                    //System.out.print(" ");
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
            MILLISECONDS.sleep(250);

            //System.out.println(this.stack.peek().stringCoordinate() + " == " + this.exit.stringCoordinate());
        }

        this.stack.printStack();
        System.out.println("GG!");
    }

    public void move(){
        Coordinate up       = new Coordinate(this.current.x - 1, this.current.y);
        Coordinate down     = new Coordinate(this.current.x + 1, this.current.y);
        Coordinate left     = new Coordinate(this.current.x, this.current.y - 1);
        Coordinate right    = new Coordinate(this.current.x, this.current.y + 1);

        if(Objects.equals(this.laby[up.x][up.y], " ") || this.laby[up.x][up.y].equals("S")){
            this.laby[up.x][up.y] = "-";
            this.current = up;
            this.stack.push(up);
            System.out.println("Go UP!");
        }else if(Objects.equals(this.laby[down.x][down.y], " ") || this.laby[down.x][down.y].equals("S")){
            this.laby[down.x][down.y] = "-";
            this.current = down;
            this.stack.push(down);
            System.out.println("Go DOWN!");
        }else if(Objects.equals(this.laby[left.x][left.y], " ") || this.laby[left.x][left.y].equals("S")){
            this.laby[left.x][left.y] = "-";
            this.current = left;
            this.stack.push(left);
            System.out.println("Go LEFT!");
        }else if(Objects.equals(this.laby[right.x][right.y], " ") || this.laby[right.x][right.y].equals("S")){
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
    }

    public boolean gg(Coordinate cord){
        if(cord.x == this.exit.x && cord.y == exit.y){
            return true;
        }else{
            return false;
        }
    }
}
