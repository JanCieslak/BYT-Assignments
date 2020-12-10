package edu.ncsu.monopoly;

public class GoCell extends Cell {
	private boolean available = true;

	public GoCell() {
		super.setName("Go");
		setAvailable(false);
	}

	public boolean playAction(String msg) {
	}
	
	void setName(String name) {
	}
}
