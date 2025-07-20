package testingDates;

import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.stream.Stream;

public class Date
{
    private Month month;
    private int day;
    private int year; //a four digit number.

    public Date( )
    {
        this("January", 1, 1000);  // Could have used setDate instead
    }

    public Date(int monthInt, int day, int year)
    {
        setDate(monthInt, day, year);
    }

    public Date(String monthString, int day, int year)
    {
        setDate(monthString, day, year);
    }

    public Date(int year)
    {
        month = Month.JANUARY;
        day = 1;
        this.year = year;
    }

    public Date(Date aDate)
    {
        if (aDate == null)//Not a real date.
        {
             System.out.println("Fatal Error in Date(Date).");
             System.exit(0);
        }

        month = aDate.month;
        day = aDate.day;
        year = aDate.year;
    }

    public void setDate(int monthInt, int day, int year)
    {
        if (dateOK(monthInt, day, year))
        {
            this.month = monthFromNumber(monthInt);
            this.day = day;
            this.year = year;
        }
        else
        {
            System.out.println("Fatal Error in setDate(int, int, int)");
        }
    }

    public void setDate(String monthString, int day, int year)
    {
    	try {
    		int monthNumber = Month.getByName(monthString).ordinal() + 1;    		
    		setDate(monthNumber, day, year);
    	} catch(NoSuchElementException e) {
    		System.out.println("Fatal Error in setDate(String, int, int)");
    		System.out.println("Invalid month!");
    	}
        
    }

    public void setDate(int year)
    {
        setDate(1, 1, year);
    }

    public void setYear(int year)
    {
        if ( (year < 1000) || (year > 9999) )
        {
            System.out.println("Fatal Error in setYear(int)");
            System.exit(0);
        }
        else
            this.year = year;
    }
    public void setMonth(int monthNumber)
    {
        if ((monthNumber <= 0) || (monthNumber > 12))
        {
            System.out.println("Fatal Error in setMonth(int)");
            System.exit(0);
        }
        else
            month = monthFromNumber(monthNumber);
    }

    public void setDay(int day)
    {
        if ((day <= 0) || (day > 31))
        {
            System.out.println("Fatal Error in setDay(int)");
            System.exit(0);
        }
        else
            this.day = day;
    }

    public int getMonth( )
    {
        return month.ordinal() + 1;
    }

    public int getDay( )
    {
        return day;
    }

    public int getYear( )
    {
        return year;
    }

    public String toString( )
    {
        return (month + " " + day + ", " + year);
    }

    public boolean equals(Date otherDate)
    {
        return ( (month.equals(otherDate.month))
                  && (day == otherDate.day) && (year == otherDate.year) );
    }

    public boolean precedes(Date otherDate)
    {
        return ( (year < otherDate.year) ||
           (year == otherDate.year && getMonth( ) < otherDate.getMonth( )) ||
           (year == otherDate.year && month.equals(otherDate.month)
                                         && day < otherDate.day) );
    }

    public void readInput( )
    {
        boolean tryAgain = true;
        Scanner keyboard = new Scanner(System.in);
        while (tryAgain)
        {
            System.out.println("Enter month, day, and year.");
              System.out.println("Do not use a comma.");
            String monthInput = keyboard.next( );
            int dayInput = keyboard.nextInt( );
            int yearInput = keyboard.nextInt( );
            if (dateOK(monthInput, dayInput, yearInput) )
            {
                setDate(monthInput, dayInput, yearInput);
                tryAgain = false;
            }
            else
                System.out.println("Illegal date. Reenter input.");
         }
        keyboard.close();
    }

    private boolean dateOK(int monthInt, int dayInt, int yearInt)
    {
    	if ((monthInt < 1) || (monthInt > 12)) {
    		return false;
    	}
    	
    	Month targetMonth = monthFromNumber(monthInt);
    	
        return ( (dayInt >= 1) && (dayInt <= targetMonth.days) &&
                 (yearInt >= 1000) && (yearInt <= 9999) );
    }

    private boolean dateOK(String monthString, int dayInt, int yearInt)
    {
    	try {
    		int monthNumber = Month.getByName(monthString).ordinal() + 1;
    		return dateOK(monthNumber, dayInt, yearInt);    		
    	} catch (NoSuchElementException exception) {
    		return false;
    		
    	}
    }

    private Month monthFromNumber(int monthNumber)
    {
        switch (monthNumber)
        {
        case 1:
            return Month.JANUARY;
        case 2:
            return Month.FEBRUARY;
        case 3:
            return Month.MARCH;
        case 4:
            return Month.APRIL;
        case 5:
            return Month.MAY;
        case 6:
            return Month.JUNE;
        case 7:
            return Month.JULY;
        case 8:
            return Month.AUGUST;
        case 9:
            return Month.SEPTEMBER;
        case 10:
            return Month.OCTOBER;
        case 11:
            return Month.NOVEMBER;
        case 12:
        default:
            return Month.DECEMBER;
        }
    }
    public static void main(String[] args) {
        System.out.println("Main in Date.");
        Date tester = new Date();
        System.out.println("tester is "+tester);
    }
    
    public Date addOneDay(){
    	Month newMonth = this.month;
    	int newDay = this.day + 1;
    	int newYear = this.year;
    	if (newDay > this.month.days) {// time for a new month
    		int nextMonth = (this.month.ordinal() + 1);
    		if (nextMonth > 11) { // It's already December, time for a new year
    			nextMonth = nextMonth % 12;
    			newYear++;
    			if (newYear > 9999) {
    				return this; // year would be invalid, don't change the date
    			}
    		}
    		newMonth = Month.values()[nextMonth];
    		newDay = 1;
    	}
    		
	   return new Date(newMonth.name, newDay, newYear);
	}
    
    private enum Month{
    	JANUARY 	("January", 31), 
    	FEBRUARY	("February", 28),
    	MARCH		("March", 31),
    	APRIL		("April", 30),
    	MAY			("May", 31),
    	JUNE		("June", 30),	
    	JULY		("July", 31),
    	AUGUST		("August", 31),
    	SEPTEMBER	("September", 30),
    	OCTOBER		("October", 31),
    	NOVEMBER	("November", 30),
    	DECEMBER	("December", 31);
    	
    	private final String name;
    	
    	private final int days;
    	
    	Month(String name, int days){
    		this.name = name;
    		this.days = days;
    	}
    	
    	public static Month getByName(String name) throws NoSuchElementException {
    		return Stream.of(Month.values())
    		.filter(m-> m.name.toLowerCase().equals(name.toLowerCase()))
    		.findFirst()
    		.orElseThrow();
    	}
    }
    
}
