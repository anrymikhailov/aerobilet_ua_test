package ua.aerobilet.entities;

import java.util.Random;

public class SearchFormData {
	public static SearchForm searchUAFormOW = new SearchForm()
			{
		{
			airportFrom ="KIEV";
			airportReturn ="PARIS";
			dataFromMonth ="Серпень";
			dataFromYear = "2018";
			// choose random day, because we need make different reservation
			Random r = new Random();
			dataFromDay = Integer.toString(r.nextInt(30-1)+1);
		}
			};
			
	public static SearchForm searchRUFormOW = new SearchForm()
			{
		{
			airportFrom ="MOSCOW";
			airportReturn ="New York";
			dataFromMonth ="Апрель";
			dataFromYear = "2019";
			// choose random day, because we need make different reservation
			Random r = new Random();
			dataFromDay = Integer.toString(r.nextInt(30-1)+1);
		}
			};
	public static SearchForm searchRUFormRT = new SearchForm()
			{
		{
			airportFrom ="MOSCOW";
			airportReturn ="PARIS";
			dataFromMonth ="Апрель";
			dataFromYear = "2019";
			// choose random day, because we need make different reservation
			Random r = new Random();
			dataFromDay = Integer.toString(r.nextInt(30-1)+1);
		}
			};
}
