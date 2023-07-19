
public class alien extends spaceObject{
	private String type;
	
	public alien(int x, int y) {
		setHp(6);
		setX(x);
		setY(y);
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}

	@Override
	void moveRight() {
		// TODO Auto-generated method stub
		
	}

	@Override
	void moveLeft() {
		// TODO Auto-generated method stub
		
	}

	@Override
	void moveUp() {
		// TODO Auto-generated method stub
		
	}

	@Override
	void moveDown() {
		y++;
	}
}
