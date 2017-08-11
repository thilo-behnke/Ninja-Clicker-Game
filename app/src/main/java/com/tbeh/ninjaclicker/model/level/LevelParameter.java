package com.tbeh.ninjaclicker.model.level;

public enum LevelParameter {

    DIFFICULTY_EASY("DIFFICULTY_EASY", Type.DIFFICULTY),
    SPRITE_COUNT("SPRITE_COUNT", Type.RANDOM),
    GOAL_KILL_ALL("KILL_ALL", Type.GOAL),
    GOAL_SURVIVE("SURVIVE", Type.GOAL),
    TIMER_ON("TIMER_ON", Type.TIMER),
    TIMER_OFF("TIMER_OFF", Type.TIMER),

    CHARACTER_LUIGI("LUIGI", Type.CHARACTER),
    CHARACTER_MARIO("Mario", Type.CHARACTER),
    CHARACTER_PEACH("Peach", Type.CHARACTER);

    public enum Type {
        DIFFICULTY(Group.SETTINGS, "DIFFICULTY"),
        RANDOM(Group.SETTINGS, "RANDOM"),
        GOAL(Group.SETTINGS, "GOAL"),
        TIMER(Group.SETTINGS, "TIMER"),
        CHARACTER(Group.SPRITES, "CHARACTER");

        private Group group;
        private String name;

        Type(Group group, String name) {
            this.group = group;
            this.name = name;
        }

        public String getName() {
            return this.name;
        }
        public Group getGroup(){
            return this.group;
        }

        public enum Group {
            SETTINGS("SETTINGS"),
            SPRITES("SPRITES");

            private String name;

            Group(String name) {
                this.name = name;
            }

            public String getName() {
                return this.name;
            }
        }
    }

    private String name;
    private Type type;

    LevelParameter(String name, Type type){
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return this.name;
    }
    public Type getType(){
        return this.type;
    }
    public Type.Group getGroup(){
        return this.type.getGroup();
    }
}
