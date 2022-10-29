package uet.oop.bomberman.controllers;

public class AudioController {
    public enum AudioType {
        GAME_BGM, BOMB_DESTROY, BOMB_PLANTED, ENEMY_DIE, ITEM_COLLECTED,
        MENU_START, STAGE_START, PLAYER_DIE, PLAYER_WIN, PLAYER_LOSE, PLAYER_MOVING_HORIZONTAL, PLAYER_MOVING_VERTICAL, SIZE
    }

    private static final AudioController instance = new AudioController();
    private final Audio[] audioList = new Audio[AudioType.SIZE.ordinal()];

    public static AudioController getInstance() {
        return instance;
    }

    private AudioController() {
        audioList[AudioType.GAME_BGM.ordinal()] = new Audio("game_bgm");
        audioList[AudioType.BOMB_PLANTED.ordinal()] = new Audio("bomb_planted");
        audioList[AudioType.BOMB_DESTROY.ordinal()] = new Audio("bomb_destroy");
        audioList[AudioType.ENEMY_DIE.ordinal()] = new Audio("enemy_die");
        audioList[AudioType.ITEM_COLLECTED.ordinal()] = new Audio("item_collected");
        audioList[AudioType.MENU_START.ordinal()] = new Audio("menu_start");
        audioList[AudioType.PLAYER_DIE.ordinal()] = new Audio("player_die");
        audioList[AudioType.PLAYER_WIN.ordinal()] = new Audio("player_win");
        audioList[AudioType.PLAYER_LOSE.ordinal()] = new Audio("player_lose");
        audioList[AudioType.STAGE_START.ordinal()] = new Audio("stage_start");
        audioList[AudioType.PLAYER_MOVING_HORIZONTAL.ordinal()] = new Audio("player_moving_horizontal");
        audioList[AudioType.PLAYER_MOVING_VERTICAL.ordinal()] = new Audio("player_moving_vertical");
    }

    public void playBgm() {
        audioList[AudioType.GAME_BGM.ordinal()].playBgm();
    }

    public void stopBgm() {
        audioList[AudioType.GAME_BGM.ordinal()].stopBgm();
    }

    public void restartBgm() {
        audioList[AudioType.GAME_BGM.ordinal()].restartBgm();
    }

    public void playSoundEffect(AudioType audioType) {
        audioList[audioType.ordinal()].playSoundEffect();
    }
    public boolean isPlaying(AudioType audioType){
        return audioList[audioType.ordinal()].isPlaying();
    }
}
