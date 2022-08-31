package app.org.halsa360.pojo;

/**
 * Created by viji on 7/2/16.
 */
public class Patient {
    private String patientimage;
    private String patientname;
    private String age;
    private String mobilepatient;
    private String patientid;

    private int flag;

    public Patient(String patientid, String patientimage, String patientname, String age, String mobilepatient

    ) {
        this.patientid=patientid;
        this.patientimage = patientimage;
        this.patientname = patientname;
        this.age = age;
        this.mobilepatient = mobilepatient;


    }

    public String getPatientid() {
        return patientid;
    }

    public void setPatientid(String patientid) {
        this.patientid = patientid;
    }

    public String getPatientimage() {
        return patientimage;
    }

    public void setPatientimage(String patientimage) {
        this.patientimage = patientimage;
    }

    public String getPatientname() {
        return patientname;
    }

    public void setPatientname(String patientname) {
        this.patientname = patientname;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }


    public String getMobilepatient() {
        return mobilepatient;
    }

    public void setMobilepatient(String mobilepatient) {
        this.mobilepatient = mobilepatient;
    }

}
