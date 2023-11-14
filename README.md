# COMP2140

sign up or log in (Student/Staff)
login (of Admin)

Main Menu
list of all the opition user can make 

User
password
first name
last name 
email address 
rate


createuseraccount()
allows the user to create an account
cancelSubscription()
deletes data for a specific user
 
Student 
studentID
points  

Calculatepoints():points
points=numofrides*rate*cost

Staff
staffID

Admin 
accesskey 

viewuser()
viewincidentreport()
viewrenthistory()
edituser()
deleteuser()


Paymetplan(hours) 
date
price
paymentmethod (post/pre-paid)
paymentplantype
invoice number
sendinvoice()
calculatefee(): price*hours + latefee

Login 
ID number 
password
authenticate user ()
notifyUser()
sends token to user to verify their identity 

BicycleRental 
price=Paymentplan()
dockingstations
avaliablebikes 
rentaltimeslots[1hr-1day]
returnbike()
rentbike()
getloaction()
getavaliablebikes()

Bike
price
bicycleID
brand
model
braketype
make
type
adminrating
userrating 
size
condition
isavaliable 
latefee

RateBike 
bikeID
bikecondition [A, B, C, D, E. F]
review (string)

IncidentReport (child of ratebike)
bikeID
first name
last name
ID number 
userStatment



