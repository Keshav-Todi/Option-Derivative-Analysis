**Option Derivative Analysis**

This repository offers a comprehensive toolkit for generating virtual option chains, extracting live option data from any exchange API, and analyzing multiple option strategies for potential profit and loss payoffs.

Visual interface: https://github.com/Keshav-Todi/Option-Derivative-Analysis/blob/main/option%20profit%20analysis%20terminal.png

**Key Features**

**Strategy Comparison and Payoff Analysis**

**Multi-Strategy Comparison:** Evaluate multiple strategies across all strikes and expiries, comparing potential payoffs to find the optimal strategy based on selected criteria like credit preference, maximum payoff, or minimum loss.
**Custom Constraints**: Set constraints for maximum investment amount and quantity to fine-tune strategy selection.
Static and Dynamic Payoff Analysis

**Static Analysis:** Visualize fixed payoff outcomes through both tables and charts for clear, static analysis.
**Dynamic Analysis:** Track real-time strategy performance with live market data to adjust and optimize in changing conditions.

**Custom Complex Strategy Payoff**

Input specific calls, puts, strikes, expiries, and futures to visualize payoffs of unique multi-legged strategies numerically and graphically.

**Market Testing and Deployment**

**Virtual & Historical Testing:** Test strategies using virtual or historical data for safe, simulated analysis.
Live Deployment: Execute strategies directly on an exchange using API integration for real-time trading.

**Core Classes**

Option_Profit_Analysis: Handles payoff calculations, strategy comparison, and logic for analysis.
Default_Value_Example: Provides a user interface for inputting parameters, viewing charts, and seeing numerical results.

**Getting Started**

Clone the repository and run the Default_Value_Example java file
A user interface panel must have opened
Choose virtual option chain and market
Choose option strategy and run it

**Requirements**

Java
Python 3.x
Libraries: Pandas, Matplotlib, requests (for API access)
