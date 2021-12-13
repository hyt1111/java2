package builder;

import java.util.ArrayList;
import java.util.Date;

public class Scheduler {
	//AppointBuilderを使ってAppointmentのインスタンスを作る
	public Appointment createAppointment(AppointmentBuilder builder,
			Date startDate, Date endDate, String description, Location location,
			ArrayList<Contact> attendees) throws NoSuchFieldException{
		if (builder == null) builder = new AppointmentBuilder();
		builder.buildAppointment();
		builder.buildDates(startDate, endDate);
		builder.buildDescription(description);
		builder.buildAttendees(attendees);
		builder.buildLocation(location);
		return builder.getAppointment();
	}

	//AppointBuilder2を使ってAppointmentのインスタンスを作る（例）
	public Appointment createAppointment(Date startDate, Location location) {
		return new AppointmentBuilder2(startDate, location)
		.description("To see my old friends at " + location.toString())
		.addAttendee(new Contact("Taro Dendai", "close friend"))
		.addAttendee(new Contact("Jiro Dendai", "ex-girlfriend"))
		.build() ;
	}
}
