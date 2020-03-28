package buildSchedule;

import java.util.ArrayList;

public class DayPeriodActivity {
	Days day;
	int period;
	
	public DayPeriodActivity(Days day, int period) {
		this.day = day;
		this.period = period;
	}
	
	public static ArrayList<DayPeriodActivity> generateActivityPeriodList(DayPeriodActivity ...activities){
		ArrayList<DayPeriodActivity> list = new ArrayList<DayPeriodActivity> ();
		for (DayPeriodActivity activity: activities) {
			list.add(activity);
		}
		return list;
	}


	public static ArrayList<DayPeriodActivity> generateActivityPeriodList(){
		ArrayList<DayPeriodActivity> list = new ArrayList<DayPeriodActivity> ();
		return list;
	}

	public static void addActivityPeriodToList(ArrayList<DayPeriodActivity> list, Days day, int period){
		DayPeriodActivity temp = new DayPeriodActivity(day, period);

		if(list.contains(temp)) return;

		list.add(temp);
	}

	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof DayPeriodActivity)) return false;

		DayPeriodActivity object = (DayPeriodActivity) obj;
		return this.day.equals(object.day) && this.period==object.period;
	}
}

