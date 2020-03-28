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

    public Course(String name, String code, ArrayList<CourseDetail> details){
        this(name, code);
        this.courseDetails = details;

        this.available = this.courseDetails.size();
        this.visited = new int[this.courseDetails.size()];
    }

    public Course(String name, String code, CourseDetail [] details){
        this(name, code);
        for (CourseDetail courseDetail : details) {
            this.courseDetails.add(courseDetail);
        }
        this.available = this.courseDetails.size();
        this.visited = new int[this.courseDetails.size()];
    }

    public boolean addDetail(CourseDetail detail){
        if(this.courseDetails.contains(detail)) return false;
        this.courseDetails.add(detail);
        this.available++;
        this.visited = Arrays.copyOf(this.visited, this.visited.length+1);

        return true;
    }

    public boolean addDetail(String name, String professor, ArrayList<DayPeriodActivity> periods){
        CourseDetail detail = new CourseDetail(name, professor, periods);
        if(this.courseDetails.contains(detail)) return false;
        this.courseDetails.add(detail);
        this.available++;
        this.visited = Arrays.copyOf(this.visited, this.visited.length+1);

        return true;
    }

    public boolean addDetail(String name, String professor, int priority, ArrayList<DayPeriodActivity> periods){
        CourseDetail detail = new CourseDetail(name, professor, priority, periods);
        if(this.courseDetails.contains(detail)) return false;
        this.courseDetails.add(detail);
        this.available++;
        this.visited = Arrays.copyOf(this.visited, this.visited.length+1);

        return true;
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


    public static void main(String[] args) {
        
        ArrayList<DayPeriodActivity> diasDeClase = DayPeriodActivity.generateActivityPeriodList();
        DayPeriodActivity.addActivityPeriodToList(diasDeClase, Days.MONDAY, 1);
        DayPeriodActivity.addActivityPeriodToList(diasDeClase, Days.THURSDAY, 1);
        DayPeriodActivity.addActivityPeriodToList(diasDeClase, Days.FRIDAY, 1);
        CourseDetail requerimientos = new CourseDetail("Especificación de Requerimientos", "Ricardo Anaya", diasDeClase);
        
        ArrayList<DayPeriodActivity> diasDeClase2 = DayPeriodActivity.generateActivityPeriodList();
        DayPeriodActivity.addActivityPeriodToList(diasDeClase2, Days.MONDAY, 2);
        CourseDetail requerimientos2 = new CourseDetail("Especificación de Requerimientos", "El pequeño rufián", 3, diasDeClase2);
        
        ArrayList<DayPeriodActivity> diasDeClase3 =   DayPeriodActivity.generateActivityPeriodList();
        DayPeriodActivity.addActivityPeriodToList(diasDeClase3, Days.THURSDAY, 1);
        DayPeriodActivity.addActivityPeriodToList(diasDeClase3, Days.TUESDAY, 1);
        CourseDetail requerimientos3 = new CourseDetail("Especificación de Requerimientos", "Le Mauri Durán", 1, diasDeClase3);

        CourseDetail[] details= CourseDetail.generateCourseDetailArray(requerimientos, requerimientos2, requerimientos3);
            
        
        Course RequerimientosClase = new Course("Especificación de requerimientos", "req0123", details);


        RequerimientosClase.addDetail("Especificación de Requerimientos", "Me pongo bien alterado", 2, diasDeClase3);


        System.out.println("1");
        System.out.println("2");
        System.out.println("");
        System.out.println(RequerimientosClase);
    }

}
 
