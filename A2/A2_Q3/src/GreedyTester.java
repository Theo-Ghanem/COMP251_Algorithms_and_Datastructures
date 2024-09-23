import java.util.Arrays;

public class GreedyTester {
	public static void main(String[] args) {
		
		//This is the typical kind of input you will be tested with. The format will always be the same
		//Each index represents a single homework. For example, homework zero has weight 23 and deadline t=3.
//		int[] weights = new int[] {23, 60, 14, 25, 7};  //the relative importance of the homework towards your final grade
//		int[] deadlines = new int[] {3, 1, 2, 1, 3};

		//pass
		int[] weights = new int[] {60, 37, 51, 44};
		int[] deadlines = new int[] {1, 2, 3, 3};
		System.out.println("Expected output: [0, 3, 2]");

		//pass
//		int[] weights = new int[] {30, 10, 20};
//		int[] deadlines = new int[] {3, 1, 2};

		//pass
//		int[] weights = new int[] {6, 7, 1, 9000, 2};
//		int[] deadlines = new int[] {1,1,1,1,1};

		//pass
//		int[] weights = new int[] {5};
//		int[] deadlines = new int[] {1000};

		int m = weights.length;
		
		//This is the declaration of a schedule of the appropriate size
		HW_Sched schedule =  new HW_Sched(weights, deadlines, m);
		
		//This call organizes the assignments and outputs homeworkPlan
		int[] res = schedule.SelectAssignments();
//		System.out.println(Arrays.toString(res));
	}
		
}
