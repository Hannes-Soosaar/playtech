#  Betting Data Report Generator (*BDRG*)

## Data Extraction

the *BDGR* program extracts data from two file sources *"player_data.txt"* and *"match_data.txt"*. transforms the data and Writes the generated report to *"results.txt"*.

**File names and file read/write paths can be changed from the *Config.java* file**

### player_data

The player_data.txt file must contain the following.
  - the columns must be separated by a comma (",")
  - there must be 5 columns (playerId,operation,matchId,tansactionAmmount,sideBetOn)
  - matchId and sideBetOn can be left empty but the places holder must still be marked with commas  eg.     (playerId,operation,,tansactionAmmount,)
  - the file may NOT contain any empty lines
  - the file may NOT have a header

**In the *Config.java* file changes can be made to the column if needed**

### match_data

The match_data.txt file must contain the following
  - the columns must be separated by a comma (",")
  - there must be 4 columns (matchId,returOnA,returOnB,matchResult)
  - returOnA and returOnB must have two places after the decimal and the decimal must be a period (".")
  - all fields must be filled
  - the file may NOT contain any empty lines
  - the file may NOT have a header

**In the *Config.java* file changes can be made to the column if needed**

## The reports

The *BDGR* generates a single file *results.txt* that contains three reports separated by empty lines.

### Player Account Statistical report

Generates a list of all legitimate* players containing
    - the player id
    - total coin balance as per the input data
    - the wins ratio ( wins divided by total bets made)
-The win ratio has two places after the decimal and uses a "," comma as the decimal mark

### Illegitimate Player Report

 Generates a list of all illegitimate* players and their last transaction containing.
   - player ID
  - operation 
  - match ID (can be "null")
  - tansaction Ammount,
  - sideBetOn (can be "null")
- all fields are separated by a space (" ")
- If there is nothing to report an empty line will be generated along with the separating empty line
- none of the Illegitimate player transactions are considered regardless of when the last transaction took place
  
      
### Host Balance Report

 Generates a balance for the host after all legitimate transactions have concluded as per the input data.
   - the result is rounded down
  - the result does not have any decimal places
  - the result does not contain any transactions done by illegitimate* players   
    
**A legitimate player is a player who has **NOT** bet more or tried to withdraw more than is in their account balance* 

**File names and file read/write paths can be changed from the *Config.java* file**


