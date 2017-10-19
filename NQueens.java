/**An NQueens board that holds the solution to the N-Queens problem.  The constructor creates an empty board of size n by n.
 * The placeQueens() method attempts to place n queens on the board (in a recursive manner).  If successful then method
 * return true, otherwise it returns false.  The toString() returns a string representation of the board with dots (.) for
 * empty locations and the letter Q for where queens should be placed.
 * 
 * @author Louis Oliphant, italianoaj, & bountsebese.
 *
 */
public class NQueens {
	private final int QUEEN=1;
	private final int EMPTY=0;
	private int[][] board;
	private final int MAX;
	public double start;
	public double end;
	public double time;

	/**Initializes a board of size n by n as empty.  Calls to toString()
	 * should return dots at all positions.
	 * @param n -- the size of the board
	 */
	public NQueens(int n) {
		if (n<=0) throw new IllegalStateException("Can't have NQueens board <= 0.");
		board=new int[n][n];  //initialize board to be an n x n array of all zeros
		this.MAX = n;
	}

	/**places N queens on the board so that no queen can attack any other queen.
	 * After a call to this method, if all queens were successfully placed, calls to toString() should
	 * show where the queens are placed on the board.  If this method was not successful (no placement of
	 * queens was found) then calls to toString() should be unchanged (all dots).
	 * @return returns true if all queens were successfully placed.  Otherwise returns false.
	 */
	public boolean placeQueens() {
		start = System.currentTimeMillis();
		int[] array = new int[MAX];
		return placeQueens(array, 0);

		//TODO finish this
		//Wrapper class that must call some recursive private method
	}
	/**
	 * This placeQueens method is the recursive method that is called by the wrapper. 
	 * @param columns the number of columns that are on the board
	 * @param current the current column the queen is being placed.
	 * @return returns true if all the queens were placed correctly, false otherwise. 
	 */
	private boolean placeQueens(int[] columns, int current) {
		if (current == MAX) {
			end = System.currentTimeMillis();
			return true;
		}
		else {

			for (int i = 0; i < MAX; i++) {

				columns[current] = i;
				
				if (canPlace(columns, current) && placeQueens(columns, current+1)) {

					board[current][i] = QUEEN;
					return placeQueens(columns, current+1);
				}
				
			}
			return false;
		}
	}  
	/**
	 * The canPlace method checks the possibilities of where a queen can be placed.
	 * @param columns the array of possible columns on the board.
	 * @param current the current column the queen is attempting to be placed in.
	 * @return true if there is a position the queen can be placed, false otherwise. 
	 */
	private boolean canPlace(int[] columns, int current) {
		for (int i = 0; i < current; i++) {
			if (columns[i] == columns[current]) {
				return false;   
			}
			if ((columns[i] - columns[current]) == (current - i)) {
				return false;  
			}
			if ((columns[current] - columns[i]) == (current - i)) {
				return false;   
			}
		}
		return true;
	}
	/**
	 * Testing main method, may be removed later. 
	 * @param args
	 */
	public static void main(String[] rgs) {
		NQueens queens = new NQueens(30);
		queens.placeQueens();
		System.out.println(queens.end-queens.start);
		System.out.println(queens.toString());
	}
	/** Returns a string representation of the board.  You should not make any changes to this method.
	 * @return returns a string representation of the board.  A new board (before call to placeQueens) should return
	 *         a dot (.) at every location.  After a call to placeQueens, the letter Q should appear wherever there is
	 *         a queen on the board (the remaining locations should be dots (.).
	 */
	@Override
	public String toString() {
		StringBuilder result=new StringBuilder();
		for(int i=0;i<board.length;i++) {
			for(int j=0;j<board[i].length;j++) {
				switch (board[i][j]) {
				case EMPTY:
					result.append(".");
					break;
				case QUEEN:
					result.append("Q");
					break;
				default:
					result.append("E"); //error -- should never happen
				}
			}
			result.append("\n");
		}
		return result.toString();
	}
}