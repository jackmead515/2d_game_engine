package com.engine.jsm.abilities;

import com.engine.jsm.creatures.Creature;
import com.engine.jsm.entities.ICreatureUpdateable;

public class Stats implements ICreatureUpdateable {

    private float health;
    private float mana;
    private float energy;
    private float strength;
    private float agility;
    private float defence;
    private float stamina;
    private float castSpeed;
    private float attackSpeed;
    private float movementSpeed;

    public Stats() {

    }

    public float getHealth() {
        return health;
    }

    public void setHealth(float health) {
        this.health = health;
    }

    public float getMana() {
        return mana;
    }

    public void setMana(float mana) {
        this.mana = mana;
    }

    public float getEnergy() {
        return energy;
    }

    public void setEnergy(float energy) {
        this.energy = energy;
    }

    public float getStrength() {
        return strength;
    }

    public void setStrength(float strength) {
        this.strength = strength;
    }

    public float getAgility() {
        return agility;
    }

    public void setAgility(float agility) {
        this.agility = agility;
    }

    public float getDefence() {
        return defence;
    }

    public void setDefence(float defence) {
        this.defence = defence;
    }

    public float getStamina() {
        return stamina;
    }

    public void setStamina(float stamina) {
        this.stamina = stamina;
    }

    public float getCastSpeed() {
        return castSpeed;
    }

    public void setCastSpeed(float castSpeed) {
        this.castSpeed = castSpeed;
    }

    public float getAttackSpeed() {
        return attackSpeed;
    }

    public void setAttackSpeed(float attackSpeed) {
        this.attackSpeed = attackSpeed;
    }

    public float getMovementSpeed() { return movementSpeed; }

    public void setMovementSpeed(float movementSpeed) {
        this.movementSpeed = movementSpeed;
    }

    @Override
    public void update(Creature self) {

    }
}
