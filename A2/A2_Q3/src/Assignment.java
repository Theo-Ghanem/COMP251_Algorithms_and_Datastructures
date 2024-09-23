//no collaborators
import java.util.*;

class Assignment implements Comparator<Assignment> {
	int number; //the index of the Assignment in the input array
	int weight;  //weight of assignment
	int deadline; //deadline of assignment

	protected Assignment() {
	}

	protected Assignment(int number, int weight, int deadline) {
		this.number = number; //the index of the Assignment in the input array
		this.weight = weight; //weight of assignment
		this.deadline = deadline; //deadline of assignment
	}

	/**
//	 * This method is used to sort to compare assignment objects for sorting.
//	 */
//	@Override
//	public int compare(Assignment a1, Assignment a2) { //By deadline, then weight
//		// TODO Implement this
//
//		// return 0, if the two items are equivalent
//		// return 1, if a1 should appear after a2 in the sorted list
//		// return -1, if a2 should appear after a1 in the sorted list
//
//
//		if ((a1.deadline) == (a2.deadline)) { //the assignments have the same deadline
//			if ((a1.weight) == (a2.weight)) //they have the same weight
//				return 0;
//			if ((a1.weight) > (a2.weight)) //A1 has a bigger weight then A2
//				return -1;
//			else return 1; //A2 has a bigger weight then A1
//		} else if ((a1.deadline) < (a2.deadline)) { //A1 needs to be completed before A2
//			return -1;
//		} else { //A2 needs to be completed before A1
//			return 1;
//		}
//	}

	/**
	 * This method is used to sort to compare assignment objects for sorting.
	 */
	@Override
	public int compare(Assignment a1, Assignment a2) { //By weight, then deadline
		// TODO Implement this

		// return 0, if the two items are equivalent
		// return 1, if a1 should appear after a2 in the sorted list
		// return -1, if a2 should appear after a1 in the sorted list


		if ((a1.weight) == (a2.weight)) { //the assignments have the same weight
			if ((a1.deadline) == (a2.deadline)) //they have the same weight
				return 0;
			if ((a1.deadline) < (a2.deadline)) //A1 has a smaller deadline than A2
				return -1;
			else return 1; //A2 has a smaller deadline than A1
		} else if ((a1.weight) > (a2.weight)) { //A1 needs to be completed before A2
			return -1;
		} else { //A2 needs to be completed before A1
			return 1;
		}
	}
}
