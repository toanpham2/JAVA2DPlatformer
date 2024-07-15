package main;

import Inputs.KeyboardInputs;
import Inputs.MouseInputs;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import static utils.Constants.PlayerConstants.GetSpriteAmount;
import static utils.Constants.PlayerConstants.*;
import static utils.Constants.Directions.*;


//class for panel to draw inside game window
public class GamePanel extends JPanel {
    private MouseInputs mouseInputs;
    private float xDelta = 100, yDelta = 100;
    private BufferedImage img;
    private BufferedImage[][] animations;
    private int aniTick, aniIndex, aniSpeed = 15;

    //contain current playeraction
    private int playerAction = IDLE;
    private int playerDir = 1;
    private boolean moving = false;

    private void setPanelSize(){
        setMinimumSize(new Dimension(1200, 800));
        setPreferredSize(new Dimension(1200, 800));
    }

    public void setDirection (int direction){
        this.playerDir = direction;
        moving =  true;
    }
    private void loadAnimation(){
        //y and x
        animations = new BufferedImage[9][6];
        for (int j = 0; j < animations.length; j++) {
            for (int i = 0; i < animations[j].length; i++) {
                //img dimension is 64 by 40
                animations[j][i] = img.getSubimage(i * 64, j*40, 64, 40);
            }
        }
    }

    private void updateAnimationTick(){

        aniTick++;
        if(aniTick >= aniSpeed){
            aniTick = 0;
            aniIndex++;
            if(aniIndex >= GetSpriteAmount(playerAction)){
                aniIndex = 0;
            }
        }

    }

    public GamePanel() {
        mouseInputs = new MouseInputs(this);
        setPanelSize();
        importImg();
        loadAnimation();
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);

    }

    private void importImg(){
        InputStream is = getClass().getResourceAsStream("/res/player_sprites.png");
        try{
            img = ImageIO.read(is);
        } catch(IOException e){
            e.printStackTrace();
        }
    }

   public void setMoving(boolean moving){
        this.moving = moving;
   }


    private void setAnimation(){
        if(moving){
            playerAction = RUNNING;
        }
        else{
            playerAction = IDLE;
        }
    }

    private void updatePos(){
        if(moving){
            switch(playerDir){
                case LEFT:
                    xDelta -= 5;
                    break;
                case UP:
                    yDelta -= 5;
                    break;
                case RIGHT:
                    xDelta += 5;
                    break;
                case DOWN:
                    yDelta += 5;
                    break;


            }
        }

    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        updateAnimationTick();
        setAnimation();
        updatePos();
        g.drawImage(animations[playerAction][aniIndex], (int)xDelta, (int)yDelta,128,80, null);


    }


}
