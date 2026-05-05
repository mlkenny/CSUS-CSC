// Backtracking solver
public class KnapsackBTSolver extends KnapsackBFSolver
{
	protected KnapsackInstance inst;
	protected KnapsackSolution crntSoln;
	protected KnapsackSolution bestSoln;

	public void FindSolns(int itemNum) {
		int itemCnt = inst.GetItemCnt();
		if (crntSoln.GetWeight() > inst.GetCapacity()) { // Backtrack if curCap > totalCap.
			return;
		}
		if (itemNum == itemCnt + 1) {
			CheckCrntSoln();
			return;
		}
		crntSoln.DontTakeItem(itemNum);
		FindSolns(itemNum + 1);
		crntSoln.UndoDontTakeItem(itemNum); // Backtracking, undo dont take.
		crntSoln.TakeItem(itemNum);
		FindSolns(itemNum + 1);
		crntSoln.UndoTakeItem(itemNum); // Backtracking, undo take.
	}

	public void CheckCrntSoln() {
		int crntVal = crntSoln.ComputeValue();
		// System.out.print("\nChecking solution ");
		// crntSoln.Print(" ");

		if (crntVal == DefineConstants.INVALID_VALUE) {
			return;
		}

		if (bestSoln.GetValue() == DefineConstants.INVALID_VALUE)
		{
			bestSoln.Copy(crntSoln);
		} else {
			if (crntVal > bestSoln.GetValue()) {
				bestSoln.Copy(crntSoln);
			}
		}
	}

	public KnapsackBTSolver()
	{
		crntSoln = null;
	}

	public void close() {
		if (crntSoln != null) {
			crntSoln = null;
		}
	}

	public void Solve(KnapsackInstance inst_, KnapsackSolution soln_) {
		inst = inst_;
		bestSoln = soln_;
		crntSoln = new KnapsackSolution(inst);
		FindSolns(1);
	}
}