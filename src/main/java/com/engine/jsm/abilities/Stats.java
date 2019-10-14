package com.engine.jsm.abilities;

import com.engine.jsm.creatures.Creature;
import com.engine.jsm.entities.ICreatureUpdateable;

public class Stats implements ICreatureUpdateable {

    private double health;
    private double mana;
    private double energy;
    private double strength;
    private double agility;
    private double defence;
    private double stamina;
    private double castSpeed;
    private double attackSpeed;
    private double movementSpeed;

    public Stats() {

    }

    public double getHealth() {
        return health;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    public double getMana() {
        return mana;
    }

    public void setMana(double mana) {
        this.mana = mana;
    }

    public double getEnergy() {
        return energy;
    }

    public void setEnergy(double energy) {
        this.energy = energy;
    }

    public double getStrength() {
        return strength;
    }

    public void setStrength(double strength) {
        this.strength = strength;
    }

    public double getAgility() {
        return agility;
    }

    public void setAgility(double agility) {
        this.agility = agility;
    }

    public double getDefence() {
        return defence;
    }

    public void setDefence(double defence) {
        this.defence = defence;
    }

    public double getStamina() {
        return stamina;
    }

    public void setStamina(double stamina) {
        this.stamina = stamina;
    }

    public double getCastSpeed() {
        return castSpeed;
    }

    public void setCastSpeed(double castSpeed) {
        this.castSpeed = castSpeed;
    }

    public double getAttackSpeed() {
        return attackSpeed;
    }

    public void setAttackSpeed(double attackSpeed) {
        this.attackSpeed = attackSpeed;
    }

    public double getMovementSpeed() { return movementSpeed; }

    public void setMovementSpeed(double movementSpeed) {
        this.movementSpeed = movementSpeed;
    }

    @Override
    public void update(Creature self) {

    }
}
