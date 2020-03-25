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
}
