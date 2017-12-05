package de.magicced01.myclasses;

public enum Classes {
	SCOUT("scout"),
	TANK("tank");
	
	String classname;
	
	private Classes(String classname)
	{
		this.classname = classname;
	}
	public String getClassName()
	{
		return this.classname;
	}
}
