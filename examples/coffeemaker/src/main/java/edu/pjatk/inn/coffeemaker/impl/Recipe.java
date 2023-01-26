package edu.pjatk.inn.coffeemaker.impl;

import sorcer.core.context.ServiceContext;
import sorcer.service.Context;
import sorcer.service.ContextException;

import java.io.Serializable;
import java.rmi.RemoteException;

/**
 * The Recipe class defines a coffee drink recipe
 * and allows to display and modify it when needed.
 *
 * @author Sarah and Mike
 */
public class Recipe implements Serializable {
	/**
	 * Fields
	 */
    private String name;
    private int price;
    private int amtCoffee;
    private int amtMilk;
    private int amtSugar;
    private int amtChocolate;

	/**
	 * Constructor
	 */
	public Recipe() {
    	this.name = "";
    	this.price = 0;
    	this.amtCoffee = 0;
    	this.amtMilk = 0;
    	this.amtSugar = 0;
    	this.amtChocolate = 0;
    }

    /**
	 * This method returns the amount of chocolate in the drink.
	 *
	 * @return The amount of chocolate in the drink.
	 */
    public int getAmtChocolate() {
		return amtChocolate;
	}

    /**
	 * This method modifies the amount of chocolate in the drink.
	 *
	 * @param amtChocolate The integer value of the new amount of chocolate in the drink.
	 */
    public void setAmtChocolate(int amtChocolate) {
		if (amtChocolate >= 0) {
			this.amtChocolate = amtChocolate;
		}
	}

    /**
	 * This method returns the amount of coffee in the drink.
	 *
	 * @return The amount of coffee in the drink.
	 */
    public int getAmtCoffee() {
		return amtCoffee;
	}

    /**
	 * This method modfies the amount of coffee in the drink.
	 *
	 * @param amtCoffee The integer value of the new amount of coffee in the drink.
	 */
    public void setAmtCoffee(int amtCoffee) {
		if (amtCoffee >= 0) {
			this.amtCoffee = amtCoffee;
		}
	}

    /**
	 * This method returns the amount of coffee in the drink.
	 *
	 * @return The amount of milk in the drink.
	 */
    public int getAmtMilk() {
		return amtMilk;
	}

    /**
	 * This method modfies the amount of coffee in the drink.
	 *
	 * @param amtMilk The integer value of the new amount of milk in the drink.
	 */
    public void setAmtMilk(int amtMilk) {
		if (amtMilk >= 0) {
			this.amtMilk = amtMilk;
		}
	}

    /**
	 * This method returns the amount of sugar in the drink.
	 *
	 * @return The amount of milk in the drink.
	 */
    public int getAmtSugar() {
		return amtSugar;
	}

    /**
	 * This method modifies the amount of sugar in the drink.
	 *
	 * @param amtSugar The integer value of the new amount of sugar in the drink.
	 */
    public void setAmtSugar(int amtSugar) {
		if (amtSugar >= 0) {
			this.amtSugar = amtSugar;
		}
	}

    /**
	 * This method returns the name of the drink
	 *
	 * @return Returns the name of the drink.
	 */
    public String getName() {
		return name;
	}

    /**
	 * This method modifies the name of the drink.
	 *
	 * @param name The string to set the new drink name to.
	 */
    public void setName(String name) {
    	if (name != null) {
    		this.name = name;
    	}
	}

    /**
	 * This method returns the price of the drink.
	 *
	 * @return Returns the price of the drink.
	 */
    public int getPrice() {
		return price;
	}

    /**
	 * This method modifies the price of the drink.
	 *
	 * @param price The integer value to set the new price to.
	 */
    public void setPrice(int price) {
		if (price >= 0) {
			this.price = price;
		}
	}

	/**
	 * This method modifies the price of the drink.
	 *
	 * @param r The integer value to set the new price to.
	 * @return true if recipe names match, otherwise returns false.
	 */
    public boolean equals(Recipe r) {
        if ((this.name).equals(r.getName())) {
            return true;
        }
        return false;
    }

	/**
	 * Returns string representation of the Recipe object.
	 *
	 * @return the name of the Recipe.
	 */
    public String toString() {
    	return name;
    }

	/**
	 * Creates a Recipe object from the provided Context.
	 *
	 * @param context Context object containing information needed for the Recipe.
	 * @return a new Recipe object created based on the information from the provided Context.
	 * @throws ContextException Throws ContextException in case the remote method has failed.
	 */
	static public Recipe getRecipe(Context context) throws ContextException {
		Recipe r = new Recipe();
		try {
			r.name = (String)context.getValue("key");
			r.price = (int)context.getValue("price");
			r.amtCoffee = (int)context.getValue("amtCoffee");
			r.amtMilk = (int)context.getValue("amtMilk");
			r.amtSugar = (int)context.getValue("amtSugar");
			r.amtChocolate = (int)context.getValue("amtChocolate");
		} catch (RemoteException e) {
			throw new ContextException(e);
		}
		return r;
	}

	/**
	 * Creates a new instance of Context from a provided Recipe object.
	 *
	 * @param recipe A Recipe object.
	 * @return instance of a Context based on the Recipe object
	 * @throws ContextException Throws a ContextException in case the remote method has failed.
	 */
	static public Context getContext(Recipe recipe) throws ContextException {
		Context cxt = new ServiceContext();
		cxt.putValue("key", recipe.getName());
		cxt.putValue("price", recipe.getPrice());
		cxt.putValue("amtCoffee", recipe.getAmtCoffee());
		cxt.putValue("amtMilk", recipe.getAmtMilk());
		cxt.putValue("amtSugar", recipe.getAmtSugar());
		cxt.putValue("amtChocolate", recipe.getAmtChocolate());
		return cxt;
	}
}
