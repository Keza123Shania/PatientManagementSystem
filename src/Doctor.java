public class Doctor extends Person{
    private String specialty;

    public Doctor(String name, int age, String gender, String specialty) {
        super(name, age, gender);
        this.specialty = specialty;
    }

    @Override
    public void performRole() {
        System.out.println("Doctor " + getName() + " specializes " + specialty + ".");
    }
}
