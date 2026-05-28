public class Person {
    private final String id;
    private final String name;
    private final String gender;     // "Male", "Female", "Unknown"
    private final String fatherId;   // μπορεί να είναι null
    private final String motherId;   // μπορεί να είναι null
    private final String spouseId;   // μπορεί να είναι null

    public Person(String id, String name, String gender, String fatherId, String motherId, String spouseId) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.fatherId = fatherId;
        this.motherId = motherId;
        this.spouseId = spouseId;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getGender() { return gender; }
    public String getFatherId() { return fatherId; }
    public String getMotherId() { return motherId; }
    public String getSpouseId() { return spouseId; }
}