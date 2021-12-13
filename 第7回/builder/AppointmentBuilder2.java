package builder;

import java.util.ArrayList;
import java.util.Date;

public class AppointmentBuilder2 {
	private String description;
	private Date startDate;
	private Date endDate;
	private ArrayList<Contact> attendees = new ArrayList<Contact>();
	private Location location;

	//�Œ���K�{���ڂ�����������R���X�g���N�^
	public AppointmentBuilder2(Date startDate, Location location) {
		this.startDate = startDate ;
		this.location = location ;
	}

	//�K�v�ɉ����ČĂ΂�郁�\�b�h
	public AppointmentBuilder2 description(String newDescription) {
		description = newDescription;
		return this ;
	}
	public AppointmentBuilder2 startDate(Date newStartDate) {
		startDate = newStartDate;
		return this ;
	}
	public AppointmentBuilder2 date(Date newEndDate) {
		endDate = newEndDate;
		return this;
	}
	public AppointmentBuilder2 location(Location newLocation) {
		location = newLocation;
		return this ;
	}
	public AppointmentBuilder2 attendees(ArrayList<Contact> newAttendees) {
		if (newAttendees != null) attendees = newAttendees;
		return this ;
	}
	public AppointmentBuilder2 addAttendee(Contact attendee){
		if (!attendees.contains(attendee)) attendees.add(attendee);
		return this ;
	}
	public AppointmentBuilder2 removeAttendee(Contact attendee){
		attendees.remove(attendee);
		return this ;
	}

	//AppointmentBuilder2����Appointment����邽�߂̃��\�b�h.
	//���グ��Appointment��Ԃ����\�b�h.
	public Appointment build(){
		Appointment app = new Appointment();
		app.setDescription(description);
		app.setStartDate(startDate);
		app.setEndDate(endDate);
		app.setLocation(location);
		return app ;
	}
}