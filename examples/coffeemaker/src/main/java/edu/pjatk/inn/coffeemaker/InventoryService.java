package edu.pjatk.inn.coffeemaker;

import sorcer.service.Context;
import sorcer.service.ContextException;

import java.rmi.RemoteException;

public interface InventoryService {
    Context createInventory(Context context) throws ContextException, RemoteException;

    Context getInventory(Context context) throws ContextException, RemoteException;
}
