package org.boatpos.service.core.util;

import org.boatpos.repository.api.values.Day;
import org.boatpos.repository.api.values.DayId;
import org.boatpos.repository.core.model.RentalCore;
import org.boatpos.service.api.bean.RentalBean;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;

@RunWith(Arquillian.class)
public class MyRentalCryptTest {

    @Inject
    private MyRentalCrypt myRentalCrypt;

    @Test
    public void encrypt() throws Exception {
        assertEquals("IMkdD/ddNWPL4kq4WejIkg==", myRentalCrypt.encrypt("test"));
        assertEquals("/XJDKtT2DW/+H58/LZhpSA==", myRentalCrypt.encrypt("20150701-0001"));
    }

    @Test
    public void encryptHtmlSafe() throws Exception {
        assertEquals("IMkdD%2FddNWPL4kq4WejIkg%3D%3D", myRentalCrypt.encryptHtmlSafe("test"));
        assertEquals("%2FXJDKtT2DW%2F%2BH58%2FLZhpSA%3D%3D", myRentalCrypt.encryptHtmlSafe("20150701-0001"));
    }

    @Test
    public void encryptRental() throws Exception {
        assertEquals("GwLtkrFX3ehALyQoR1I8bg==", myRentalCrypt.encrypt(new RentalCore(new RentalBean()).setDay(new Day(2015, 7, 1)).setDayId(new DayId(1))));
        assertEquals("2015-07-01_0001", myRentalCrypt.decrypt("GwLtkrFX3ehALyQoR1I8bg=="));
    }

    @Test
    public void encryptHtmlSafeRental() throws Exception {
        assertEquals("GwLtkrFX3ehALyQoR1I8bg%3D%3D", myRentalCrypt.encryptHtmlSafe(new RentalCore(new RentalBean()).setDay(new Day(2015, 7, 1)).setDayId(new DayId(1))));
    }

    @Test
    public void decrypt() throws Exception {
        assertEquals("test", myRentalCrypt.decrypt("IMkdD/ddNWPL4kq4WejIkg=="));
        assertEquals("20150701-0001", myRentalCrypt.decrypt("/XJDKtT2DW/+H58/LZhpSA=="));
    }

    @Test
    public void decryptHtmlSafe() throws Exception {
        assertEquals("test", myRentalCrypt.decryptHtmlSafe("IMkdD%2FddNWPL4kq4WejIkg%3D%3D"));
        assertEquals("20150701-0001", myRentalCrypt.decryptHtmlSafe("%2FXJDKtT2DW%2F%2BH58%2FLZhpSA%3D%3D"));
    }
}