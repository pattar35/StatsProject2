# this was the example given to use by the tutorial
function Example
  # we create two variables and set them equal to a value
  x = linspace(0,1,11)
  y = x.^ 2.
  # we then ploted them in three different ways
  plot(x,y)
  plot(x,y,'r-x')
  plot(x,y,'r--*','linewidth',3)
endfunction



