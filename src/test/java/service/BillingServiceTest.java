package service;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BillingServiceTest {
    @Test
    void testSlab100() {
        BillingService s = new BillingService();
        assertEquals(2000.0, s.calculateAmount(100), 0.001);
    }
    @Test
    void testSlab150() {
        BillingService s = new BillingService();
        // 100*20 + 50*30 = 2000+1500 = 3500
        assertEquals(3500.0, s.calculateAmount(150), 0.001);
    }
    @Test
    void testSlab250() {
        BillingService s = new BillingService();
        // 100*20 + 100*30 + 50*45 = 2000+3000+2250 = 7250
        assertEquals(7250.0, s.calculateAmount(250), 0.001);
    }
}
