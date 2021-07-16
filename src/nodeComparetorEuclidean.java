import java.util.Comparator;

public class nodeComparetorEuclidean implements Comparator<node> {
    @Override
    public int compare(node o1, node o2) {
        if (o1.euclideanh > o2.euclideanh)
            return 1;
        else if (o1.euclideanh < o2.euclideanh)
            return -1;
        return 0;
    }
}
