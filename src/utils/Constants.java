package utils;

public class Constants {


    public static class Directions{
        public static final int LEFT = 0;
        public static final int UP = 1;
        public static final int RIGHT = 2;
        public static final int DOWN = 3;
    }


    public static class PlayerConstants{
        public static final int RUNNING = 1;
        public static final int IDLE = 0;
        public static final int JUMP = 2;
        public static final int FALLING = 3;
        public static final int GROUND = 4;
        public static final int HIT = 5;
        public static final int ATTACK_1 = 6;
        public static final int ATTACK_JUMP_1 = 7;
        public static final int ATTACK_JUMP_2 = 8;

        public static int GetSpriteAmount(int player_action) {
            int ret = 0;
            if(player_action == RUNNING){
                ret = 6;
            }
            else if(player_action == IDLE){
                ret = 5;
            }
            else if(player_action == JUMP){
                ret = 3;
            }
            else if(player_action == FALLING){
                ret = 1;
            }
            else if(player_action == GROUND){
                ret = 5;
            }
            else if(player_action == HIT){
                ret = 4;
            }
            else if (player_action == ATTACK_1){
                ret = 3;
            }
            else if (player_action == ATTACK_JUMP_1){
                ret = 3;
            }
            else if (player_action == ATTACK_JUMP_2){
                ret = 3;
            }
            else{
                ret = 1;
            }
            return ret;
        }
    }
}