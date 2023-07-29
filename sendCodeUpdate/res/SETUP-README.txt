Run the jar file by giving 0 for text based and 1 for GUI based

STRUCTURE ( The files should be in the following format )
* The folder should contain a data folder that has the tickerName.txt file and in the same folder
    we store the jar file as well.
    - data(folder)
        - tickerName.txt
    - StockMarket.jar


Supports some 10000 stock names, names should be given in uppercase.

Listing few of the stocks for ease of user
    [ AAPL, BAC, AMZN, T, GOOG, MO, DAL, AA, AXP, DD, BABA, ABT, UA  ]


Sample Example of how the flow goes for the given situation :

To create a portfolio with 3 different stocks, a second portfolio with 2 different stocks and query
their value on a specific date. We will run your program with this data to begin grading. You should
 also include a list of stocks that your program supports, along with dates on which its value can
 be determined (if there are restrictions in your program about which data is available)

Inputs that should be given

    - 2 : Select new user
    - <name> : Enter a name ( should be a name that is not there already because you selected create
                a new user ).  ( Only alphabets )
    - 1 : Create a portfolio
    - <portfolio name> : any name ( should contain only alphabets )
    - 1 : Create stock {3 times}
        - <StockName> : Name of the stock.
        - <Quantity> : Number of stocks you want to buy. (Integer else it asks to enter integer)
    - 2 : Exit stock creation ( goes back to initial screen that comes after user logs in ).
    - 3 : Goes into evaluation mode
        - <portfolio Name> : name of portfolio already created, keeps asking till you provide
                                existing portfolio.
        - <date> : Any date in the previous years till the stock started working, does not have a
                    check to see if entered date is future. ( testing with 2022-10-24 )

    { Same way it can be carried out for another portfolio by providing different portfolio name }





Sample code run for the same scenario output

/Library/Java/JavaVirtualMachines/jdk-11.0.12.jdk/Contents/Home/bin/java -javaagent:/Applications/IntelliJ IDEA.app/Contents/lib/idea_rt.jar=60319:/Applications/IntelliJ IDEA.app/Contents/bin -Dfile.encoding=UTF-8 -classpath /Users/histravelstories/PDP/StockMarket/out/production/StockMarket MVCStockMarket
Welcome to the Stock Exchange
Press 1 : Existing User
Press 2 : New User
Press 3 : Exit
1
Welcome to stock world, need couple of information before you can start buying
Please Enter Username
Aish
Successfully logged in Aish
Please select one of the options
Press 1 to create new Portfolio
Press 2 to examine composition of a portfolio
Press 3 to determine total value of a portfolio on a certain date
Press 4 to edit portfolio
Press 5 to determine cost basis of portfolio
Press 6 to see portfolio Performance
Press 7 to exit
4
Please Enter portfolio name to update
Please Enter Portfolio name
inflexi
Stock Name : [Buying Price($), Quantity, Buying Date]
"GOOG" : [122.65,90,"2022-08-12"]
"T" : [17.38,120,"2022-09-12"]
The portfolio entered inflexi is inflexible.
Please select one of the options
Press 1 to create new Portfolio
Press 2 to examine composition of a portfolio
Press 3 to determine total value of a portfolio on a certain date
Press 4 to edit portfolio
Press 5 to determine cost basis of portfolio
Press 6 to see portfolio Performance
Press 7 to exit
4
Please Enter portfolio name to update
Please Enter Portfolio name
flexi
Stock Name : [Buying Price($), Quantity, Buying Date]
"POR" : [53.12,10,"2022-09-12"]
"T" : [20.6,110,"2022-07-12"]
"ABC" : [135.71,87,"2022-01-12"]
"TR" : [33.6,2,"2022-02-12"]
"RT" : [2.4,12,"2022-04-12"]
"AAP" : [208.9,12,"2022-05-12"]
"MN" : [12.8,20,"2022-08-12"]
"PQ" : [0.5764,10,"2022-10-09"]
"TY" : [26.09,12,"2022-10-09"]
"HI" : [39.49,50,"2022-10-04"]
"EW" : [97.36,120,"2022-09-12"]
"GOOG" : [2567.49,90,"2022-04-12"]
Please select one of the options
Press 1 adding more stocks to portfolio
Press 2 for deleting stocks from portfolio
1
Please select one of the options
Press 1 to Buy Stock
Press 2 to Exit
1
Please Enter Stock name
MNT
Please Enter Stock does not exists, please give a different name
Please Enter Stock name
MNB
Please Enter Stock does not exists, please give a different name
Please Enter Stock name
PNB
Please Enter Stock does not exists, please give a different name
Please Enter Stock name
BUS
Please Enter Stock does not exists, please give a different name
Please Enter Stock name
SA
120
Please Enter Brokerage Fees
30.9
Please Enter date to evaluate portfolio, should be in format yyyy-MM-dd
2022-09-10
Please select one of the options
Press 1 to Buy Stock
Press 2 to Exit
2
Please select one of the options
Press 1 to create new Portfolio
Press 2 to examine composition of a portfolio
Press 3 to determine total value of a portfolio on a certain date
Press 4 to edit portfolio
Press 5 to determine cost basis of portfolio
Press 6 to see portfolio Performance
Press 7 to exit
6
Please Enter portfolio name to see performance
Please Enter Portfolio name
inflexi
Please Enter date to evaluate portfolio, should be in format yyyy-MM-dd
2022-05-09
Please Enter date to evaluate portfolio, should be in format yyyy-MM-dd
2022-07-10
Porfolio name is inflexi
16 May 2022: *************************************************
23 May 2022: **************************************************
30 May 2022: ************************************************
06 Jun 2022: *************************************************
13 Jun 2022: ***************************************************
20 Jun 2022: **********************************************
27 Jun 2022: ***********************************************
04 Jul 2022: **************************************************
11 Jul 2022: ***********************************************
Scale: * = $4262

Please select one of the options
Press 1 to create new Portfolio
Press 2 to examine composition of a portfolio
Press 3 to determine total value of a portfolio on a certain date
Press 4 to edit portfolio
Press 5 to determine cost basis of portfolio
Press 6 to see portfolio Performance
Press 7 to exit
5
Please Enter portfolio name to update
Please Enter Portfolio name
inflexi
Please Enter date to evaluate portfolio, should be in format yyyy-MM-dd
2022-09-12
Cost basis for portfolio inflexi on date 2022-09-12 is 11068.5$
Please select one of the options
Press 1 to create new Portfolio
Press 2 to examine composition of a portfolio
Press 3 to determine total value of a portfolio on a certain date
Press 4 to edit portfolio
Press 5 to determine cost basis of portfolio
Press 6 to see portfolio Performance
Press 7 to dollar Strategy
Press 8 to exit
