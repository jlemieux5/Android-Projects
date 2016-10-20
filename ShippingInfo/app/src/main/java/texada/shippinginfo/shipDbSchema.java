package texada.shippinginfo;

/**
 * Created by Josh on 10/18/2016.
 */

// Class for easy access to table and row names
public class shipDbSchema {
    public static final class shipTable {
        public static final String NAME = "shippingInfo";

        public static final class Cols {
            public static final String ID = "id";
            public static final String PRODUCT = "product";
            public static final String DATE = "date";
            public static final String LAT = "latitude";
            public static final String LONG = "longitude";
            public static final String ELV = "elevation";
        }
    }
}
