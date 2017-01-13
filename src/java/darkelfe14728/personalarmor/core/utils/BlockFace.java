package darkelfe14728.personalarmor.core.utils;

/**
 * @author Julien Rosset
 * 
 *         Enumerate blocks faces.
 */
public enum BlockFace
{
        BOTTOM(0),
        TOP(1),
        NORTH(2),
        SOUTH(3),
        WEST(4),
        EAST(5);

    private int face;

    BlockFace(int face)
    {
        this.face = face;
    }

    public int getFace()
    {
        return this.face;
    }

    public boolean equals(int face)
    {
        return(this.face == face);
    }
}
