import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
import matplotlib.ticker as ticker
#%matplotlib inline

import pylab

functionName = 'F2_4.x0{-10,-10}' #should be same as field in csv file
csvSource = 'test.csv' #file with table

def parse_points(source):
  df = pd.read_csv(source)
  x_1, x_2 = [], []
  for i in df[functionName]:
    print(str(i))
    if str(i) == 'nan':
        break
    a, b = map(float, i.split(", "))
    x_1.append(a)
    x_2.append(b)
  return x_1, x_2




x_1, x_2 = parse_points(csvSource)
pylab.plot(x_1, x_2, '-r')

#pylab.show()
pylab.savefig(functionName+'.png')
pylab.show()
