import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class FlightPlanChainedComparator implements Comparator<FlightPlan> {
    private List<Comparator<FlightPlan>> listComparators;

    @SafeVarargs
    public FlightPlanChainedComparator(Comparator<FlightPlan>... comparators) {
        this.listComparators = Arrays.asList(comparators);
    }

    @Override
    public int compare(FlightPlan fp1, FlightPlan fp2) {
        for (Comparator<FlightPlan> comparator : listComparators) {
            int result = comparator.compare(fp1, fp2);
            if (result != 0) {
                return result;
            }
        }
        return 0;
    }
}
