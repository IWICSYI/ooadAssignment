package controllerClasses;

import misc.ObjectContainer;

public class MiscControl {

	public ObjectContainer idPairerWithName(int i, int id, String name)
	{
		ObjectContainer pair=new ObjectContainer(i,id,name);
		return pair;
	}
	
	public ObjectContainer idPairerWithMovieLength(int i, int id, int len)
	{
		ObjectContainer pair=new ObjectContainer(i,id,len);
		return pair;
	}
}
