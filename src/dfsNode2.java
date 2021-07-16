import java.util.*;

public class dfsNode2 {
    int x= 0;
    public boolean bfsNode (int [][] initial,int [][] goal){

        Stack<node> frontier = new Stack<>();
        Set<String> visited = new HashSet<>();
        Stack<node> stackPrinting = new Stack<>();

        node root = new node(initial,null);
        node state;

        frontier.add(root);

        while (!frontier.isEmpty()){
            state=frontier.pop();
            x++;
            visited.add(Arrays.deepToString(state.array));

            if (check(state.array,goal)) {
                System.out.println("cost =" + state.cost);
                System.out.println("number of expanded nodes :" + x);
                System.out.println("depth ="+state.cost);
                System.out.println("path to goal :");
                while(state != null){
                    stackPrinting.push(state);
                    state=state.parent;
                }
                while (!stackPrinting.isEmpty()){
                    printArray(stackPrinting.pop().array);
                }
                return true;
            }
            state.setNeighbours(state,goal);

            for (int i = 0 ; i < state.neighbours.size() ; i ++){
                if( !visited.contains(Arrays.deepToString(state.neighbours.get(i).array)))
                {
                    frontier.add(state.neighbours.get(i));
                    visited.add(Arrays.deepToString(state.neighbours.get(i).array)) ;
                }
            }

        }


        return false;
    }

    public void printArray(int [][] array){
        for (int i = 0 ; i <3 ; i++){
            for (int j= 0; j < 3 ; j++)
                System.out.print(array[i][j]);
        }
        System.out.println();
    }

    //check whether this is the goal
    public boolean check(int state[][] , int goal [][]){
        for (int i = 0 ; i < 3 ; i++)
        {
            for (int j = 0 ; j < 3 ; j++)
                if (state[i][j] != goal[i][j])
                    return false;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println("enter initial state :");
        Scanner sc = new Scanner(System.in);
        int [][] initial = new int[3][3];

        for (int i = 0 ; i <3 ; i++){
            for (int j = 0 ; j < 3 ;j++) {
                initial[i][j] = sc.nextInt();
            }
        }
        int [][] goal = {{0,1,2},
                {3,4,5},
                {6,7,8}};

        bfsNode6 obj = new bfsNode6();

        long start = System.currentTimeMillis();
        obj.bfsNode(initial,goal);
        long end = System.currentTimeMillis();
        float sec = (end - start) / 1000F;
        System.out.print("time taken :");
        System.out.println(sec + " seconds");
    }
}
