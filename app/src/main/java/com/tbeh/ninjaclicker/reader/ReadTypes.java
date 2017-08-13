package com.tbeh.ninjaclicker.reader;

public class ReadTypes {

    public enum Count {
        ONE(1), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), NINE(9), TEN(10);

        private int index;

        Count(int index) {
            this.index = index;
        }

        public int getIndex() {
            return index;
        }
    }

    public enum Operation {
        ADD, REMOVE, REMOVE_ALL, SET, CLEAR;
    }

    public enum GameObject {
        GAME_TIMER;
    }

    // TODO: Add functional interface for support of swapping goals
    public enum Goal {
        REMOVE;
    }

}
