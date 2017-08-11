package com.tbeh.ninjaclicker.collision;

public class CollisionDetector {

    private static CollisionDetector instance;

    private CollisionDetection collisionDetection;

    public CollisionDetector(CollisionDetection collisionDetection){
        this.collisionDetection = collisionDetection;
    }

    public static CollisionDetector getInstance(CollisionDetection collisionDetection){
        if(instance==null){
            instance = new CollisionDetector(collisionDetection);
        }
        return instance;
    }

    public void detectCollision(int touchX, int touchY){
        collisionDetection.detectCollision(touchX, touchY);
    };

    public void changeStrategy(CollisionDetection collisionDetection){
        this.collisionDetection = collisionDetection;
    }

    public CollisionDetection getStrategy(){
        return collisionDetection;
    }

}
