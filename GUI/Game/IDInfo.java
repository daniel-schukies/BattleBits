package GUI.Game;
/**
 * Speichert Informationen zu einem zugeordneten Objekt
 * @author Daniel Schukies, Sebastian Junger
 *
 */
public class IDInfo 
{
	private int ID;
	private boolean isPressed;
	
	/**
	 * Ertstellt eine IDInfo und gibt dem zugeordneten Objekt die uebergebene ID
	 * @param ID ID des zugeordneten Objekts
	 */
	public IDInfo(int ID)
	{
		this.ID = ID;
		this.isPressed = true;
	}
	
	/**
	 * Ertstellt eine IDInfo und gibt dem zugeordneten Objekt die ID 0
	 */
	public IDInfo()
	{
		this.ID = 0;
		this.isPressed = false;
	}
	
	/**
	 * Gibt die ID des Objektes zurueck
	 * @return ID des Objektes
	 */
	public int getID()
	{
		return this.ID;
	}
	
	/**
	 * Gibt zurueck, ob die isPressed Variable des zugeordneten Objektes angeklickt wurde
	 * @return Gibt zurueck, ob Objekt angeklickt wurde
	 */
	public boolean getIsPressed()
	{
		return this.isPressed;
	}
	
	/**
	 * Setzt die ID des Objektes
	 * @param ID zu setzende ID
	 */
	public void setID(int ID)
	{
		this.ID = ID;
	}
	
	/**
	 * Setzt den Inhalt der isPressed Variable
	 * @param isPressed gibt an, ob das Objekt angeklickt wurde
	 */
	public void setIsPressed(boolean isPressed)
	{
		this.isPressed = isPressed;
	}
}
