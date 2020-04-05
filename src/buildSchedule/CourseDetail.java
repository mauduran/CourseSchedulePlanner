package buildSchedule;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumMap;


public class CourseDetail implements Comparable<CourseDetail> {
	String professor;
	int priority;
	String code;
	Course parent;
	EnumMap<Days, ArrayList<Integer>> classDays;

	public CourseDetail(String professor, String code, Course parent, ArrayList<DayPeriodActivity> periods) {
		this.professor = professor;
		this.parent = parent;
		this.priority = 1;
		this.code = code;
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
	
	public CourseDetail(String professor, String code, Course parent, int priority, ArrayList<DayPeriodActivity> periods) {
		this(professor, code, parent, periods);
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
