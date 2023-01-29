package edu.pjatk.inn.requestor;

import edu.pjatk.inn.coffeemaker.*;
import sorcer.service.*;

import static sorcer.co.operator.*;
import static sorcer.eo.operator.*;
import static sorcer.eo.operator.context;

public class Client {
    private Mogram createInventory() throws Exception {
        Context mocha = context(val("key", "mocha"), val("price", 100),
                val("amtCoffee", 8), val("amtMilk", 1),
                val("amtSugar", 1), val("amtChocolate", 2));

        Task order = task("order", sig("makeOrder", OrderService.class), context(
                val("recipe/key", "mocha"),
                val("order/dateTime", "29-01-2022 12:25:10"),
                val("order/quantity", 3),
                val("order/price"),
                val("recipe", mocha)));

        Task makeCoffee = task("makeCoffee", sig("makeCoffee", CoffeeService.class), context(
                val("coffee/paid", 455),
                val("coffee/status"),
                val("coffee/change")));

        Task delivery = task("delivery", sig("deliver", Delivery.class), context(
                val("location", "PJATK"),
                val("delivery/paid"),
                val("room", "101")));

        Task inventory = task("recipe", sig("createInventory", InventoryService.class), context(
                val("inventory/description", "inventory added"),
                val("inventory/status")));

        Job placeOrder = job(order, makeCoffee, delivery, inventory,
                pipe(outPoint(order, "order/price"), inPoint(makeCoffee, "payment/change")),
                pipe(outPoint(makeCoffee, "coffee/change"), inPoint(delivery, "delivery/paid")),
                pipe(outPoint(delivery, "delivery/status"), inPoint(inventory, "inventory/status")));

        return placeOrder;
    }
}
