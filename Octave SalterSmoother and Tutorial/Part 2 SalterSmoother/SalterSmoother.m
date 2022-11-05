%{
For this project I had to implement the salter smoother project into octave, I first wanted to graph just a standard function
I used the simple y=mx+b formula to plug in my x values and solve for my y, i then called the salter function which just takes the data and salts it
then i called my smoother function which just takes the data and just smooths its out put
%}
function Grapher
  #crateing a array of our x values
  x=[]
  # for loop that creates the x values and adds it to our new array
  for i=1:20
    #adding one to keep a steady interval
    val = i+ 1
    #adding values to array
    x = [x val]
  endfor
  #rinse and reapet, create our y array to hold our y values
  y=[]
  #for loop that takes 20 values plugs it into the problem y=5x+1 and solves
  for i = 1:20
    val = 5*(i)+1
    #adds the y values to our newly created array
    y = [y val]
  endfor
  #combining the two array into one matrix that hold both values
  xy=[x,y]
  #next i wrote my data to a csv file
  csvwrite('RegularGraph.csv',[x;y]')
  #I next had to plot our answer, but before this i researched somthing called subplot which allows multiple plots on one figure
  #the first 2 numbers are the amount of graphs so here we have a 2x2 graph matrix and 1 is declaring where the graph will be placed
  subplot(221)
  #next i ploted using the color blue with a dashed line and circle point i also had a line thickness of 2
  # i tried to change up the color and line each time i ploted
  plot(x,y,'b--o','linewidth',2)
  #i added a title as well as x and y labels and i turned the grid on, because it looks neater
  grid on
  title("Regular Created Graph")
  xlabel('x values')
  ylabel('y values')
  #i then called my Salter function and passed the two arrays through
  Salter(x,y)
 endfunction



 %{
 This funciton salts are data it takes two arrays in labeld as x and y
 once our values are passed this function takes our y values and salts them by adding random number to them
 %}
function Salter(x,y)
  #i first created to arrays and set the values equal to the values being passed through
  y=[y]
  x=[x]
  #this for loop runs through each y values and adds a random number to them
  for i=1:length(x)
    #this get a random number 0-1 as a decimal then multiples it by 100 to give us a better salted number
    #for example this rand(1) would give a decimal 0-1 such as 0.50 but multiply by 100 gives us 50
    ran=rand(1)*100
    y(i)=y(i)+ran
  endfor
  #i next just wrote the data to a csv file
  csvwrite('SaltedGraph.csv',[x;y]')
  # i then declared my subplot again, its important to do this before you plot because if you dont your graph gets messed up
  subplot(222)
  # i then ploted our values using a red solid line with astrix and a line thickness of 1
  plot(x,y,'r-*',1)
  # then i turned the grib on and labeled our graph
  grid on
  title('SaltedGraph')
  xlabel('x values')
  ylabel('salted values')
  # i then called the smoother funtion to smooth our data
  Smoother(x,y)
 endfunction
 %{
  This function takes our salted data and finds the average of 5 points and smooths the data
 %}
function Smoother(x,y)
  #i once again just took two new arrays and passed our variables to them
  y=[y]
  x=[x]
  #this for loop runs though each y value and smooths the data accordingly
  #because of some situation such as the first value in the array we have to write special if statments.
  for i=1:length(x)
    #for the first value in the array there is no middle value because its the first value so we have to find the next 4 values instead
    #if we were to try and just run  y(i)= (y(i-2)+y(i-1)+y(i)+y(i+1)+y(i+2))/5 it would prouduce a error because it would be out of bounds of the array
    if i == 1
          y(i)= (y(i)+y(i+1)+y(i+2)+y(i+3)+y(i+4))/5
    #for the second value in the array the index is off by 1 so we have to adjust the formula to account for this situation
    elseif i == 2
          y(i)= (y(i-1)+y(i)+y(i+1)+y(i+2)+y(i+3))/5
    #for the second to last value in the array we have to adjust the index again to account for the bounds of the y array
    elseif i == length(y)-1
          y(i)= (y(i)+y(i+1)+y(i-1)+y(i-2)+y(i-3))/5
    #for the last value in the array we have to adjust the index one last time to keep in bounds of the array
    elseif i == length(y)
          y(i)= (y(i)+y(i-1)+y(i-2)+y(i-3)+y(i-4))/5
    #finally this else statments catchs the remaining situation, which wouldnt produce a bounds error
    else
          y(i)= (y(i-2)+y(i-1)+y(i)+y(i+1)+y(i+2))/5
    endif
  endfor
  #i then print this data to a csv file
   csvwrite('SmoothedGraph.csv',[x;y]')
   #i then created my subplot one last time
    subplot(223)
    # then i ploted our data with a green dashed line with open cirlces and a line thickness of 3
    plot(x,y,'g--o',3)
    #i then finally labeld my graph and turned the grid on
    grid on
    title('SmoothedGraph')
    xlabel('x values')
    ylabel('Smoothed values')
 endfunction
