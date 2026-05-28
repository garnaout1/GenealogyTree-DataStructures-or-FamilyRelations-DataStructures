import java.util.Map;

public class Relations {

    private final Map<String, Person> idToPerson;
    private final Map<String, String> nameToId;

    public Relations(Map<String, Person> idToPerson, Map<String, String> nameToId) {
        this.idToPerson = idToPerson;
        this.nameToId = nameToId;
    }

    public boolean isFather(String idA, String idB) {
        Person child = idToPerson.get(idB);
        if(child==null) return false;
        return idA.equals(child.getFatherId());
    }

    public boolean isMother(String idA, String idB) {
        Person child = idToPerson.get(idB);
        if (child==null) return false;
        return idA.equals(child.getMotherId());
    }
    
    public boolean isChild(String idA, String idB) {
        Person child = idToPerson.get(idA);
        if (child == null) return false;

        String f = child.getFatherId();
        String m = child.getMotherId();

        return idB.equals(f) || idB.equals(m);
    }

    public boolean isSibling(String idA, String idB) {
        if(idA.equals(idB)) return false;

        Person a = idToPerson.get(idA);
        Person b = idToPerson.get(idB);

        if (a==null || b==null) return false;

        String af = a.getFatherId();
        String am = a.getMotherId();
        String bf = b.getFatherId();
        String bm = b.getMotherId();

        boolean sameFather = (af != null && af.equals(bf));
        boolean sameMother = (am != null && am.equals(bm));

        return sameFather || sameMother;
    }

    public boolean isGrandparent(String idA, String idB) {
        Person b = idToPerson.get(idB);
        if (b == null) return false;

        String father = b.getFatherId();
        String mother = b.getMotherId();

        if (father !=null) {
            Person f = idToPerson.get(father);
            if (f != null) {
                if (idA.equals(f.getFatherId()) || idA.equals(f.getMotherId())) return true;
            }
        }

        if (mother != null) {
            Person m = idToPerson.get(mother);
            if (m != null) {
                if(idA.equals(m.getFatherId()) || idA.equals(m.getMotherId())) return true;
            }
        }
        return false;
    }
    public boolean isGrandchild(String idA, String idB) {
        return isGrandparent(idB, idA);
    }

    public boolean isFirstCousin(String idA, String idB) {
        if(isSibling(idA,idB)) return false;
        if(idA.equals(idB)) return false;

        Person a = idToPerson.get(idA);
        Person b = idToPerson.get(idB);
        if (a == null || b == null) return false;

        String af = a.getFatherId();
        String am = a.getMotherId();

        String bf = b.getFatherId();
        String bm = b.getMotherId();

        if((af==null && am==null) || (bf==null && bm==null)) return false;

        return (af != null && bf != null && isSibling(af, bf)) ||
                (af != null && bm != null && isSibling(af, bm)) ||
                (am != null && bf != null && isSibling(am, bf)) ||
                (am != null && bm != null && isSibling(am, bm));
    }


    public String relation(String nameA, String nameB) {
        
        String idA = nameToId.get(nameA);
        String idB = nameToId.get(nameB);

        if(idA == null) return "Το όνομα δεν βρέθηκε: " + nameA;
        if(idB == null) return "Το όνομα δεν βρέθηκε: " + nameB;

        if(idA.equals(idB)) return "Ίδιο πρόσωπο.";

        if (isFather(idA, idB))
            return nameA + " είναι ο πατέρας του/της " + nameB;

        if (isMother(idA, idB))
            return nameA + " είναι η μητέρα του/της " + nameB;

        if (isChild(idA, idB))
            return nameA + " είναι το παιδί του/της " + nameB;

        if (isHalfSibling(idA, idB))
            return nameA + " είναι ετεροθαλής αδελφός/ή του/της " + nameB;

        if (isSibling(idA, idB))
            return nameA + " είναι αδελφός/ή του/της " + nameB;

        if (isGrandparent(idA, idB))
            return nameA + " είναι παππούς/γιαγιά του/της " + nameB;

        if (isGrandchild(idA, idB))
            return nameA + " είναι εγγόνι του/της " + nameB;

        if (isFirstCousin(idA, idB))
            return nameA + " είναι πρώτος/η ξάδερφος/η του/της " + nameB;

        if (isSpouse(idA, idB))
            return nameA + " είναι σύζηγος του/της " + nameB;

        return "Δεν έχουν συγγένεια." ;
    }

    public boolean isSpouse(String idA, String idB) {
        Person a = idToPerson.get(idA);
        if (a == null) return false;

    String spouse = a.getSpouseId();
        return spouse != null && spouse.equals(idB);
}

    public boolean isHalfSibling(String idA, String idB) {
    if (idA.equals(idB)) return false;

    Person a = idToPerson.get(idA);
    Person b = idToPerson.get(idB);
    if (a == null || b == null) return false;

    String af = a.getFatherId();
    String am = a.getMotherId();
    String bf = b.getFatherId();
    String bm = b.getMotherId();

    boolean sameFather = (af != null && af.equals(bf));
    boolean sameMother = (am != null && am.equals(bm));

    // ακριβώς ένας κοινός γονέας
    return (sameFather ^ sameMother);
}
}
