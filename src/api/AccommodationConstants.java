package api;

public enum AccommodationConstants {
    Ξενοδοχείο(1), Διαμέρισμα(2), Μεζονέτα(3);

    private final int i;
    AccommodationConstants(int i) {
        this.i = i;
    }

    public int getI(){
        return i;
    }
}
