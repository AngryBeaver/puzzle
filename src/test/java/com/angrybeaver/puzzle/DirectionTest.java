package com.angrybeaver.puzzle;

import org.junit.Test;

import static com.angrybeaver.puzzle.Direction.*;
import static org.junit.Assert.*;

public class DirectionTest {

    @Test
    public void getOpposedDirection() {
        assertEquals(North.getOpposedDirection(),South);
        assertEquals(South.getOpposedDirection(),North);
        assertEquals(East.getOpposedDirection(),West);
        assertEquals(West.getOpposedDirection(),East);
    }
}