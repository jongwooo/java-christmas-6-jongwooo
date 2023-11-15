package christmas.domain.menu;

public enum MenuType {

    APPETIZER(true),
    MAIN(true),
    DESSERT(true),
    DRINK(false);

    private final boolean standaloneOrder;

    MenuType(final boolean standaloneOrder) {
        this.standaloneOrder = standaloneOrder;
    }

    public boolean isAvailableStandaloneOrder() {
        return standaloneOrder;
    }
}
