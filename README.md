# SpartaSimulator-Eng95

Group project: Simulation of Sparta-like training company.

## Table of Contents
1. [Introduction](#introduction)
2. [Installation Requirements](#installation)
3. [Instructions](#instructions)
4. [Features](#features)
5. [Testing](#testing)
6. [Future Direction](#future)

## Introduction <a name ="instroduction"><a/>

In this project we were tasked with creating a simulator for a company like Sparta, this involved the creation of trainees, training centres, and clients all with requirements that need to be met.

## Installation Requirements<a name="installation"><a/>

This project runs with JDK 16, and needs this to operate if downloaded via a cloned repository or downloaded from a zip and installed.

## Instructions<a name="instructions"><a/>

Upon running the system, it will prompt the user in the console with this message:
- *Please select a results output method:*
- -*'t' for all months displayed*
- - *'m' for Month-by-month breakdown with summary at the end*

These two options allow you to either see the results of the simulation at the end of its run with a total or to show the changes seen with every month that passes during the simulation's run.
Once either option has been selected, it will then show this message
- *Please enter a number of months for the simulation to run for: *
  The user should then enter a number that isn't 0 but isn't more than 120, and this input is used as the number of months that the system will simulate the system for.
  
  Upon submitting a value, the system will output a row of values showing the
- Open training centres
- - This will have a further breakdown showing the amount for each Sparta Centre type. These are centres which still haven't reached their max capacity of students.
- Closed training centres
- - This will have a further breakdown showing the amount for each Sparta Centre type. Closed training centres are created when the centre has less than 25 people in it after a month.
- Full Training Centres
- - This will have a further breakdown showing the amount for each Sparta Centre type. This is when the centre has reached its max capacity
- Total Trainees
- - This will have a further breakdown with each student categorised by the current course stream that they're enrolled in.
- Waiting List Length
- - This will have a further breakdown with each student categorised by the current course stream that they're enrolled in. This section is for students who were unable to enter a centre that's currently in the system and thus are waiting to be placed in one.
- Bench
- - This will have a further breakdown showing the amount for each Sparta Centre type. This is where students who have been in a centre for over a year get moved to and await being placed with a client.
- Clients
- - These are people who hire trainees and take them from the bench if they have a matching course stream as the one that the client wants. Their ID, happiness and amount of hired spartans will be shown, along with how many are in the system.

  The program should then stop running after showing the results for the set period.

## Features<a name="features"><a/>

This project has several features that make it stand out from other similar projects, a number of those are listed below.

### Streams

Streams were used with this s1imulation's view class to streamline the code needed to output the results of the simulation in a clean and ordered tabular method. 

### Collections

The sim project uses a variety of collections. Bench uses ArrayDeques to create a system where trainees who get removed from a centre are placed at the top of the queue so that they can be prioritised first for being placed into new centres that match their course stream or are open.

### Model-View-Controller

An MVC model was implemented in this project. 
The model is called **Simulator**, which handled the main logic when it came to interacting with the centre, bench, trainees and clients. 
The view was called **SimulationCLIView**, which handled the output of the simulation's values.
The controller is called **Simulation**, which manages the Simulator and View through running the methods from Simulation and creating arrays to hold the values retrieved from the model to update the view with.

### Intuitive Interface

This application has a simple to use command-line interface that allows the user to select the number of months they wish the simulation to run for, as well as the type of output the user wishes to have, be it monthly increments or the overall results. The Overall results will show the number of open, closed and full training centres, as well as the total number of trainees, and the trainees on the waiting list.

## Testing<a name="testing"><a/>

There are a total of 61 tests so far.

### TraineeCentre Tests

The tests performed for this class include...

### BenchShould
This tested whether or not the class was able to add trainees into the different course stream ArrayDeques, whether it was capable of recognising when trainees were added into any of its streams by decreasing a total count variable.

### BootCampShould

This tests the functions of the BootCamp class. This has tests that check if the grace period constraint (where it has 2 months before it closes if it has under 25 trainees) works properly, checking if the counter for this properly works and the isClosable triggers only if it has reached that condition.

### ClientShould

This tests whether the client happiness check works, where
- The check triggers when the client is older than 12 months in the system and if it correctly updates whether the amount requirements for trainees have been hit or not
- Tests whether it stops adding more trainees if they are happy

### SimulationShould
Tests Simulation's methods such as 
- the ability to send trainees to the bench from their centres once they're trained for 12 months
- The ability to return trainees from the bench and take them out to check if they fit for a client being checked in the system
- creating and storing clients in an ArrayList
- Check whether the Trainee is sent to right bench ArrayDeque which matches their current course stream

### TestCentreShould
Tests if the standard isClosable (closes the next month if the trainees are lower than 25) is correctly implemented as so to make sure that it doesn't incorrectly trigger.

### TraineeShould
Tests if trainee objects are properly created by using different ints as params for the ID and if setting the course stream enum would work.

### TraineeHubShould
Tests the isClosable method for this class to see if it functions correctly using the standard condition.


## Future Direction <a name="future"><a/>
One of the further implementations for this project would've been the implementation of the Client happiness system, where if a Client has been able to meet the required amount of trainees, they will leave the system for a year happy and return a year later with the same amount and course stream requirements, and if they are unable to meet this requirement, they'd become unhappy and leave the system completely.

Another requirement would've been the proper implementation of multiple Traininghubs (between 1-3 at once) during the simulation's run time.

Adding a way to persist to databases would be a further addition to this project.
