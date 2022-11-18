# The tutorial give us a promt to code it tell us
# "Exercise 1: Plot the following function on the given interval.
# f(x)  e^-x^2 , 0<=x<=1
# Plot it as a solid red line of greater than
#default thickness. Add a grid and give the plot the title ”Gaussian”
function Exercise
  # this sets our interval from 0-1 we need 20 sub intervals so 0.05 fits the bill
  x=(0:0.05:1)
  # next we need our exponent value which is x^2
  val = x.^2.
  #then we need to solve for y using our exp function
  y = exp(val)
  #then we can just plot our answers
  plot(x,y,'b--*','linewidth',3)
  #need to add the gidlines and the title
  grid on
  title("Gaussian")
 endfunction

