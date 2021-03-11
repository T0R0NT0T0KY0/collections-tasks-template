import tasks.first.FirstTaskSolution;

public class MainForTest {
	public static void main(String[] args) {
		FirstTaskSolution firstTaskSolution = new FirstTaskSolution();
		boolean[][] b = {{false, true, true, false, false, false, false, false, false, false},
				{true, false, true, true, false, false, false, false, false, false},
				{true, true, false, false, false, false, false, false, false, true},
				{false, true, false, false, true, false, true, false, true, false},
				{false, false, false, true, false, true, false, false, false, false},
				{false, false, false, false, true, false, true, false, false, false},
				{false, false, false, true, false, true, false, true, false, false},
				{false, false, false, false, false, false, true, false, false, false},
				{false, false, false, true, false, false, false, false, false, true},
				{false, false, true, false, false, false, false, false, true, false}};
		System.out.println(firstTaskSolution.breadthFirst(b, 5));
		System.out.println(firstTaskSolution.breadthFirst(b, 9));
		System.out.println(firstTaskSolution.breadthFirst(b, 0));
	}
}
