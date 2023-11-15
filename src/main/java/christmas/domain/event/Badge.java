package christmas.domain.event;

public enum Badge {

    NOTHING("없음", 0),
    STAR("별", 5_000),
    TREE("트리", 10_000),
    SANTA("산타", 20_000);

    private final String name;
    private final int minimumBenefitPrice;

    Badge(final String name, final int minimumBenefitPrice) {
        this.name = name;
        this.minimumBenefitPrice = minimumBenefitPrice;
    }

    public String getName() {
        return name;
    }

    public static Badge newYearsEventParticipantBadge(final int totalBenefitPrice) {
        if (totalBenefitPrice >= SANTA.minimumBenefitPrice) {
            return SANTA;
        }
        if (totalBenefitPrice >= TREE.minimumBenefitPrice) {
            return TREE;
        }
        if (totalBenefitPrice >= STAR.minimumBenefitPrice) {
            return STAR;
        }
        return NOTHING;
    }
}
