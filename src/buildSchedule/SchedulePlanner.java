package buildSchedule;

import java.util.ArrayList;


public class SchedulePlanner {

    public static ArrayList<Schedule> generateSchedulesFromCourses(Course ... courseList){
        return generateSchedules(courseList);
    }

    public static ArrayList<Schedule> generateSchedules(ArrayList<Course> courseList){
       Course [] coursesArr = new Course[courseList.size()];
       for(int i=0; i<courseList.size(); i++){
            coursesArr[i] = courseList.get(i);
       }
        return generateSchedules(coursesArr);
    }

    public static ArrayList<Schedule> generateSchedules(Course[] courseList){
        ArrayList<Schedule> solutionSchedules = new ArrayList<>();
        Schedule solution = new Schedule(8);
        
        recursiveScheduleGenerator(courseList, 0, solution, solutionSchedules);


        return solutionSchedules;
    }


    public static boolean recursiveScheduleGenerator(Course[] courseList, int i, Schedule solution, ArrayList<Schedule> solutionSchedules){
        if(i>=courseList.length) return true;

        Course current = courseList[i];
        
        for (CourseDetail detail : current.courseDetails) {
            if(!solution.addCourseToSchedule(detail)) continue;

            if(recursiveScheduleGenerator(courseList, i+1, solution, solutionSchedules)){
                // System.out.println(solution);
                solutionSchedules.add(solution.clone());
            }

            solution.removeCourseFromSchedule(detail);

        }
        return false;
    }


