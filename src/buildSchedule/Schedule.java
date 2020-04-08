package buildSchedule;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.HashSet;

public class Schedule {	
	CourseDetail[][] calendar;
	int courseCount;
	int maxClassesPerDay;
	HashSet<Course> coursesAdded;
	HashMap<Course, CourseDetail> courseDetails;
	// int prioritySum = 0;

	public Schedule(int maxClassesPerDay) {
		this.maxClassesPerDay = maxClassesPerDay;
		calendar = new CourseDetail[5][maxClassesPerDay];
		this.courseCount = 0;
		this.coursesAdded = new HashSet<>();
		this.courseDetails = new HashMap<>();
	}

	public Schedule clone(){
		Schedule newSched = new Schedule(this.maxClassesPerDay);
		newSched.courseCount = this.courseCount;
		newSched.courseDetails = (HashMap<Course, CourseDetail>) this.courseDetails.clone();
		newSched.coursesAdded = (HashSet<Course>) this.coursesAdded.clone();

		for(int i=0; i<5; i++){
			newSched.calendar[i] = this.calendar[i].clone();
		}

		return newSched;
	}
	public boolean addCourseToSchedule(CourseDetail detail){
		if(detail==null || detail.parent==null || coursesAdded.contains(detail.parent)) return false;
		
		if(!isPeriodAvailable(detail.classDays)) return false;

		if(!this.addDetailToSchedule(detail)) return false;
		coursesAdded.add(detail.parent);
		courseDetails.put(detail.parent, detail);

		this.courseCount++;

		return true;	
	}


	public boolean removeCourseFromSchedule(CourseDetail detail){
		if(detail==null || detail.parent==null || !this.coursesAdded.contains(detail.parent) 
		|| !this.courseDetails.get(detail.parent).equals(detail)) return false;

		ArrayList<Integer> monday = detail.classDays.get(Days.MONDAY);
		ArrayList<Integer> tuesday = detail.classDays.get(Days.TUESDAY);
		ArrayList<Integer> wednesday = detail.classDays.get(Days.WEDNESDAY);
		ArrayList<Integer> thursday = detail.classDays.get(Days.THURSDAY);
		ArrayList<Integer> friday = detail.classDays.get(Days.FRIDAY);

		for (Integer period : monday) {
			this.calendar[0][period-1] = null;
		}

		for (Integer period : tuesday) {
			this.calendar[1][period-1] = null;
		}
		for (Integer period : wednesday) {
			this.calendar[2][period-1] = null;
		}
		for (Integer period : thursday) {
			this.calendar[3][period-1] = null;
		}
		for (Integer period : friday) {
			this.calendar[4][period-1] = null;
		}

		this.coursesAdded.remove(detail.parent);
		this.courseDetails.remove(detail.parent);

		this.courseCount--;
		return true;
		

	}



	public boolean addDetailToSchedule(CourseDetail detail){
		if(detail == null || detail.classDays.size()==0) return false;

		ArrayList<Integer> monday = detail.classDays.get(Days.MONDAY);
		ArrayList<Integer> tuesday = detail.classDays.get(Days.TUESDAY);
		ArrayList<Integer> wednesday = detail.classDays.get(Days.WEDNESDAY);
		ArrayList<Integer> thursday = detail.classDays.get(Days.THURSDAY);
		ArrayList<Integer> friday = detail.classDays.get(Days.FRIDAY);

		for (Integer period : monday) {
			this.calendar[0][period-1] = detail;
		}

		for (Integer period : tuesday) {
			this.calendar[1][period-1] = detail;
		}
		for (Integer period : wednesday) {
			this.calendar[2][period-1] = detail;
		}
		for (Integer period : thursday) {
			this.calendar[3][period-1] = detail;
		}
		for (Integer period : friday) {
			this.calendar[4][period-1] = detail;
		}
		return true;

	}

	public boolean isPeriodAvailable(EnumMap<Days, ArrayList<Integer>> periods){
		ArrayList<Integer> monday = periods.get(Days.MONDAY);
		ArrayList<Integer> tuesday = periods.get(Days.TUESDAY);
		ArrayList<Integer> wednesday = periods.get(Days.WEDNESDAY);
		ArrayList<Integer> thursday = periods.get(Days.THURSDAY);
		ArrayList<Integer> friday = periods.get(Days.FRIDAY);

		for (Integer period : monday) {
			if(this.calendar[0][period-1]!=null) return false;
		}

		for (Integer period : tuesday) {
			if(this.calendar[1][period-1]!=null) return false;
		}
		for (Integer period : wednesday) {
			if(this.calendar[2][period-1]!=null) return false;
		}
		for (Integer period : thursday) {
			if(this.calendar[3][period-1]!=null) return false;
		}
		for (Integer period : friday) {
			if(this.calendar[4][period-1]!=null) return false;
		}
		return true;
	}

	@Override
	public String toString() {
		String str = String.format("Num cursos: %d\n\n", this.courseCount);
		
		str += "\tMon\t\tTue\t\tWed\t\tThu\t\tFri\n";
		for(int i=0; i<this.calendar[0].length;i++){
			str += (i+1) + "\t";
			for(int j=0; j<5; j++){
				str+= (this.calendar[j][i]==null)? "-\t\t": this.calendar[j][i].code + "\t\t";
			}
			str += "\n";
		}

		for (CourseDetail detail : this.courseDetails.values()) {
			str += String.format("%s %s: %s\n", detail.parent.name, detail.code, detail.professor);
		}
		return str;
	}
	
	
}
