package buildSchedule;

import java.util.ArrayList;

public class Schedule {
	
	ArrayList<Integer> monday;
	ArrayList<Integer> tuesday;
	ArrayList<Integer> wednesday;
	ArrayList<Integer> thursday;
	ArrayList<Integer> friday;
	
	public Schedule(int maxClassesPerDay) {
		monday = new ArrayList<>(maxClassesPerDay);
		tuesday = new ArrayList<>(maxClassesPerDay);
		wednesday = new ArrayList<>(maxClassesPerDay);
		thursday = new ArrayList<>(maxClassesPerDay);
		friday = new ArrayList<>(maxClassesPerDay);
	}
	
	int [] fulldays = {0,0,0,0,0};
	
	public static void main(String[] args) {
		// Days today = Days.MONDAY;
	}
	
}
