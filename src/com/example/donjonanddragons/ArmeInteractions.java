package com.example.donjonanddragons;

public interface ArmeInteractions {
    void displayYouAreEquiped(String type, String weaponName);

    void displayYouCantTakeThisWeapon();

    void displayYouAreAlreadyEquiped(String weaponName, String typeYouAreEquiped);
}
