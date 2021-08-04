package medical;
import java.util.List;
import java.util.Set;

import person.Patient;

// 질병관리본부
public class CDC extends Organization{
	private List<Hospital> hospitalList;
	private Set<Patient> patientList;
	public CDC(String name, int employeeCount, List<Hospital> hospitalList, Set<Patient> patientList) {
		super(name, employeeCount);
		this.hospitalList = hospitalList;
		this.patientList = patientList;
	}
	public CDC() {}
	public List<Hospital> getHospitalList() {
		return hospitalList;
	}
	public void setHospitalList(List<Hospital> hospitalList) {
		this.hospitalList = hospitalList;
	}
	public Set<Patient> getPatientList() {
		return patientList;
	}
	public void setPatientList(Set<Patient> patientList) {
		this.patientList = patientList;
	}
	public void about() { // 오버 라이딩
		System.out.println("Orgainization - CDC : " + super.getName());
	}
	public void about(String more) { // 오버로딩
		about();
		System.out.println("We manage Hospital and Patients" );
	}
	// HW추가
	public void addPatient(Patient p) {
		this.patientList.add(p);
	}
	public void removePatient(Patient p) {
		this.patientList.remove(p);
	}
}
