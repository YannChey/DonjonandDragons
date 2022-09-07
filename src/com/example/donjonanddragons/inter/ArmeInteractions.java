package com.example.donjonanddragons.inter;

public interface ArmeInteractions {
    void displayYouAreEquiped(String type, String weaponName);

    void displayYouCantTakeThisWeapon();

    void displayYouAreAlreadyEquiped(String weaponName, String typeYouAreEquiped);

    String displayChooseIfYouWantThisWeapon();

    void displayDontTakeThisWeapon();

}
