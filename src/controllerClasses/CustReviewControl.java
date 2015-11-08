package controllerClasses;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

import boundaryClasses.CustMovieDetailUi;
import data.Review;
import dataController.ReviewDataControl;

public class CustReviewControl {

	
	public static void retrieveReviewList(int movieId, int listingId, String type, int plat) throws IOException, ParseException
	{
		ArrayList<Review> review= new ArrayList<Review>();
		review=ReviewDataControl.readReview();
		int count=0;
		
		for(int i=0;i<review.size();i++)
		{
			if(movieId == review.get(i).getMovieId())
			{
				review.get(i).getString();
				count++;
			}
		}
		
		if(count==0)
		{
			System.out.println("No Reviews Yet!");
		}
		System.out.println("\nReady to resume?");
		Scanner sc=new Scanner(System.in);
		sc.nextLine();
		CustMovieDetailUi.displayMovieDetails( movieId,  listingId, type,  plat);
	}
	
	
}
