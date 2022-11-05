# the tutorial then gives us a Excercise2 to do, that is similar to the first but harder
# the prompt states " Exercise 2 Plot on the same axis as in Exercise 1 the following functions on the interval
#0 ≤ x ≤ 1,
#f1(x) = sin(2πx), f2(x) = cos(2πx)
#Let the points on the x-axis be spaced 0.025 units apart. Plot f1(x) using black asterisks
#with no connecting line, and f2(x) using black circles with no connecting line. Label your
#x- and y-axis, “x” and “y” respectively. Include an appropriate legend.
 function Exercise2
    # first we set our intervals which was given to us in the problem
    x=(0:0.025:1)
    # next we want to find our value for our first problem which is sin(2pix)
    y = sin(2*pi*x)
    # since we are ploting two diffrent graphs in one function we can use the subplot
    # the subplot method plots multiple graphs on one figure making it look realy nice
    subplot(221)
    #then we plot our function with block asterisks
    plot(x,y,'k--*','linewidth',1)
    #next we label our x and y axis and give our graph a title
    xlabel('x values')
    ylabel('sin(2*pi*x)')
    title ("f1(x) = sin(2πx)")

    #now we set do our next plot which has the same intervals but diffrent y values
    y = cos(2*pi*x)
    #now we plot our new values we need black circles with no conecting lines
    subplot(222)
    plot(x,y,'k--o','linewidth',1)
    #finally label our graph apropriatly
    xlabel('x values')
    ylabel('cos(2*pi*x)')
    title ("f2(x) = cos(2πx)")
 endfunction
