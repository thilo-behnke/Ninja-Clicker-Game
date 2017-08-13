package com.tbeh.ninjaclicker.reader;

import java.util.List;

// TODO: Move to other place
public class WorldObjects {

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
        GAME_TIMER, GOAL;

    }

    // TODO: Add functional interface for support of swapping goals
    public enum Goal {
        KILL_CERTAIN(ListAttributes.class), KILL_ALL(null), SCORE(IntAttributes.class);

        private Class clazz;

        Goal(Class clazz){
            this.clazz = clazz;
        }

        public Class getAttributeType() {
            return clazz;
        }
    }



    public static interface Attributes {}

    public class IntAttributes implements Attributes{

        private int attribute;

        public IntAttributes(int attribute) {
            this.attribute = attribute;
        }
    }

    public class ListAttributes implements Attributes{

        private List<Enum> enumList;

        public ListAttributes(List<Enum> enumList) {
            this.enumList = enumList;
        }
    }

}
