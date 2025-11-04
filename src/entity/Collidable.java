package entity;

public interface Collidable {
	/** Action when the object has collision with other objects **/
	void onCollision(Collidable other);
}
