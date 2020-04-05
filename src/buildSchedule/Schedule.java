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

	public Schedule(int maxClassesPerDay) {
		this.maxClassesPerDay = maxClassesPerDay;
		calendar = new CourseDetail[5][maxClassesPerDay];
		this.courseCount = 0;
		this.coursesAdded = new HashSet<>();
		this.courseDetails = new HashMap<>();
	}

	public boolean addCourseToSchedule(CourseDetail detail){
		if(detail==null || detail.parent==null || coursesAdded.contains(detail.parent)) return false;
		
		if(!isPeriodAvailable(detail.classDays)) return false;

		if(!this.addDetailToSchedule(detail)) return false;
		coursesAdded.add(detail.parent);
		courseDetails.put(detail.parent, detail);

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
		return str;
	}
	
	public static void main( String[] args) {

		//Clase de Requerimientos 
		Course RequerimientosClase = new Course("Especificación de requerimientos", "req0123");

		ArrayList<DayPeriodActivity> diasDeClase = DayPeriodActivity.generateActivityPeriodList();
        DayPeriodActivity.addActivityPeriodToList(diasDeClase, Days.MONDAY, 1);
        DayPeriodActivity.addActivityPeriodToList(diasDeClase, Days.THURSDAY, 1);
        CourseDetail requerimientos = new CourseDetail( "Ricardo Anaya", "RQ1", RequerimientosClase, diasDeClase);
        // CourseDetail[] details= CourseDetail.generateCourseDetailArray(requerimientos);

		//Clase de formales

		Course LenguajesClase = new Course("Lenguajes Formales", "LF0123");

		ArrayList<DayPeriodActivity> diasDeClase1 = DayPeriodActivity.generateActivityPeriodList();
        DayPeriodActivity.addActivityPeriodToList(diasDeClase1, Days.MONDAY, 3);
        DayPeriodActivity.addActivityPeriodToList(diasDeClase1, Days.WEDNESDAY, 3);
        CourseDetail lenguajesFormales = new CourseDetail("Luis Peres", "LF1",LenguajesClase, diasDeClase1);
        
        // CourseDetail[] details1= CourseDetail.generateCourseDetailArray(LenguajesFormales);    
        
        //
		//Clase de bases
		Course BasesClase = new Course("Bases de datos", "BTDT0123");

		ArrayList<DayPeriodActivity> diasDeClase2 = DayPeriodActivity.generateActivityPeriodList();
        DayPeriodActivity.addActivityPeriodToList(diasDeClase2, Days.TUESDAY, 3);
        DayPeriodActivity.addActivityPeriodToList(diasDeClase2, Days.THURSDAY, 3);
        CourseDetail bases = new CourseDetail("Victor Hugo", "BD1", BasesClase,  diasDeClase2);
        
        // CourseDetail[] details2= CourseDetail.generateCourseDetailArray(bases);
            

		//Clase gráfica
		Course GraficaClase = new Course("Programación Gráfica", "graf102");

		ArrayList<DayPeriodActivity> diasDeClaseGrafica = DayPeriodActivity.generateActivityPeriodList();
        DayPeriodActivity.addActivityPeriodToList(diasDeClaseGrafica, Days.MONDAY, 4);
        DayPeriodActivity.addActivityPeriodToList(diasDeClaseGrafica, Days.THURSDAY, 4);

        CourseDetail grafica = new CourseDetail("Hugo Ivan Piza", "GRA1", GraficaClase, diasDeClaseGrafica);
        
        // CourseDetail[] graficaDetails= CourseDetail.generateCourseDetailArray(grafica);
            
		Schedule mySchedule = new Schedule(8);

		mySchedule.addCourseToSchedule(requerimientos);

		mySchedule.addCourseToSchedule(grafica);

		mySchedule.addCourseToSchedule(bases);
		
		mySchedule.addCourseToSchedule(lenguajesFormales);

		System.out.println(mySchedule);
	}


	
}
