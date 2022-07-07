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
    }

    @Test
    public void validCrsCode_false() {
        String crsCode = "CEG 2100";
        assertFalse(validator.validCrsCode(crsCode));
    }
}