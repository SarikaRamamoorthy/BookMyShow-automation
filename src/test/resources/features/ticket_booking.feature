Feature: Book movie ticket

  @Test
  Scenario: User selects their preferred location

	Given the user is on the home page
	When the user searches for the location
  	Then the relevant location should be displayed and selected
  	And verify the selected location

  @Test
  Scenario: User selects their preferred movie

  	When user selects the movie
	Then verify the selected movie
  	When user selects book ticket
  	And user selects language and format
	Then verify the selected movie in theatre details page

  @Test
  Scenario: User selects their preferred theatre and show timings

	When the user selects a theatre and show timings, prioritizing fast filling or an available slot
	And the user selects the number of seats
	And the clicks select seat button
	Then verify the selected movie in seat selection page
	And verify the selected theatre name
	And verify the seat count
	And print the seat numbers which have adjacent empty seats

