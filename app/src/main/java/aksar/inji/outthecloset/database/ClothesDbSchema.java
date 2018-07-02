package aksar.inji.outthecloset.database;

public class ClothesDbSchema {
    public static final class ClothesTable {
        public static final String NAME = "clothes";

        public static final class Cols {
            public static final String brandId = "brandId";
            public static final String UUID = "uuid";
            public static final String NAME = "name";
            public static final String DATE = "date";
            public static final String COST = "cost";
            public static final String SIZE = "size";
            public static final String COLOR = "color";
            public static final String NOTES = "notes";
            public static final String DIY = "diy";
        }
    }
}
