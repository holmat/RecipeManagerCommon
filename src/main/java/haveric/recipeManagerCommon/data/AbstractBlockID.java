package haveric.recipeManagerCommon.data;

import java.util.UUID;

import org.apache.commons.lang3.Validate;

public class AbstractBlockID {
    private transient int hash;

    protected UUID wid;
    protected int x;
    protected int y;
    protected int z;

    public AbstractBlockID() { }

    /**
     * @param id
     * @param coords
     * @throws IllegalArgumentException
     *             if coordinate string isn't valid or id is null
     */
    public AbstractBlockID(UUID id, String coords) {
        Validate.notNull(id, "id argument must not be null!");
        Validate.notNull(coords, "coords argument must not be null!");

        wid = id;

        try {
            String[] s = coords.split(",", 3);

            x = Integer.parseInt(s[0]);
            y = Integer.parseInt(s[1]);
            z = Integer.parseInt(s[2]);
        } catch (Throwable e) {
            throw new IllegalArgumentException("Coords argument must have 3 numbers separated by commas!");
        }

        buildHash();
    }

    protected void buildHash() {
        hash = (wid.toString() + ":" + x + ":" + y + ":" + z + ":").hashCode();
    }

    public UUID getWorldID() {
        return wid;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }


    /**
     * @return coordinates in x,y,z format string
     */
    public String getCoordsString() {
        return x + "," + y + "," + z;
    }

    @Override
    public int hashCode() {
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (!(obj instanceof AbstractBlockID)) {
            return false;
        }

        AbstractBlockID b = (AbstractBlockID) obj;

        return (b.x == x && b.y == y && b.z == z && b.wid.equals(wid));
    }
}
