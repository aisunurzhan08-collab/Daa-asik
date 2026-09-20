import pandas as pd
import matplotlib.pyplot as plt
import os

# Read CSV
data = pd.read_csv("results/results.csv")

# Create plots folder
os.makedirs("docs/plots", exist_ok=True)

# Time vs n
plt.figure()

for algorithm in data["Algorithm"].unique():
    part = data[data["Algorithm"] == algorithm]
    plt.plot(part["Size"], part["TimeNs"], marker="o", label=algorithm)

plt.xlabel("Input size (n)")
plt.ylabel("Time (ns)")
plt.title("Time vs Input Size")
plt.legend()
plt.grid(True)

plt.savefig("docs/plots/time_vs_n.png")
plt.close()

# Recursion Depth vs n
plt.figure()

for algorithm in data["Algorithm"].unique():
    part = data[data["Algorithm"] == algorithm]
    plt.plot(
        part["Size"],
        part["RecursionDepth"],
        marker="o",
        label=algorithm
    )

plt.xlabel("Input size (n)")
plt.ylabel("Recursion depth")
plt.title("Recursion Depth vs Input Size")
plt.legend()
plt.grid(True)

plt.savefig("docs/plots/recursion_depth_vs_n.png")
plt.close()

print("Graphs created successfully!")

