package org.katas.refactoring;

/**
 * OrderReceipt prints the details of order including customer name, address, description, quantity,
 * price and amount. It also calculates the sales tax @ 10% and prints as part
 * of order. It computes the total order amount (amount of individual lineItems +
 * total sales tax) and prints it.
 */
public class OrderReceipt {
    private Order order;

    public OrderReceipt(Order order) {
        this.order = order;
    }

    public String printReceipt() {
        StringBuilder output = new StringBuilder("======Printing Orders======\n");


        output.append(order.getCustomerName());
        output.append(order.getCustomerAddress());

        double totalSalesTx = 0d;
        double total = 0d;
        for (LineItem lineItem : order.getLineItems()) {
            printLineItem(output, lineItem);
        }
        totalSalesTx = getTotalSalesTx();
        total = getTotal();
        printsTheStateColumn(output, totalSalesTx, "Sales Tax");

        printsTheStateColumn(output, total, "Total Amount");
        return output.toString();
    }

    private double getTotal() {
        double total;
        total = order.getLineItems().stream().mapToDouble(x->x.totalAmount()+x.totalAmount()*.10).sum();
        return total;
    }

    private double getTotalSalesTx() {
        double totalSalesTx;
        totalSalesTx = order.getLineItems().stream().mapToDouble(x -> x.totalAmount() * .10).sum();
        return totalSalesTx;
    }

    private void printsTheStateColumn(StringBuilder output, double totalSalesTx, String s) {
        output.append(s).append('\t').append(totalSalesTx);
    }

    private void printLineItem(StringBuilder output, LineItem lineItem) {
        output.append(lineItem.getDescription());
        output.append('\t');
        output.append(lineItem.getPrice());
        output.append('\t');
        output.append(lineItem.getQuantity());
        output.append('\t');
        output.append(lineItem.totalAmount());
        output.append('\n');
    }
}