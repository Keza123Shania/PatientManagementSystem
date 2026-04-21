public class Patient extends Person{
    private String sickness;

    public Patient(String name, int age, String gender, String sickness) {
        super(name, age, gender);
        this.sickness = sickness;
    }

    @Override
    public void performRole(){
        System.out.println("Patient: " + getName() + " --receiving treatment for: " + sickness);
    }
}
