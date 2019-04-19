package dynamic_greedy;

import java.util.ArrayList;
import java.util.List;

public class Weighted_Job_Scheduling_Dynamic {

	public static void main(String[] args) {
		Job a = new Job("a", 1, 4, 3);
		Job b = new Job("b", 2, 6, 5);
		Job c = new Job("c", 4, 7, 2);
		Job d = new Job("d", 6, 8, 6);
		Job e = new Job("e", 5, 9, 4);
		Job f = new Job("f", 7, 10, 8);

		/*
		 * We have to short the jobs in ascending order of end time.For
		 * simplicity we are creating the jobs in ascending order and fill up
		 * the profit array with their corresponding profits.
		 */

		int[] profits = { 5, 6, 5, 4, 11, 2 };
		Job[] jobs = { a, b, c, d, e, f };

		for (int i = 1; i < profits.length; i++) {
			for (int j = 0; j < i; j++) {
				if (jobs[i].startTime >= jobs[j].endTime
						&& jobs[i].profit + profits[j] > profits[i]) {
					profits[i] = jobs[i].profit + profits[j];
				}
			}
		}

		// Print profit array
		System.out.print("Profits: ");
		for (int i : profits)
			System.out.print(+i + " ");
		System.out.println();

		// Find out the maximum profit and index of the profit
		int maxIndex = 0;
		int maxProfit = profits[0];

		for (int i = 1; i < profits.length; i++) {
			if (profits[i] > maxProfit) {
				maxProfit = profits[i];
				maxIndex = i;
			}
		}

		// Back tracking to see which are the jobs we can use together
		List<Job> compatibleJobs = new ArrayList<>();

		for (int j = 0; j < maxIndex; j++) {
			if (jobs[j].endTime <= jobs[maxIndex].startTime) {
				compatibleJobs.add(jobs[j]);
			}
		}
		compatibleJobs.add(jobs[maxIndex]);

		// Now check that in the compatibleJobs those jobs are compatible
		// among them except the last job(Because with last job only the maximum
		// profit came),if not we consider the max profit among them

		for (int i = 0; i < compatibleJobs.size() - 1; i++) {
			if (compatibleJobs.get(i).endTime > compatibleJobs.get(i + 1).startTime) {
				if (compatibleJobs.get(i).profit > compatibleJobs.get(i + 1).profit)
					compatibleJobs.remove(i + 1);
				else
					compatibleJobs.remove(i);
			}
			i--;// Suppose a,b,c jobs are present.After removing b the list
				// become a,c.Then we have to compare a with c, so i=1 now, to
				// compare again with a we have to reduce the index number to
				// zero

			if (compatibleJobs.get(i + 2).jobName
					.equals(jobs[maxIndex].jobName))
				break;
		}
		System.out.println("Job scheduled : " + compatibleJobs);
		System.out.println("Highest profit: " + profits[maxIndex]);
	}
}

class Job {
	String jobName;
	int startTime;
	int endTime;
	int profit;

	Job(String jobName, int startTime, int endTime, int profit) {
		this.jobName = jobName;
		this.startTime = startTime;
		this.endTime = endTime;
		this.profit = profit;
	}

	public String toString() {
		return jobName;
	}
}