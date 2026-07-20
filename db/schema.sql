-- Users Table
CREATE TABLE IF NOT EXISTS Users (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    username TEXT NOT NULL UNIQUE,
    password TEXT NOT NULL
);

-- Trains Table
CREATE TABLE IF NOT EXISTS Trains (
    train_no INTEGER PRIMARY KEY,
    train_name TEXT NOT NULL
);

-- Reservations Table
CREATE TABLE IF NOT EXISTS Reservations (
    pnr TEXT PRIMARY KEY,
    passenger_name TEXT NOT NULL,
    train_no INTEGER NOT NULL,
    train_name TEXT NOT NULL,
    class_type TEXT NOT NULL,
    journey_date TEXT NOT NULL,
    source TEXT NOT NULL,
    destination TEXT NOT NULL,
    FOREIGN KEY (train_no) REFERENCES Trains(train_no)
);

-- Insert Sample Data for Users
INSERT OR IGNORE INTO Users (username, password) VALUES ('admin', 'admin123');

-- Insert Sample Data for Trains
INSERT OR IGNORE INTO Trains (train_no, train_name) VALUES (12627, 'Tamil Nadu Express');
INSERT OR IGNORE INTO Trains (train_no, train_name) VALUES (12622, 'Tamil Nadu Express Return');
INSERT OR IGNORE INTO Trains (train_no, train_name) VALUES (12637, 'Pandian Express');
INSERT OR IGNORE INTO Trains (train_no, train_name) VALUES (12675, 'Kovai Express');
INSERT OR IGNORE INTO Trains (train_no, train_name) VALUES (12631, 'Nellai Express');
