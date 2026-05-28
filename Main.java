public class Main {
    public static void main(String[] args) throws Exception{

        CSVLoader loader = new CSVLoader();
        loader.loadPersons("Person.csv");

        Relations r = new Relations(loader.getIdToPerson(), loader.getNameToId());

        if (args.length == 0) {
            System.out.println("Χρήση:");
            System.out.println("  java Main relation \"Name1\" \"Name2\"");
            System.out.println();
            System.out.println("Παράδειγμα:");
            System.out.println("  java Main relation \"Giannis Papadopoulos\" \"Kostas Papadopoulos\"");
            return;
        }

        // CLI: relation "Name1" "Name2"
        if (args.length == 3 && args[0].equalsIgnoreCase("relation")) {
            String nameA = args[1];
            String nameB = args[2];
            System.out.println(r.relation(nameA, nameB));
            return;
        }

        System.out.println("Λάθος arguments.");
        System.out.println("Χρήση: java Main relation \"Name1\" \"Name2\"");
    }



    }

