# 🪙 CurrencyConverterFX

**CurrencyConverterFX** is a simple JavaFX desktop application that converts currencies using real-time exchange rate data. It demonstrates the use of JavaFX for GUI development and JSON parsing using the `org.json` library.

---

## ✨ Features
- User-friendly graphical interface built with JavaFX
- Live currency conversion between major world currencies
- Parses and displays real-time exchange rate data from JSON APIs
- Lightweight and beginner-friendly codebase

---

## 🧰 Technologies Used
- Java 17+
- JavaFX (OpenJFX 21+)
- `org.json` library for JSON parsing
- HTTP API (optional: [ExchangeRate API](https://exchangerate.host/) or hardcoded JSON for demo)

---

## 🚀 How to Run

### 1. Clone this repository:
```bash
git clone https://github.com/yourusername/CurrencyConverterFX.git
cd CurrencyConverterFX

### 2. Compile the application:

javac --module-path "<path-to-javafx-lib>" --add-modules javafx.controls -cp "lib/json-20231013.jar" -d out src/CurrencyConverterFX.java

###3. Run the application:

java --module-path "<path-to-javafx-lib>" --add-modules javafx.controls -cp "out;lib/json-20231013.jar" CurrencyConverterFX
🔁 Replace <path-to-javafx-lib> with your local JavaFX lib folder path.

📦 Folder Structure

CurrencyConverterFX/
├── src/
│   └── CurrencyConverterFX.java
├── lib/
│   └── json-20231013.jar
├── out/
└── javafx-sdk-24.0.1/
    └── lib/
📜 License
This project is licensed under the MIT License. Feel free to use and modify it!









