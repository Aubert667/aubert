package model;
import java.awt.Image;
    
/**
 * An item that is able to return an image of itself.
 * 
 * @author EMAKO TIENTCHEU
 * @version 2011.07.31
 */

public interface DrawableItem extends Item
{
    public Image getImage();
}
