## Getting Started

Welcome to the VS Code Java world. Here is a guideline to help you get started to write Java code in Visual Studio Code.

## Folder Structure

The workspace contains two folders by default, where:

- `src`: the folder to maintain sources
- `lib`: the folder to maintain dependencies

Meanwhile, the compiled output files will be generated in the `bin` folder by default.

> If you want to customize the folder structure, open `.vscode/settings.json` and update the related settings there.

## Dependency Management

The `JAVA PROJECTS` view allows you to manage your dependencies. More details can be found [here](https://github.com/microsoft/vscode-java-dependency#manage-dependencies).


🚚 Vehicle Tracking & Maintenance System
DCIT308: Data Structures and Algorithms II — Group Project Report
University of Ghana
July 2025

📋 Project Overview
Adom Logistics is a regional freight transport company operating a fleet of trucks and delivery vans. The company’s manual record-keeping process has led to delayed servicing, route mix-ups, and inaccurate delivery logs.

This project implements a console-based Vehicle Tracking & Maintenance System, simulating dispatcher decisions, and utilizing key data structures and algorithms.

🎯 Functional Modules Implemented
✅ Vehicle Database

Add, remove, and search vehicles by registration number.

Each vehicle stores:

Registration number

Type

Mileage

Fuel usage

Assigned driver

Maintenance history

Uses a TreeMap<String, Vehicle> for storage (efficient searching).

Supports binary search and custom sorting by mileage/type.

✅ Driver Assignment & Activity Logs

Priority queue of available drivers, sorted by experience.

Stack of assigned drivers with activity tracking.

Logs include:

Assigned routes

Delays

Infractions

Supports saving and loading drivers to/from drivers.txt.

✅ Delivery Tracking

Queue-based delivery tracking.

Each delivery stores:

Package ID

Origin & Destination

Assigned Vehicle & Driver

ETA & Status (Pending/In-Transit/Delivered)

Supports rerouting and updating based on package status.

Deliveries persist in deliveries.txt.

🚧 Maintenance Scheduler (to be implemented by group member)

Prioritizes vehicles needing maintenance based on mileage and last service.

Records parts replaced and costs.

Uses a min-heap / priority queue.

Persists records in maintenance.txt.

🚧 Fuel Efficiency Reports (to be implemented by group member)

Calculates average fuel usage and flags outliers.

Implements filtering and custom sorting of vehicles by fuel performance.

🧪 Data Structures & Algorithms
Feature	Data Structure/Algorithm
Vehicle DB	TreeMap (log n search)
Driver Assignment	PriorityQueue, Stack
Delivery Tracking	Queue, LinkedList
Maintenance Scheduler	PriorityQueue/Min-Heap
Sorting	Insertion/Merge/Quick
Searching	Binary Search
File Storage	Plain text files 

📄 Report Summary
This system demonstrates application of data structures and algorithms in a real-world logistics context.

Time complexity of vehicle search: O(log n)

Space complexity proportional to number of vehicles, drivers, and deliveries.

Designed to improve operational efficiency, reduce errors, and ensure timely maintenance.

