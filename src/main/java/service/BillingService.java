package service;

public class BillingService {
    // Slab-based calculation: 0-100 @ Rs.20, 101-200 @ Rs.30, >200 @ Rs.45
    public double calculateAmount(int units){
        int remaining = units;
        double amount = 0;
        int[] slabUnits = {100, 100, Integer.MAX_VALUE};
        double[] rates = {20.0, 30.0, 45.0};
        for(int i=0;i<slabUnits.length && remaining>0;i++){
            int used = Math.min(remaining, slabUnits[i]);
            amount += used * rates[i];
            remaining -= used;
        }
        return amount;
    }
}
