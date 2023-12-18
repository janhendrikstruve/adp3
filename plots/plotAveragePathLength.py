import matplotlib.pyplot as plt  # Import the matplotlib library for plotting
import pandas as pd  # Import pandas for data manipulation

csv_path = 'averagePathLength.csv'  # Path to the CSV file

data = pd.read_csv(csv_path)  # Read the CSV file into a pandas DataFrame

# Extract the first column (assumed to be the number of keys) from the DataFrame
numberOfKeys = data.iloc[:, 0]  
# Extract the second column (assumed to be the average path length); it should be iloc[:, 1] instead of [:, 1]
averagePathLength = data.iloc[:, 1]  

plt.plot(numberOfKeys, averagePathLength)  # Plot the data

# Set the title and labels for the plot
plt.title("Durchschnittliche Pfadlänge vs. Anzahl der Schlüssel")
plt.xlabel("Anzahl der Schlüssel")
plt.ylabel("Durchschnittliche Pfadlänge")

plt.show()  # Display the plot
