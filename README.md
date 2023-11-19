# Betting data report Generator (*BDRG*)

The BDRG is created to give an overview of active accounts, illegitimate players, and the hosts
balance to the stakeholders

    ## Data Extraction

    data is collected from two files that need to be located in the root directory of the program. The read location of the
    files can be changed in the *Config.java* file.

    There are formatting guidelines for the input files that need to be followed to ensure data extraction

        ### player_data.txt
            - the file can not have an empty line
            - the data delimiters are ","
            - there must be no more than 5 columns
            - the data is assumed to not contain instances where a player has made more than one bet on a single match
            - the column can be rearranged in the *Config.java* file
            - the file should not contain a header
            - matchId and betPlaceOn may be left empty eg. *"id1,WITHDRAW,,100,"*

        As default the columns in the file must be arranged as follows

        **playerID,operation,matchId,transactionSum,betPlacedOn**

        ### match_data.txt
            - the file can not have an empty line
            - the data delimiters are ","
            - there must be no more than 4 columns
            - the column can be rearranged in the *Config.java* file
            - the file should not contain a header
            - none of the fields can be empty

        As default the columns in the file must be arranged as follows

        **matchId,matchReturnOnA,matchReturnOnB,matchResult**

    ## Data Transformation

        ### playerAccount
            - Player accounts are created based on the *"player_data.txt"* file
            - All starting balances start from 0
            - Any operation that exceeds the players balance will have the player marked as an illegitimate player.
            - A balance is calculated for each player based on the operations performed rounded, without any decimal
            places
            - A win rate is calculated as wins divided by total bets with a precision of two places after the
            decimal

        ### Illegitimate players list
             - Any player that has tried to place a bet or withdraw more than what is on their balance will be placed on
              the illegitimate player list

        ### hostBalance
            A balance is calculated for the host based on the following rules
                -The balance starts at 0
                -The balance can be negative
                -Any bet made by a player on the Illegitimate player list regardless of when it was made will be omitted
                 from the balance calculations
                -the host pays out all wins
                -the host gets all funds from a losing bet

    ## Report Generations

        **The win ratio on the player report uses a "," (comma) as a decimal separator**

        - All reports are saved to a file with an empty line separating the reports
        - If there is nothing to report an empty line will be added to the file instead of the report, the report
        separating empty line will also be added
        - A space character is used to separate data fields
        - the report file directory can be changed from the  *config.java* file
        -There are three reposts that are to be generated in the same order as per the list below
            -Active player account report
            -Illegitimate player list
            -Host balance

In case of questions please feel free to write to hsoosaar@gmail.com
