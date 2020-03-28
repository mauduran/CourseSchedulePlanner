package buildSchedule;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.HashSet;

public class Schedule {
	
	CourseDetail[][] calendar;
	int courseCount;
	HashSet<Course> coursesAdded;
	HashMap<Course, CourseDetail> courseDetails;

	public Schedule(int maxClassesPerDay) {
		calendar = new CourseDetail[5][maxClassesPerDay];
		this.courseCount = 0;
		this.coursesAdded = new HashSet<>();
		this.courseDetails = new HashMap<>();
	}

	public boolean addCourseToSchedule(Course course, CourseDetail detail){
		if(!course.courseDetails.contains(detail)|| 
		course==null || detail==null || coursesAdded.contains(course)) return false;
		
		if(!isPeriodAvailable(detail.classDays)) return false;

		return true;
		
	}

	public boolean isPeriodAvailable(EnumMap<Days, ArrayList<Integer>> periods){
		ArrayList<Integer> monday = periods.get(Days.MONDAY);
		ArrayList<Integer> tuesday = periods.get(Days.TUESDAY);
		ArrayList<Integer> wednesday = periods.get(Days.WEDNESDAY);
		ArrayList<Integer> thursday = periods.get(Days.THURSDAY);
		ArrayList<Integer> friday = periods.get(Days.FRIDAY);

		for (Integer period : monday) {
			if(this.calendar[0][period]!=null) return false;
		}

		for (Integer period : tuesday) {
			if(this.calendar[1][period]!=null) return false;
			
		}
		for (Integer period : wednesday) {
			if(this.calendar[2][period]!=null) return false;
		}
		for (Integer period : thursday) {
			if(this.calendar[3][period]!=null) return false;
		}
		for (Integer period : friday) {
			if(this.calendar[4][period]!=null) return false;
		}

		return true;
	}
	
	public static void main(final String[] args) {
		Schedule mySchedule = new Schedule(8);
		System.out.println("g");
	}


	
}
