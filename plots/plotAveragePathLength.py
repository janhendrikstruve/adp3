import matplotlib.pyplot as plt
import pandas as pd


csv_path = 'averagePathLength.csv'

data = pd.read_csv(csv_path)

numberOfKeys = data.iloc[:, 0]
averagePathLength = data[:, 1]

plt.plot(numberOfKeys, averagePathLength)

plt.title("Durchschnittliche Pfadlänge vs. Anzahl der Schlüssel")
plt.xlabel("Anzahl der Schlüssel")
plt.ylabel("Durchschnittliche Pfadlänge")

plt.show()