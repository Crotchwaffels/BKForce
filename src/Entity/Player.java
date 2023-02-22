package Entity;

import Main.GamePan;
import Main.Keys;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity{

    GamePan gp;
    Keys keyH;

    public final int screenX;
    public final int screenY;
    public Player(GamePan gp, Keys keyH) {

        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth/2 - (gp.pixSize/2);
        screenY = gp.screenHeight/2 - (gp.pixSize/2);

        setDefaultValues();
        getPlayerImage();
    }
    public void setDefaultValues() {

        worldX = gp.pixSize * 3;
        worldY = gp.pixSize * 16;
        speed = 6;
        direction = "right";
    }
    public void getPlayerImage() {

        try{

            left1 = ImageIO.read(getClass().getResourceAsStream("/player/WopyLeft1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/WopyLeft2.png"));
            left3 = ImageIO.read(getClass().getResourceAsStream("/player/WopyLeft3.png"));
            left4 = ImageIO.read(getClass().getResourceAsStream("/player/WopyLeft4.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/WopyRight1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/WopyRight2.png"));
            right3 = ImageIO.read(getClass().getResourceAsStream("/player/WopyRight3.png"));
            right4 = ImageIO.read(getClass().getResourceAsStream("/player/WopyRight4.png"));

        }catch(IOException e) {
            e.printStackTrace();
        }
    }
    public void update() {

        if(keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {

            if(keyH.upPressed) {
                direction = "up";
                worldY -= speed;
            }
            else if(keyH.downPressed) {
                direction = "down";
                worldY += speed;
            }
            else if(keyH.leftPressed) {
                direction = "left";
                worldX -= speed;
            }
            else if(keyH.rightPressed) {
                direction = "right";
                worldX += speed;
            }

            spriteCounter++;
            if(spriteCounter > 4) {
                if(spriteNum == 1) {
                    spriteNum = 2;
                }
                else if(spriteNum == 2) {
                    spriteNum = 3;
                }
                else if(spriteNum == 3) {
                    spriteNum = 4;
                }
                else if(spriteNum == 4) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
    }
    public void draw(Graphics2D g2) {

        BufferedImage image = null;

        switch(direction) {
            case "up":
                  image = right1;
                break;
            case "down":
               image = right1;
               break;
            case "left":
                if(spriteNum == 1) {
                    image = left1;
                }
                if(spriteNum == 2) {
                    image = left2;
                }
                if(spriteNum == 3) {
                    image = left3;
                }
                if(spriteNum == 4) {
                    image = left4;
                }
                break;
            case "right":
                if(spriteNum == 1) {
                    image = right1;
                }
                if(spriteNum == 2) {
                    image = right2;
                }
                if(spriteNum == 3) {
                    image = right3;
                }
                if(spriteNum == 4) {
                    image = right4;
                }
                break;
        }
        g2.drawImage(image, screenX, screenY, gp.pixSize, gp.pixSize, null);
    }
}
