package de.magicced01.myclasses;

public enum Messages {
	PREFIX("§6§l[MyClasses]§r"),
	CLASS("§6§l[MyClasses]§r Du folgende Klasse ausgewählt: "),
	NOPERM("§6§l[MyClasses]§r Du hast keine Rechte diesen Befehl auszuführen"),
	ARENAJOIN("§6§l[MyClasses]§r Du hast die Arena betreten"),
	ARENALEAVE("§6§l[MyClasses]§r Du hast die Arena verlassen"),
	ARENA_JOINED("§6§l[MyClasses]§r Du bist bereits in der Arena"),
	NOT_IN_ARENA("§6§l[MyClasses]§r Du bist nicht in der Arena. Gib zunächst /classarena join ein"),
	SPAWN_SET("§6§l[MyClasses]§r Du hast erfolgreich den Spawnpunkt gesetzt"),
	NO_DROP("§6§l[MyClasses]§r Du darfst hier nichts droppen"),
	NO_BREAK("§6§l[MyClasses]§r Du darfst hier nicht abbauen"),
	NO_PLACE("§6§l[MyClasses]§r Du darfst hier nicht bauen"),
	BLUE("§6§l[MyClasses]§r Du bist Team Blau beigetreten"),
	RED("§6§l[MyClasses]§r Du bist Team Rot beigetreten");

	
	
	String description;
	
	private Messages(String description)
	{
		this.description = description;
	}
	public String getDescription()
	{
		return this.description;
	}

}
