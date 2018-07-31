package aksar.inji.outthecloset;

public class SymbolTable {

    private String[] images = new String[] {"belt" , "blazer" , "blue_jean" , "collar_shirt" , "collar_shirt_and_tie" ,
                                            "collar_t_shirt" , "dress" , "hand_bag" , "high_heel" , "jacket" , "jumpsuit_romper" ,
                                            "purse" , "scarf" , "shoe" , "shorts" , "skirt" , "sleevless_dress" , "sleevless_top" ,
                                            "sneaker" , "sweater" , "swim_suit" , "trousers"};

    enum ClothesIcons{ belt, blazer, blue_jean, collar_shirt, collar_shirt_and_tie, collar_t_shirt, dress, hand_bag, high_heel, jacket, jumpsuit_ronper,
        purse, scarf, shoe, shorts, skirt, sleevless_dress, sleevless_top, sneaker, sweater, swim_suit, trousers;
    }


    public String getIcon(int icon) {
        ClothesIcons[] icons = ClothesIcons.values();
        return icons[icon].toString();
    }
}
