import matplotlib.pyplot as plt
import pandas as pd


csv_path = "plots/averagePathLength.csv"

data = pd.read_csv(csv_path)

numberOfKeys = data['numberOfKeys']
averagePathLength = data['averagePathLength']

plt.plot(numberOfKeys, averagePathLength)

plt.show()