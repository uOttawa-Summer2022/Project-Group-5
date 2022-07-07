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
    public void validSessionCheck() {
        int startMin = 30, startHour = 8, endMin = 45, endHour = 10;
        assertTrue(validator.validSession(startHour,startMin, endHour, endMin));
        startMin = 300;
        startHour = 1000;
        assertFalse(validator.validSession(startHour,startMin, endHour, endMin));
    }
}