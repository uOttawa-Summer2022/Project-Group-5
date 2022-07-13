package com.example.course_booking;

import static org.junit.Assert.*;

import org.junit.Test;

public class ValidatorTest {

    Validator validator = new Validator();

    @Test
    public void validUsername_true() {
        String username = "John";
        assertTrue(validator.validUsername(username));
    }

    @Test
    public void validUsername_false() {
        String username = "Minecraft_Man0707";
        assertFalse(validator.validUsername(username));
    }

    @Test
    public void validCrsCode_true() {
        String crsCode = "SEG2105";
        assertTrue(validator.validCrsCode(crsCode));
        crsCode = "CEG2136";
        assertTrue(validator.validCrsCode(crsCode));
    }

    @Test
    public void validCrsCode_false() {
        String crsCode = "CEG 2100";
        assertFalse(validator.validCrsCode(crsCode));
        crsCode = "CEG/2136";
        assertFalse(validator.validCrsCode(crsCode));
        crsCode = "ceg2136";
        assertFalse(validator.validCrsCode(crsCode));
        crsCode = "CEG2";
        assertFalse(validator.validCrsCode(crsCode));
        crsCode = "SE2105";
        assertFalse(validator.validCrsCode(crsCode));
    }

    @Test
    public void validSessionCheck_true() {
        int startHour = 8, startMin = 30, endHour = 10, endMin = 45;
        assertTrue(validator.validSession(startHour,startMin, endHour, endMin));
    }

    @Test
    public void validSessionCheck_false() {
        int startHour = 1000, startMin = 300, endHour = 10, endMin = 45;
        assertFalse(validator.validSession(startHour,startMin, endHour, endMin));
    }
}