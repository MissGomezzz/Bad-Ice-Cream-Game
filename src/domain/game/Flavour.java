package domain.game;

public enum Flavour {
    VANILLA("vanilla"),
    STRAWBERRY("strawberry"),
    CHOCOLATE("chocolate");

    private final String prefix;

    Flavour(String prefix) {
        this.prefix = prefix;
    }

    public String prefix() {
        return prefix;
    }
}
