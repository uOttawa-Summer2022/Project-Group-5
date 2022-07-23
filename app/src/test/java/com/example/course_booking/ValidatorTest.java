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

    @Test
    public void validDay_true() {
        assertTrue(validator.validDay("MONDAY"));
    }

    @Test
    public void validDay_false() {
        assertFalse(validator.validDay("SOMEDAY"));
    }

    @Test
    public void validTime_true() {
        Session s1 = new Session(Day.MONDAY, 8, 00, 9, 50);
        Session s2 = new Session(Day.TUESDAY, 10, 00, 11, 20);

        assertTrue(validator.validTime(s1, s2));
    }

    @Test
    public void validTime_false() {
        Session s1 = new Session(Day.MONDAY, 8, 00, 11, 00);
        Session s2 = new Session(Day.MONDAY, 9, 30, 11, 50);

        assertFalse(validator.validTime(s1, s2));
    }

    @Test
    public void validCapacity_true() {
        CourseModel cm = new CourseModel();
        cm.setCrsCapacity(10);

        assertTrue(validator.validCapacity(cm));
    }

    @Test
    public void validCapacity_false() {
        CourseModel cm = new CourseModel();
        cm.setCrsCapacity(0);

        assertFalse(validator.validCapacity(cm));
    }
}