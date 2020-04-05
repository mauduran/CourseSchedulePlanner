package buildSchedule;

import java.util.ArrayList;

public class SchedulePlanner {

    public static ArrayList<Schedule> generateSchedulesFromCourses(Course ... courseList){
        return generateSchedules(courseList);
    }


	public static ArrayList<Schedule> generateSchedules(Course[] courseList){
        ArrayList<Course> courses = new ArrayList<>();

        for (Course course : courseList) {
            courses.add(course);
        }
        return generateSchedules(courses);
    }


    public static ArrayList<Schedule> generateSchedules(ArrayList<Course> courseList){
        return new ArrayList<Schedule>();
    }


	public static void main(String[] args) {
        
    }
	
}
