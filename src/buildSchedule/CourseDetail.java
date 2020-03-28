package buildSchedule;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumMap;


public class CourseDetail implements Comparable<CourseDetail> {
	String name;
	String professor;
	int priority;
	EnumMap<Days, ArrayList<Integer>> classDays;

	public CourseDetail(String name, String professor, ArrayList<DayPeriodActivity> periods) {
		this.name = name;
		this.professor = professor;
		this.priority = 1;
		this.classDays = new EnumMap<Days, ArrayList<Integer>>(Days.class);
		for (Days day : Days.values()) { 
		    this.classDays.put(day, new ArrayList<>());
		    
		}
		
		for (int i = 0; i < periods.size(); i++) {
			DayPeriodActivity currentPeriod = periods.get(i);
			if(!this.classDays.get(currentPeriod.day).contains(currentPeriod.period)) 
				this.classDays.get(currentPeriod.day).add(currentPeriod.period);
		}
	}
	
	public CourseDetail(String name, String professor, int priority, ArrayList<DayPeriodActivity> periods) {
		this(name,  professor, periods);
		this.priority = priority;
	}
	
	public String toString() {
		String periodsPerDay ="";
		
		for (Days day : Days.values()) { 
		    ArrayList<Integer> timePeriodList = this.classDays.get(day);
		    periodsPerDay +=  day.toString() + ": ";
		    for(Integer period: timePeriodList) {
		    	periodsPerDay += String.format("%d ", period);
		    }
		    periodsPerDay += "\n";
		    
		} 
		
		String result = String.format("Professor: %s\nPriority: %d\n\n%s",  this.professor, this.priority, periodsPerDay);
		
		return result;
	}

	public static void sortCourseDetailList(CourseDetail[] details) {
		Arrays.sort(details);
	}
	

	public static void printDetailList(CourseDetail[] details) {
		for(CourseDetail courseDet: details) {
			System.out.println(courseDet.toString());
		}
	}
	
	public static CourseDetail[] generateCourseDetailArray(CourseDetail ... details){
		return details;
	}
	
	@Override
	public int compareTo(CourseDetail o) {
		if(o==null) return -1;
		return o.priority - this.priority;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof CourseDetail) || obj==null) return false;

		CourseDetail object = (CourseDetail) obj;

		return this.professor.equals(object.professor) && this.classDays.equals(object.classDays);
	}
	
}
