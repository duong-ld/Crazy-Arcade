package objgame.character;

import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import game.constant.Constain;
import game.constant.Direction;

public class Monster extends Bomber implements Runnable {

    public Monster(int x, int y) {
        super(x, y);
        // this.isLiving = false;
        new Thread(this).start();
        //TODO Auto-generated constructor stub
    }

    public void autoMove() {
        if (!this.isLiving) {
            return;
        }
        Random rand = new Random();
        int num = rand.nextInt(100);
        // 30% turn back
        if (num < 30) { 
            // sum is always 3
            this.move(3-getOldDirection());
            return;
        }
        // 40% turn right or left
        if (num < 70) {
            int direction = rand.nextInt(2);
            switch(getOldDirection()) {
                case Direction.DOWN: 
                case Direction.UP: {
                    if (direction == 0) {
                        this.move(Direction.LEFT);
                    } else {
                        this.move(Direction.RIGHT);
                    }
                    break;
                }
                case Direction.LEFT:
                case Direction.RIGHT: {
                    if (direction == 0) {
                        this.move(Direction.UP);
                    } else {
                        this.move(Direction.DOWN);
                    }
                    break;
                }
            }
            return;
        }

        // 30% go straight
        this.move(getOldDirection());
    }

    @Override
	public void initImage() {
        Random rand = new Random();
        int num = rand.nextInt(4)+1;
        try {
            images[Direction.UP] = ImageIO.read(new File("src/images/monster/m"+num+"/m4.png"));
            images[Direction.DOWN] = ImageIO.read(new File("src/images/monster/m"+num+"/m1.png"));
            images[Direction.LEFT] = ImageIO.read(new File("src/images/monster/m"+num+"/m2.png"));
            images[Direction.RIGHT] = ImageIO.read(new File("src/images/monster/m"+num+"/m3.png"));
		} catch (IOException e) {
	          e.printStackTrace();
		}
		setImage(images[Direction.DOWN]);
        setOldDirection(Direction.DOWN);
	}

    @Override
    public void run() {
        // TODO Auto-generated method stub
        while(true) {
            try {
                Thread.sleep(Constain.TIME_MOVE_MONSTER);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            autoMove();
        }
    }
    
}
