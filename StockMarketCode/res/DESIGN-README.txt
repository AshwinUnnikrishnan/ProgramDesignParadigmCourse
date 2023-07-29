DATAFILES that stock market uses
    - tickerName.txt : text file that contains all the acceptable ticker. Without this the system
        won't start as in real life situation also without ticker it doesn't make sense to run a
        system.
    - jsonFormat.json : json file that contains all the details of a user, the contents are store
        line by line and two users are separated by a line containing , this file will be created if
        it does not already exist.
    - userName.txt : this file contains list of all the user that is registered, when user logs in
        userName is checked against this file if the person says existing user or he is trying to
        create new account.

INPUT FORMAT
    - Username : Can only be alphabets.
    - Portfolio, Stock name : Can only be alphabets and uppercase.
    - Quantity : Restricts the investor to only provide integer values.
    - Date : yyyy-MM-dd


DATA STORAGE IN THE MODEL

    Investor Object     -   Username and list of portfolio stored as HashMap
    Portfolio Object    -   Portfolio name and list of portfolio stored as HashSet
    Stock Object        -   Stock Name, Buying Price, Date, Quantity

FLOW

    1. When the stock market starts we provide user 3 options
            a. Existing User
                If the user has already registered with the system then they press 1 we get the
                userName and check against the userName.txt if they are registered or not and
                continues if user is registered.
            b. New User
                If the user is new this step gets the userName and creates a new profile for user
                 in the database. If the userName entered has numbers or special characters, it
                 asks for valid userName.
            c. Exit
                Exits the program.
    2. Once the user logs in existing or new, the controller will have all the details of the user
        it will create an investor object for that user.
        Each investor object will have a portfolio set, that contains all the portfolio.
        Each portfolio object will have a set of stocks, that contains all stocks.
        Each stock will have quantity, buying price and buying date.
    3. The user is given options to perform once logged in
            a. Create new portfolio
                Asks for portfolio name and checks against existing portfolio's so that we don't
                create new portfolio with same name.
                Once portfolio name is valid we keep asking the user to enter stock name and
                quantity till he wants to.
                Once he wishes to stop we write the portfolio to the database.
                The buying price of the stock is taken for each stock immediately after the name,
                quantity is entered.
            b. Get Composition
                Keeps asking the user to enter a valid portfolio name that is present in the
                database under his name. Once valid portfolio is provided gives the composition of
                the portfolio by listing out the stocks and their respective attributes.
            c. Get Evaluation
                Gets a valid portfolio and date and then fetches the details of each stock and
                calculates the total money of that portfolio with respect to the quantity of stocks
                as well and uses the broker fees also
            d. Edit Portfolio
                In flexible portfolios we can add or sell stocks
                Add Stock to the Portfolio
                    Buy Stocks to a portfolio if it does not exists.
                Sell Stock from the portfolio
                    Sell stocks from a portfolio if it exists.
            e.  CostBasis
                To determine the cost basis of the portfolio for the given date.  This would include
                 all the purchases made in that portfolio till that date.
            f.  Portfolio Performance Over Time
            g.  Exit
                    Exit the program.

CHANGES

    1. We made portfolio interface and abstract class and then extended flexible and inflexible
        portfolios classes.
    2. Added models to calculate portfolio visualization and added functionalities to the stockModel
        the visualization is in different class because we can later have a different visualization
        technique to update it.
        Other functions are added to model because this is better version of stock and the other
        version is not good to be implemented as a system, may be in future if  additional chery
        like features are to be added then we can extend the class and then make a different class
        and make changes in that.
    3. We have started storing transactions in the portfolio, so that we know at what time different
        stocks where bought and sold on.
        Helps in check the portfolio value and cost basis of a portfolio
    4. Got inputs from user for broker fees, so that this can be later used by brokers.



CHANGES GUI

	1. We take in argument when running the initial main to run either the text based or GUI based.
	2. Created new Controller, view for the GUI, using the same model for the GUI.
	3. All the View GUI is implemented using one Frame and CardDeck layout and we switch between the cards based on the button presses and input and operations.






