	public static void main(String[] args) {
        ArrayList<Course> courses = new ArrayList<>();

		//Clase de Requerimientos 
		Course RequerimientosClase = new Course("Especificación de requerimientos", "req0123");

		ArrayList<DayPeriodActivity> diasDeClase = DayPeriodActivity.generateActivityPeriodList();
        DayPeriodActivity.addActivityPeriodToList(diasDeClase, Days.MONDAY, 1);
        DayPeriodActivity.addActivityPeriodToList(diasDeClase, Days.THURSDAY, 1);
    	new CourseDetail( "Ricardo Anaya", "RQ1", RequerimientosClase, diasDeClase);
        // CourseDetail[] details= CourseDetail.generateCourseDetailArray(requerimientos);

		courses.add(RequerimientosClase);

		//Clase de formales
		Course LenguajesClase = new Course("Lenguajes Formales", "LF0123");

		ArrayList<DayPeriodActivity> diasDeClase1 = DayPeriodActivity.generateActivityPeriodList();
        DayPeriodActivity.addActivityPeriodToList(diasDeClase1, Days.MONDAY, 3);
        DayPeriodActivity.addActivityPeriodToList(diasDeClase1, Days.WEDNESDAY, 3);
        new CourseDetail("Luis Perez", "LF1",LenguajesClase, diasDeClase1);
		
		ArrayList<DayPeriodActivity> diasDeClase2L = DayPeriodActivity.generateActivityPeriodList();
        DayPeriodActivity.addActivityPeriodToList(diasDeClase2L, Days.MONDAY, 1);
        DayPeriodActivity.addActivityPeriodToList(diasDeClase2L, Days.THURSDAY, 1);
        new CourseDetail("Miriam Diaz", "LF2",LenguajesClase, diasDeClase2L);

		courses.add(LenguajesClase);

		//Clase de bases
		Course BasesClase = new Course("Bases de datos", "BTDT0123");

		ArrayList<DayPeriodActivity> diasDeClase2 = DayPeriodActivity.generateActivityPeriodList();
        DayPeriodActivity.addActivityPeriodToList(diasDeClase2, Days.TUESDAY, 3);
        DayPeriodActivity.addActivityPeriodToList(diasDeClase2, Days.THURSDAY, 3);
        new CourseDetail("Victor Hugo", "BD3", BasesClase,  diasDeClase2);
		
		ArrayList<DayPeriodActivity> diasDeClase2B = DayPeriodActivity.generateActivityPeriodList();
        DayPeriodActivity.addActivityPeriodToList(diasDeClase2B, Days.TUESDAY, 2);
        DayPeriodActivity.addActivityPeriodToList(diasDeClase2B, Days.THURSDAY, 2);
        new CourseDetail("Victor Hugo", "BD32", BasesClase,  diasDeClase2B);

		
		courses.add(BasesClase);

		//Clase gráfica
		Course GraficaClase = new Course("Programación Gráfica", "graf102");

		ArrayList<DayPeriodActivity> diasDeClaseGrafica = DayPeriodActivity.generateActivityPeriodList();
        DayPeriodActivity.addActivityPeriodToList(diasDeClaseGrafica, Days.MONDAY, 4);
        DayPeriodActivity.addActivityPeriodToList(diasDeClaseGrafica, Days.THURSDAY, 4);

		new CourseDetail("Hugo Ivan Piza", "GRA1", GraficaClase, diasDeClaseGrafica);

		courses.add(GraficaClase);

		//Clase Estructurada
        Course EstructuraClase = new Course("Programación Estructurada", "estru102");
        
		ArrayList<DayPeriodActivity> diasDeClaseEstr1 = DayPeriodActivity.generateActivityPeriodList();
		DayPeriodActivity.addActivityPeriodToList(diasDeClaseEstr1, Days.MONDAY, 1);
		DayPeriodActivity.addActivityPeriodToList(diasDeClaseEstr1, Days.THURSDAY, 1);
		
		new CourseDetail("Juan Pablo Aviña", "ESTR1", EstructuraClase, diasDeClaseEstr1);
		
		///2
		ArrayList<DayPeriodActivity> diasDeClaseEstr2 = DayPeriodActivity.generateActivityPeriodList();
		DayPeriodActivity.addActivityPeriodToList(diasDeClaseEstr2, Days.TUESDAY, 1);
		DayPeriodActivity.addActivityPeriodToList(diasDeClaseEstr2, Days.FRIDAY, 1);
		
		new CourseDetail("Juan Pablo Gonzalez", "ESTR2", EstructuraClase, diasDeClaseEstr2);		
		
		/// 3
		ArrayList<DayPeriodActivity> diasDeClaseEstr3 = DayPeriodActivity.generateActivityPeriodList();
		DayPeriodActivity.addActivityPeriodToList(diasDeClaseEstr3, Days.MONDAY, 2);
		DayPeriodActivity.addActivityPeriodToList(diasDeClaseEstr3, Days.WEDNESDAY, 2);
		
		new CourseDetail("Javier de la Mora", "ESTR3", EstructuraClase, diasDeClaseEstr3);
		
		///4
		ArrayList<DayPeriodActivity> diasDeClaseEstr4 = DayPeriodActivity.generateActivityPeriodList();
		DayPeriodActivity.addActivityPeriodToList(diasDeClaseEstr4, Days.MONDAY, 3);
		DayPeriodActivity.addActivityPeriodToList(diasDeClaseEstr4, Days.WEDNESDAY, 3);
		
		new CourseDetail("Jose Manuel Franco", "ESTR4", EstructuraClase, diasDeClaseEstr4);		
		
		//5
		ArrayList<DayPeriodActivity> diasDeClaseEstr5 = DayPeriodActivity.generateActivityPeriodList();
		DayPeriodActivity.addActivityPeriodToList(diasDeClaseEstr5, Days.TUESDAY, 3);
		DayPeriodActivity.addActivityPeriodToList(diasDeClaseEstr5, Days.THURSDAY, 3);
		
		new CourseDetail("Gisel Hernandez", "ESTR5", EstructuraClase, diasDeClaseEstr5);
		
		//6	
		ArrayList<DayPeriodActivity> diasDeClaseEstr6 = DayPeriodActivity.generateActivityPeriodList();
		DayPeriodActivity.addActivityPeriodToList(diasDeClaseEstr6, Days.TUESDAY, 3);
		DayPeriodActivity.addActivityPeriodToList(diasDeClaseEstr6, Days.THURSDAY, 3);
		
		new CourseDetail("Jose Luis Elvira", "ESTR6", EstructuraClase, diasDeClaseEstr6);		
		
		courses.add(EstructuraClase);

        System.out.println("g");


        ArrayList<Schedule> schedules = generateSchedules(courses);
        for (Schedule schedule : schedules) {
            System.out.println(schedule);
        }
    }
	
}
