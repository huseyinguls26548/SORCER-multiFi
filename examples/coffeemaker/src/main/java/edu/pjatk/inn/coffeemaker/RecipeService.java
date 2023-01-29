package edu.pjatk.inn.coffeemaker;

import sorcer.service.Context;
import sorcer.service.ContextException;

import java.rmi.RemoteException;

public interface RecipeService {
    Context giveRecipe(Context context) throws ContextException, RemoteException;

    Context getRecipe(Context context) throws ContextException, RemoteException;
}
