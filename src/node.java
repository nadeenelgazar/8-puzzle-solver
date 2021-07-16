import java.util.ArrayList;
import java.util.LinkedList;

public class node {

    //attributes
    int[][] array = new int[3][3];
    ArrayList<node> neighbours;
    node parent;
    int cost;
    int manhattanh ;
    int euclideanh;

    //constructor
    public node(int[][] array , node parent) {
        this.array = copyArray(array);
        neighbours = new ArrayList<node>();
        this.parent = parent;
        cost = 0;
    }

    //functions
    public int[][] copyArray(int[][] state) {
        int[][] copy = new int[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++)
                copy[i][j] = state[i][j];
        }
        return copy;
    }

    //search for index of 0
    public int[] search(int array[][]) {
        int i , j ;

        for (i = 0; i < 3; i++) {
            for (j = 0; j < 3; j++) {
                if (array[i][j] == 0) {
                    return new int[]{i, j};
                }

            }
        }
        return new int[]{0,0};
    }

    public void setNeighbours(node state , int [][] goal){

        int [] index = search(state.array);
        boolean up = true;
        boolean down=true;
        boolean left=true;
        boolean right=true;
        int [][] arraydown , arrayup , arrayleft , arrayright;

        if (index[0] == 0)
            up=false;
        if (index[0] == 2)
            down=false;
        if (index[1] == 0)
            left=false;
        if (index[1] == 2)
            right=false;

        //in each one we set new array , make new node ,calculate cost,calculate manhattan and eculidean values,add node to neighbours
        if (down == true) {
            arraydown = moveDown(index, state.array);
            node nodeDown = new node(arraydown,state);
            nodeDown.cost=state.cost+1;
            nodeDown.manhattanh=manhattan(nodeDown,goal)+nodeDown.cost;
            nodeDown.euclideanh=euclidean(nodeDown,goal) + nodeDown.cost;
            state.neighbours.add(nodeDown);
        }

        if (up == true) {
            arrayup = moveUp(index, state.array);
            node nodeUp = new node(arrayup,state);
            nodeUp.cost=state.cost+1;
            nodeUp.manhattanh=manhattan(nodeUp,goal)+nodeUp.cost;
            nodeUp.euclideanh=euclidean(nodeUp,goal) + nodeUp.cost;
            state.neighbours.add(nodeUp);
        }

        if (left == true) {
            arrayleft = moveLeft(index, state.array);
            node nodeLeft = new node(arrayleft,state);
            nodeLeft.cost=state.cost+1;
            nodeLeft.manhattanh=manhattan(nodeLeft,goal)+nodeLeft.cost;
            nodeLeft.euclideanh=euclidean(nodeLeft,goal) + nodeLeft.cost;
            state.neighbours.add(nodeLeft);
        }

        if (right == true) {
            arrayright = moveRight(index, state.array);
            node nodeRight = new node(arrayright,state);
            nodeRight.cost=state.cost+1;
            nodeRight.manhattanh=manhattan(nodeRight,goal)+nodeRight.cost;
            nodeRight.euclideanh=euclidean(nodeRight,goal) + nodeRight.cost;
            state.neighbours.add(nodeRight);
        }

    }

    public int [][] moveRight (int [] index , int [][] state){
        int [][] temp = copyArray(state) ;
        temp [index[0]][index[1]] = temp[index[0]][index[1]+1];
        temp[index[0]][index[1]+1]=0;
        return temp;
    }

    public int [][] moveLeft (int [] index , int [][] state){
        int [][] temp = copyArray(state);
        temp[index[0]][index[1]] = temp[index[0]][index[1]-1];
        temp[index[0]][index[1]-1]=0;
        return temp;
    }

    public int [][] moveDown (int [] index , int [][] state){
        int [][] temp = copyArray(state);
        temp [index[0]][index[1]] = temp[index[0]+1][index[1]];
        temp[index[0]+1][index[1]]=0;
        return temp;
    }

    public int [][] moveUp (int [] index , int [][] state){
        int [][] temp = copyArray(state);
        temp [index[0]][index[1]] = temp[index[0]-1][index[1]];
        temp[index[0]-1][index[1]]=0;
        return temp;
    }

    //find manhattan value for this node (h(n))
    public int manhattan (node state,int [][] goal){
        int [] index = search(state.array);
        int [] indexGoal = search(goal);

        int h = Math.abs(index[0] - indexGoal[0]) + Math.abs(index[1] - indexGoal[1]);

        return h;

    }

    //used to set manhattan for the root
    public void setManhattanh(node state,int [][] goal){
        int [] index = search(state.array);
        int [] indexGoal = search(goal);

        int h = Math.abs(index[0] - indexGoal[0]) + Math.abs(index[1] - indexGoal[1]);

        state.manhattanh=h+state.cost;
    }

    //find euclidean value for this node (h(n))
    public int euclidean (node state,int [][] goal){
        int [] index = search(state.array);
        int [] indexGoal = search(goal);

        double h = Math.sqrt(Math.pow(index[0]+indexGoal[0] , 2) + Math.pow(index[1]+indexGoal[1] , 2 ));
         return (int )h;
    }

    //used to set eculidean for the root
    public void setEuclideanh (node state,int [][] goal){
        int [] index = search(state.array);
        int [] indexGoal = search(goal);

        double h = Math.sqrt(Math.pow(index[0]+indexGoal[0] , 2) + Math.pow(index[1]+indexGoal[1] , 2 ));

        state.euclideanh=(int)h+state.cost;
    }
}
