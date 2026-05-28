import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class CSVLoader {

    private final Map<String, Person> idToPerson = new HashMap<>();
    private final Map<String, String> nameToId = new HashMap<>();

    public void loadPersons(String csvPath) throws IOException {

        try (BufferedReader br = Files.newBufferedReader(Path.of(csvPath), StandardCharsets.UTF_8)) {

            String header = br.readLine();
            if (header == null) {
                System.out.println("ERROR: Το CSV είναι άδειο.");
                return;
            }

            // Περιμένουμε: id,name,gender,father_id,mother_id,spouse_id
            String line;
            int lineNumber = 1; // header = 1

            while ((line = br.readLine()) != null) {
                lineNumber++;

                if (line.trim().isEmpty()) continue;

                String[] parts = line.split(",", -1); // -1 κρατάει και τα κενά
                if (parts.length != 6) {
                    System.out.println("WARNING: Λάθος αριθμός πεδίων στη γραμμή " + lineNumber + ": " + line);
                    continue;
                }

                String id = parts[0].trim();
                String name = parts[1].trim();
                String gender = parts[2].trim();

                String fatherId = normalizeNullable(parts[3]);
                String motherId = normalizeNullable(parts[4]);
                String spouseId = normalizeNullable(parts[5]);

                if (id.isEmpty() || name.isEmpty() || gender.isEmpty()) {
                    System.out.println("WARNING: Λείπει υποχρεωτικό πεδίο στη γραμμή " + lineNumber + ": " + line);
                    continue;
                }

                Person p = new Person(id, name, gender, fatherId, motherId, spouseId);
                idToPerson.put(id, p);
                nameToId.put(name, id);
            }
        }

        // Επιβεβαίωση parse
        System.out.println("=== Persons loaded: " + idToPerson.size() + " ===");
        for (Person p : idToPerson.values()) {
            System.out.println(p.getName() + " - " + p.getGender());
        }
    }

    private String normalizeNullable(String s) {
        if (s == null) return null;
        String t = s.trim();
        return t.isEmpty() ? null : t;
    }

    public Map<String, Person> getIdToPerson() {
        return idToPerson;
    }

    public Map<String, String> getNameToId() {
        return nameToId;
    }
}
