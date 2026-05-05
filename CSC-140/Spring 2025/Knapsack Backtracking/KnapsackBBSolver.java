
// Branch-and-Bound solver

import java.util.*;

public class KnapsackBBSolver extends KnapsackBFSolver
{
	protected KnapsackInstance inst;
	protected KnapsackSolution crntSoln;
	protected KnapsackSolution bestSoln;
	protected UPPER_BOUND ub;

	private int totalValueSum;
	private Integer[] wghtValList;
	private Integer[] amortizedList;
	private HashSet<Integer> takenList = new HashSet<Integer>();

	public void FindSolns(int itemNum) {
		switch (ub.getValue()) {
			case 0:
				int itemCnt = inst.GetItemCnt();
				if (crntSoln.GetWeight() > inst.GetCapacity()) { // Backtrack if curCap > totalCap.
					return;
				}
				if (itemNum == itemCnt + 1) {
					CheckCrntSoln();
					return;
				}
				// Upperbound 1: Assume we can take all undecided items.
				// Return if remaining sum of values is less than best value.
				if (totalValueSum - crntSoln.GetUntakenValue() <= bestSoln.GetValue()) {
					return;
				}
				crntSoln.DontTakeItem(itemNum);
				FindSolns(itemNum + 1);
				crntSoln.UndoDontTakeItem(itemNum); // Backtracking, undo dont take.
				crntSoln.TakeItem(itemNum);
				FindSolns(itemNum + 1);
				crntSoln.UndoTakeItem(itemNum); // Backtracking, undo take.
				break;
			case 1:
				itemCnt = inst.GetItemCnt();
				if (crntSoln.GetWeight() > inst.GetCapacity()) {
					return;
				}
				if (itemNum == itemCnt + 1) {
					CheckCrntSoln();
					return;
				}
				// Upperbound 2: Assume we can take every item that fits in the capacity.
				// Return if sum of undecided values plus sum of taken values is less than best value.
				// O(n) Computation:
				int undecidedValue = 0;
				for (int i = itemNum; i <= itemCnt; i++) {
					if (inst.GetItemWeight(i) <= inst.GetCapacity()) {
						undecidedValue += inst.GetItemValue(i);
					}
				}
				if (crntSoln.GetValue() + undecidedValue <= bestSoln.GetValue()) {
					return;
				}
				crntSoln.DontTakeItem(itemNum);
				FindSolns(itemNum + 1);
				crntSoln.UndoDontTakeItem(itemNum);
				crntSoln.TakeItem(itemNum);
				FindSolns(itemNum + 1);
				crntSoln.UndoTakeItem(itemNum);
				break;
			case 2:
				itemCnt = inst.GetItemCnt();
				if (crntSoln.GetWeight() > inst.GetCapacity()) {
					return;
				}
				if (itemNum == itemCnt + 1) {
					CheckCrntSoln();
					return;
				}
				// Upperbound 3: Fractional Knapsack Solution with Remaining Items and Remaining Capacity.
				if (FractionalKnapsack(itemNum, inst.GetCapacity() - crntSoln.GetWeight()) + crntSoln.GetValue() <= bestSoln.GetValue()) {
					return;
				}
				crntSoln.DontTakeItem(itemNum);
				FindSolns(itemNum + 1);
				crntSoln.UndoDontTakeItem(itemNum);
				crntSoln.TakeItem(itemNum);
				FindSolns(itemNum + 1);
				crntSoln.UndoTakeItem(itemNum);
				break;
			case 3:
				itemCnt = inst.GetItemCnt();
				if (crntSoln.GetWeight() > inst.GetCapacity()) {
					return;
				}
				if (itemNum == itemCnt + 1) {
					CheckCrntSoln();
					return;
				}
				// Upperbound 4: Amortized Fractional Knapsack.
				if (AmortizedFractionalKnapsack(itemNum, inst.GetCapacity() 
					+ inst.GetItemWeight(itemNum) - crntSoln.GetWeight()) + crntSoln.GetValue()
					<= bestSoln.GetValue()) {
					return;
				}
				crntSoln.DontTakeItem(itemNum);
				takenList.remove(itemNum);
				FindSolns(itemNum + 1);
				crntSoln.UndoDontTakeItem(itemNum);
				takenList.add(itemNum);
				crntSoln.TakeItem(itemNum);
				takenList.add(itemNum);
				FindSolns(itemNum + 1);
				crntSoln.UndoTakeItem(itemNum);
				takenList.remove(itemNum);
				break;
		}
	}
	// O(n) time Fractonal Knapsack Algorithm.
	public int FractionalKnapsack(int itemNum, int cap) {
		int value = 0;
		int load = 0;
		int index = 0;
		int itemCount = inst.GetItemCnt() - itemNum;
		wghtValList = new Integer[itemCount + 1];

		// Add remaining items to wghtValList.
		for (int i = itemNum; i <= inst.GetItemCnt(); i++) {
			wghtValList[index] = i;
			index++;
		}

		// Sort array based off value / weight ratios in descending order.
		// Using longs here to avoid floating point precision errors.
		Arrays.sort(wghtValList, (a, b) -> {
			long aVal = inst.GetItemValue(a);
			long aWgt = inst.GetItemWeight(a);
			long bVal = inst.GetItemValue(b);
			long bWgt = inst.GetItemWeight(b);

			return Long.compare(bVal * aWgt, aVal * bWgt);
		});
		// Add item value to total if weight fits in capacity,
		// else add fraction of item value to total based on (cap / itemWght) * itemVal.
		for (int j = 0; j < wghtValList.length; j++) {
			if (inst.GetItemWeight(wghtValList[j]) <= cap - load) {
				value += inst.GetItemValue(wghtValList[j]); // "Take" item.
				load += inst.GetItemWeight(wghtValList[j]); // Update load.
			} else {
				// Is this wrong to use double?
				value += (inst.GetItemValue(wghtValList[j])) 
						* (cap - load) / (inst.GetItemWeight(wghtValList[j])); // Take C - load of item.
				load = cap; // Force load to be cap since maximum items have been taken.
				break;
			}
		}

		return value;
	}
	// O(n)/n => O(1) time on average Amoritzed Fractional Knapsack Algorithm.
	public int AmortizedFractionalKnapsack(int itemNum, int cap) {
		int value = 0;
		int load = 0;

		for (int i = itemNum; i <= inst.GetItemCnt(); i++) {
			if (!takenList.contains(amortizedList[i])) {
				if (inst.GetItemWeight(amortizedList[i]) <= cap - load) {
					value += inst.GetItemValue(amortizedList[i]);
					load += inst.GetItemWeight(amortizedList[i]);
				} else {
					value += (inst.GetItemValue(amortizedList[i]))
							* ((cap - load) / inst.GetItemWeight(amortizedList[i]));
					load = cap;
					break;
				}
			}
		}

		return value;
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

	public KnapsackBBSolver(UPPER_BOUND ub_)
	{
		ub = ub_;
		crntSoln = null;
	}
	public void close()
	{
    	if (crntSoln != null) {
			crntSoln = null;
		}
	}
	public void Solve(KnapsackInstance inst_, KnapsackSolution soln_)
	{
		inst = inst_;
		bestSoln = soln_;
		crntSoln = new KnapsackSolution(inst);
		for (int i = 1; i <= inst.GetItemCnt(); i++) {
			totalValueSum += inst.GetItemValue(i); // Calculate total sum of values at start.
		}
		amortizedList = new Integer[inst.GetItemCnt() + 1];
		for (int i = 0; i <= inst.GetItemCnt(); i++) {
			amortizedList[i] = i;
		}
		// Sort items into amortizedList based on value / weight ratio
		// in descending order.
		Arrays.sort(amortizedList, (a, b) -> {
			long aVal = inst.GetItemValue(a);
			long aWgt = inst.GetItemWeight(a);
			long bVal = inst.GetItemValue(b);
			long bWgt = inst.GetItemWeight(b);

			return Long.compare(bVal * aWgt, aVal * bWgt);
		});
		FindSolns(1);
	}
}