
public class spaceShip extends spaceObject {
	public spaceShip() {
		setX(375);
		setY(600);
		setHp(3);
	}

	@Override
	void moveRight() {
		setX(this.getX() + 10);
	}


	@Override
	void moveLeft() {
		setX(this.getX() - 10);
	}

	@Override
	void moveUp() {
		setY(this.getY() - 10);
	}

	@Override
	void moveDown() {
		setY(this.getY() + 10);
	}
}
