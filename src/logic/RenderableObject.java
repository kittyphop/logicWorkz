package logic;

public abstract class RenderableObject implements IRenderable {

	protected int x, y, z, movingDelayCounter;
	protected boolean destroyed;

	public int getZ() {
		return z;
	}

	public void setDestroyed(boolean destroyed) {
		this.destroyed = destroyed;
	}

	public boolean isDestroyed() {
		return destroyed;
	}

}
