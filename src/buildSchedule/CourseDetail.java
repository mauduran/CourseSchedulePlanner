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
		
		String result = String.format("Name: %s\nProfessor: %s\nPriority: %d\n%s", this.name, this.professor, this.priority, periodsPerDay);
		
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
	
	
	@Override
	public int compareTo(CourseDetail o) {
		return o.priority - this.priority;
	}
	
	
	public static void main(String[] args) {
		CourseDetail[] details = new CourseDetail[3];
		
		ArrayList<DayPeriodActivity> diasDeClase =  DayPeriodActivity.generateActivityPeriodList(new DayPeriodActivity(Days.MONDAY, 1), new DayPeriodActivity(Days.THURSDAY, 1));
		
		CourseDetail requerimientos = new CourseDetail("Especificación de Requerimientos", "Ricardo Anaya", diasDeClase);
		
		ArrayList<DayPeriodActivity> diasDeClase2 =  DayPeriodActivity.generateActivityPeriodList(new DayPeriodActivity(Days.MONDAY, 2), new DayPeriodActivity(Days.WEDNESDAY, 2));
		
		CourseDetail requerimientos2 = new CourseDetail("Especificación de Requerimientos", "El pequeño rufián", 8, diasDeClase2);
		
		ArrayList<DayPeriodActivity> diasDeClase3 =  DayPeriodActivity.generateActivityPeriodList(new DayPeriodActivity(Days.TUESDAY, 2), new DayPeriodActivity(Days.THURSDAY, 2));
		
		CourseDetail requerimientos3 = new CourseDetail("Especificación de Requerimientos", "Le rufus rufilio", 10, diasDeClase3);
		
		
		details[2] = requerimientos;
		details[0] = requerimientos2;
		details[1] = requerimientos3;
		printDetailList(details);
		System.out.println("");
		System.out.println("");
		System.out.println("");
		sortCourseDetailList(details);
		printDetailList(details);
		

	}


}
