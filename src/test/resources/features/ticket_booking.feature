Feature: Book movie ticket

  @Test
  Scenario: User selects their preferred location

	Given the user is on the home page
	When the user searches the location
  	Then the relevant location should be displayed and selected
  	And verify the selected location

#  @Test
#  Scenario: User selects their preferred location

#  	When the user searches the movie name

  	When user selects the movie
  	And user selects book ticket
  	And user selects language and format

#	Given I am on the movie booking website
#	When I search for the movie "Sarika"
#	Then "Sarika" should appear in the search suggestions
#
#  Scenario: User selects movie and showtime
#	Given I have found the movie "Sarika" in the search suggestions
#	When I select the movie "Sarika"
#	Then I should be presented with the showtimes for "Sarika" in Coimbatore
#
#  Scenario: User selects a showtime with filling fast seats
#	Given I have selected the movie "Sarika"
#	When I filter for showtimes where seats are filling fast (orange/red color)
#	Then I should see showtimes with available seats that are almost full
#
#  Scenario: User selects a showtime and language/format
#	Given I have selected a showtime with filling fast seats
#	When I select the appropriate language and format for the movie
#	Then I should be able to proceed with the booking
#
#  Scenario: User selects 1 ticket and finds adjacent empty seats
#	Given I have selected the language and format for the movie
#	When I choose 1 ticket for the showtime
#	Then I should see the seat numbers of the adjacent empty seats
#
#  Scenario: User completes the booking process
#	Given I have selected 1 ticket and reviewed the empty seats
#	When I proceed to checkout and confirm my booking
#	Then I should receive a confirmation for my movie ticket booking for "Sarika" in Coimbatore
