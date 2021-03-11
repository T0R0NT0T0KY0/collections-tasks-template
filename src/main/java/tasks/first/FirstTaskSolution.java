package tasks.first;

import BreadthFirst.Point;

import java.util.*;

public class FirstTaskSolution implements FirstTask {
	private String stringWidth;
	private LinkedHashSet<Point> pointLinkedHashSet;
	private ArrayDeque<Point> arrayDequeue;

	@Override
    public String breadthFirst(boolean[][] adjacencyMatrix, int startIndex) {
	    createPointsByMatrix(adjacencyMatrix);

	    stringWidth = "";

	    Point startPoint = lookAtStartPoint(startIndex);

	    arrayDequeue = new ArrayDeque();
	    arrayDequeue.offerFirst(startPoint);
	    startPoint.setWasTaken(true);
	    breadthFirstChildren(startPoint);

	    return stringWidth;
    }

	public void createPointsByMatrix(boolean[][] matrix){
		pointLinkedHashSet = new LinkedHashSet<>();
		for (int i = 0; i < matrix.length; i++) {
			pointLinkedHashSet.add(new Point(i));
		}


		Iterator<Point> iterator = pointLinkedHashSet.iterator();

		for (int i = 0; i < matrix.length; i++) {

			Point thisPoint = iterator.next();

			Iterator<Point> iterator2 = pointLinkedHashSet.iterator();

			ArrayList<Point> connectsPoints = new ArrayList<>();

			for (int j = 0; j < matrix.length; j++) {

				if (matrix[i][j]){
					connectsPoints.add(iterator2.next());
				}
				else {
					iterator2.next();
				}
			}
			thisPoint.setConnectsPoints(connectsPoints);
		}
	}

	public void breadthFirstChildren(Point point){

		for (int i = 0; i < point.getConnectsPoints().size(); i++) {
			if (!point.getConnectsPoints().get(i).isWasTaken()){
				arrayDequeue.offerFirst(point.getConnectsPoints().get(i));
				point.getConnectsPoints().get(i).setWasTaken(true);
			}
		}
		stringWidth+=arrayDequeue.peekLast().getPointNumber() + ", ";
		arrayDequeue.removeLast();
		if (!arrayDequeue.isEmpty()){
			breadthFirstChildren(arrayDequeue.peekLast());
		}
		return;
	}

	private Point lookAtStartPoint(int indexStart) {

		Iterator<Point> iterator = pointLinkedHashSet.iterator();


		while(iterator.hasNext()){
			Point tempPoint = iterator.next();
			if (tempPoint.getPointNumber() == indexStart){
				return tempPoint;
			}
		}
		return null;
	}




	@Override
    public Boolean validateBrackets(String s) {
		Map<Character, Character> dictionary = new HashMap<>();
		dictionary.put('(',')');
		dictionary.put('[',']');
		dictionary.put('{','}');

		if (Objects.isNull(s)){
			return false;
		}

		ArrayDeque<Character> arrayDeque = new ArrayDeque< >();

		for (int i = 0; i < s.length(); i++) {
			switch (s.charAt(i)){
				case'[':
				case'(':
				case'{':
					arrayDeque.push(s.charAt(i));
					break;


				case')':
				case']':
				case'}':

					if (!arrayDeque.isEmpty() && dictionary.get(arrayDeque.peek()).equals(s.charAt(i))){
						arrayDeque.pop();
					}else {
						return false;
					}
					break;
			}
		}
		return arrayDeque.isEmpty();
    }

    @Override
    public Long polishCalculation(String s) {
	    if (Objects.isNull(s)){
		    throw new NullPointerException();
	    }
	    if (s.length()==0){
		    return (long)0;
	    }

	    String[] countArray = s.split(" ");

	    ArrayDeque<Long> arrayDeque = new ArrayDeque<>();

	    for (int i = 0; i < countArray.length; i++) {
		    switch (countArray[i]){
			    case"+":
			    case"-":
			    case"*":
			    case"/":
				    if (arrayDeque.size()<=1){
					    throw new NoSuchElementException();
				    }else {
					    arrayDeque.push(solution(arrayDeque.pop(), arrayDeque.pop(), countArray[i]));

				    }
				    break;
			    default:arrayDeque.push(Long.valueOf(countArray[i]));
				    break;
		    }
	    }
	    if (arrayDeque.isEmpty()){
		    throw new IllegalArgumentException();
	    }
	    return arrayDeque.pop();
    }

	private long solution(long x, long y, String operator) {
		switch (operator) {
			case "/":
				return x / y;
			case "*":
				return x * y;
			case "+":
				return x + y;
			case "-":
				return x - y;
		}
		throw new IllegalArgumentException();
	}
}
