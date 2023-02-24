package tile;

import Main.GamePan;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class tileManager {

    GamePan gp;
    tile[] tile;
    int mapTileNum[][];

    public tileManager(GamePan gp) {
        this.gp = gp;
        tile = new tile[3];
        mapTileNum = new int[gp.maxWorldCol] [gp.maxWorldRow];

        getTileImage();
        loadMap("/Worlds/World1.txt");
    }

    public void getTileImage() {
        try {

            tile[0] = new tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/Sky1.png"));

            tile[1] = new tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/Floor1.png"));

            tile[2] = new tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/Dirt1.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap(String filePath) {

        try {
            InputStream is = getClass().getResourceAsStream("/Worlds/World1.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while(col < gp.maxWorldCol && row < gp.maxWorldRow) {

                String line = br.readLine();
                while(col < gp.maxWorldCol) {

                    String numbers [] = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;
                    col++;
                }
                if (col == gp.maxWorldCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();
        } catch (Exception e) {
        }
    }
        public void draw (Graphics2D g2){

            int col = 0;
            int row = 0;
            int x = 0;
            int y = 15;

            while (col < gp.maxWorldCol && row < gp.maxWorldRow) {

                int tileNum = mapTileNum[col][row];

                g2.drawImage(tile[tileNum].image, x, y, gp.pixSize, gp.pixSize, null);
                col++;
                x += gp.pixSize;

                if (col == gp.maxWorldCol) {
                    col = 0;
                    x = 0;
                    row++;
                    y += gp.pixSize;
                }
            }
        }
    }
