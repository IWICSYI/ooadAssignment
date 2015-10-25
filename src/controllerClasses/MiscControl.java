package controllerClasses;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import data.Movie;
import data.ShowTime;
import misc.ObjectContainer;

public class MiscControl {

	private ArrayList<String> stringList = new ArrayList<String>();
	

	public static ArrayList<Integer> getUniqueInteger(ArrayList<Integer> a){
		 Set<Integer> set = new HashSet<Integer>();
		 ArrayList<Integer> uniqueList=new ArrayList<Integer>();
		 for(int i=0;i<a.size();i++)
		 {
			 set.add(a.get(i));
		 }
		Object[] intListObj = set.toArray();
		for(int i=0;i<intListObj.length;i++){
			uniqueList.add(Integer.parseInt(intListObj[i].toString().trim()));
		}
		 
		 return uniqueList;
		
		
	}
	
	public static ObjectContainer idPairerWithCinema(int i, int id, String name, int cineType, int seatNo)
	{
		ObjectContainer pair=new ObjectContainer(i,id,name,cineType,seatNo);
		return pair;
	}
	
	public static ObjectContainer idPairerWithShowTime(int i, int dayOfWeek,
			ArrayList<String> showTimeArray, ArrayList<Integer> showTimeId) {
	
		ObjectContainer pair=new ObjectContainer(i,dayOfWeek,showTimeArray,showTimeId);
		return pair;
	}
	
	public static ObjectContainer idPairerWithName(int i, int id, String name)
	{
		ObjectContainer pair=new ObjectContainer(i,id,name);
		return pair;
	}
	
	public static ObjectContainer idPairerWithMovieLength(int i, int id, int len, int movieType)
	{
		ObjectContainer pair=new ObjectContainer(i,id,len,movieType);
		return pair;
	}
	
	public static ObjectContainer idPairerWithMovie(int i, Movie m)
	{
		ObjectContainer pair=new ObjectContainer(i,m);
		return pair;
	}
	
	
	
	
	 public void sort()
	    {
	 
	        stringList=mergeSort(stringList);
	 
	    }
	
    public ArrayList<String> mergeSort(ArrayList<String> stringList2)
    {
        ArrayList<String> left = new ArrayList<String>();
        ArrayList<String> right = new ArrayList<String>();
        int middle;
 
        if(stringList2.size()==1)    
            return stringList2;
        else
        {
            middle = stringList2.size()/2;
            // copy the left half of stringList into the left.
            for(int i=0; i<middle; i++)
            {
                    left.add(stringList2.get(i));
            }
 
            //copy the right half of stringList into the new arraylist.
            for(int i=middle; i<stringList2.size(); i++)
            {
                    right.add(stringList2.get(i));
            }
 
            // Sort the left and right halves of the arraylist.
            left  = mergeSort(left);
            right = mergeSort(right);
 
 
            // Merge the results back together.
            merge(left,right,stringList2);
 
        }
        return stringList2;
    }
 
    private  void merge(ArrayList<String> left, ArrayList<String> right, 
            ArrayList<String> stringList) {
 
        int leftIndex = 0;
        int rightIndex = 0;
        int intListIndex = 0;
 
 
        // As long as neither the left nor the right arraylist has
        // been used up, keep taking the smaller of left.get(leftIndex)
        // or right.get(rightIndex) and adding it at both.get(bothIndex).
        while (leftIndex < left.size() && rightIndex < right.size())
        {
        	
            if ((left.get(leftIndex).compareTo(right.get(rightIndex)))<0) 
            {
                stringList.set(intListIndex,left.get(leftIndex));
                leftIndex++;
               
            }
            else
            {
                stringList.set(intListIndex, right.get(rightIndex));
                rightIndex++;
               
                
            }
            intListIndex++;
        }
 
        ArrayList<String>rest;
        int restIndex;
        if (leftIndex >= left.size()) {
        	
            rest = right;
            restIndex = rightIndex;
        }
        else {
           
            rest = left;
            restIndex = leftIndex;
        }
 
        // Copy the rest of whichever arraylist (left or right) was
        // not used up.
        for (int i=restIndex; i<rest.size(); i++) {
            stringList.set(intListIndex, rest.get(i));
            intListIndex++;
        }
    }

	public ArrayList<String> getStringList() {
		return stringList;
	}

	public void setStringList(ArrayList<String> stringList) {
		this.stringList = stringList;
	}

	
	
}
