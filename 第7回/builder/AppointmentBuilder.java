package builder;

import java.util.ArrayList;
import java.util.Date;

public class AppointmentBuilder {
	public static final int START_DATE_REQUIRED = 1;
	public static final int END_DATE_REQUIRED = 2;
	public static final int DESCRIPTION_REQUIRED = 4;
	public static final int ATTENDEE_REQUIRED = 8;
	public static final int LOCATION_REQUIRED = 16;

	//子孫クラスでも そのまま使いたいフィールド
	protected Appointment appointment;
	protected int requiredElements;

	public void buildAppointment(){ appointment = new Appointment(); }

	public void buildDates(Date startDate, Date endDate){
		Date currentDate = new Date();
		if ((startDate != null) && (startDate.after(currentDate)))
			appointment.setStartDate(startDate);
		if ((endDate != null) && (endDate.after(startDate)))
			appointment.setEndDate(endDate);
	}

	public void buildDescription(String newDescription){
		if (newDescription != null)
			appointment.setDescription(newDescription);
	}

	public void buildAttendees(ArrayList<Contact> attendees){
		if ((attendees != null) && (!attendees.isEmpty()))
			appointment.setAttendees(attendees);
	}

	public void buildLocation(Location newLocation){
		if (newLocation != null)
			appointment.setLocation(newLocation);
	}

	public Appointment getAppointment() throws NoSuchFieldException {
		requiredElements = 0;
		if (appointment.getStartDate() == null)
			requiredElements += START_DATE_REQUIRED;
		if (appointment.getLocation() == null)
			requiredElements += LOCATION_REQUIRED;
		if (requiredElements > 0)
			throw new NoSuchFieldException("Missing required fields: " + requiredElements);
		return appointment;
	}

	public int getRequiredElements(){ return requiredElements; }
}
