package application;

public enum Saluturi {
	BUNA_ZIUA("Buna ziua"),
    BUNA_DIMINEATA("Buna dimineata"),
    BUNA_SEARA("Buna seara"),
	WELCOME("Welcome"),
	HI("hi"),
	BONJOUR("Bonjour");


    private final String salut;

    Saluturi(String salut) {
        this.salut = salut;
    }

    public String getSalut() {
        return salut;
    }
}