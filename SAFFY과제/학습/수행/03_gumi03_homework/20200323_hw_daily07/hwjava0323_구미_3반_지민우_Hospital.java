package medical;

import java.util.List;

import person.Patient;

public class Hospital extends Organization implements MedicalAction {
	private String hospitalId;
	private int roomTotalCount;
	private int roomEmptyCount;
	public Hospital(String name, int employeeCount, String hospitalId, int roomTotalCount, int roomEmptyCount) {
		super(name, employeeCount);
		this.hospitalId = hospitalId;
		this.roomTotalCount = roomTotalCount;
		this.roomEmptyCount = roomEmptyCount;
	}
	public Hospital() {}
	public String getHospitalId() {
		return hospitalId;
	}
	public void setHospitalId(String hospitalId) {
		this.hospitalId = hospitalId;
	}
	public int getRoomTotalCount() {
		return roomTotalCount;
	}
	public void setRoomTotalCount(int roomTotalCount) {
		this.roomTotalCount = roomTotalCount;
	}
	public int getRoomEmptyCount() {
		return roomEmptyCount;
	}
	public void setRoomEmptyCount(int roomEmptyCount) {
		this.roomEmptyCount = roomEmptyCount;
	}
	public void about(String more) { // 오버로딩
		System.out.println("We are Hospital" );
	}
//	@Override
//	public void addPatient(CDC cdc, Patient p) throws NotCoronaException {
//		// TODO Auto-generated method stub
//		if(!p.isCorona()) throw new NotCoronaException("NotCorona");
//		cdc.getPatientList().add(p);
//	}
//	@Override
//	public void removePatient(CDC cdc, Patient p) {
//		// TODO Auto-generated method stub
//		cdc.getPatientList().remove(p);
//		
//	}
	@Override
	public void writeReport(List<Patient> pList) {
		// TODO Auto-generated method stub
		
	}
	// HW추가
	@Override
	public void addPatient(CDC cdc, Patient p) throws NotCoronaException {
		// TODO Auto-generated method stub
		if(!p.isCorona()) throw new NotCoronaException("NotCorona");
		cdc.addPatient(p);
	}
	@Override
	public void removePatient(CDC cdc, Patient p) {
		// TODO Auto-generated method stub
		cdc.removePatient(p);
	}
}
