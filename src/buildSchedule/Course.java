package buildSchedule;

import java.util.ArrayList;
import java.util.Arrays;

public class Course {
    String name;
    String code;
    ArrayList<CourseDetail> courseDetails;
    int [] visited; //Pendiente: falta lógica sobre cómo utilizar
    int available; //Numero de detalles que están disponibles.

    public Course(String name, String code){
        this.name = name;
        this.code = code;
        this.courseDetails = new ArrayList<>();
    }

    public boolean addDetails(ArrayList<CourseDetail> details){
        this.courseDetails.addAll(details);
        return true;
    }

    public boolean addDetails(CourseDetail[] details){
        this.courseDetails.addAll(Arrays.asList(details));
        return true;
    }


    public boolean addDetail(CourseDetail detail){
        if(this.courseDetails.contains(detail)) return false;
        this.courseDetails.add(detail);
        this.available++;
        this.visited = Arrays.copyOf(this.visited, this.visited.length+1);

        return true;
    }

    public boolean addDetail(String professor, String code, ArrayList<DayPeriodActivity> periods){
        CourseDetail detail = new CourseDetail(professor, code, this,  periods);
        if(this.courseDetails.contains(detail)) return false;
        this.courseDetails.add(detail);
        this.available++;
        this.visited = Arrays.copyOf(this.visited, this.visited.length+1);

        return true;
    }

    public boolean addDetail(String professor, String code,  int priority, ArrayList<DayPeriodActivity> periods){
        CourseDetail detail = new CourseDetail(professor, code, this, priority, periods);
        if(this.courseDetails.contains(detail)) return false;
        this.courseDetails.add(detail);
        this.available++;
        this.visited = Arrays.copyOf(this.visited, this.visited.length+1);

        return true;
    }
    
    public static ArrayList<Course> generateCourseList(Course ... courses){
        ArrayList<Course> courseList = new ArrayList<>();
        for(Course course: courses){
            courseList.add(course);
        }
		return courseList;
    }
    
    public static Course[] generateCourseArray(Course ... courses){
		return courses;
	}

    @Override
    public String toString() {
        String str = String.format("Name: %s\nCode: %s\n", this.name, this.code);

        for (CourseDetail courseDetail : this.courseDetails) {
            str += courseDetail.toString() + "\n";
        }
        return str;
    }
    
    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Course) || obj==null) return false;

        Course object = (Course) obj;
        return this.code.equals(object.code);
    }

    @Override
    public int hashCode() {
        return this.code.hashCode();
    }
}
 
