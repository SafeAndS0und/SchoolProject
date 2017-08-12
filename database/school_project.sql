-- phpMyAdmin SQL Dump
-- version 4.6.5.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Czas generowania: 12 Sie 2017, 15:58
-- Wersja serwera: 10.1.21-MariaDB
-- Wersja PHP: 5.6.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Baza danych: `school_project`
--

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `questions`
--

CREATE TABLE `questions` (
  `ID` int(11) NOT NULL,
  `question` varchar(1000) NOT NULL,
  `answerA` varchar(255) NOT NULL,
  `answerB` varchar(255) NOT NULL,
  `answerC` varchar(255) NOT NULL,
  `answerD` varchar(255) NOT NULL,
  `correctAnswer` varchar(11) NOT NULL,
  `teacherID` int(11) NOT NULL,
  `category` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Zrzut danych tabeli `questions`
--

INSERT INTO `questions` (`ID`, `question`, `answerA`, `answerB`, `answerC`, `answerD`, `correctAnswer`, `teacherID`, `category`) VALUES
(1, 'kiedy wybuchla 2 wojna swiatowa?', '1567', '1924', '1914', '1939', 'D', 1, 'historia'),
(2, 'Ile maksymalnie pamieci RAM przyjmuje system 32-bitowy?', '1', '2', '4', '8', 'C', 1, 'informatyka'),
(3, 'Ile to jest 2^10?', '256', '512', '1000', '1024', 'D', 1, 'informatyka'),
(4, 'Jak nazywa sie typ zmiennej, ktory przyjmuje liczby zmiennoprzecinkowe?', 'float', 'int', 'double', 'boolean', 'C', 1, 'programowanie'),
(5, 'Jak nazywa sie typ zmiennej, ktory przyjmuje ciag znakow?', 'String', 'int', 'boolean', 'char', 'A', 1, ''),
(6, 'Jak msz na imie?', 'Nikodem', 'Jakub', 'Maciej', 'Dawid', 'D', 1, 'imiona'),
(7, 'ile to 2+2?', '1', '2', '3', '4', 'D', 1, 'matematyka'),
(8, 'Z czego zrobione jest leczo?', 'kalafior', 'ziemniaki', 'papryka', 'makaron', 'C', 3, 'kuchnia');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `results`
--

CREATE TABLE `results` (
  `ID` int(11) NOT NULL,
  `studentID` int(11) NOT NULL,
  `teacherID` int(11) NOT NULL,
  `askedQuestions` int(11) NOT NULL,
  `correctAnswers` int(11) NOT NULL,
  `result` int(11) NOT NULL,
  `time` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `student`
--

CREATE TABLE `student` (
  `ID` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `lastName` varchar(255) NOT NULL,
  `whatClass` varchar(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `teacher`
--

CREATE TABLE `teacher` (
  `ID` int(11) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Zrzut danych tabeli `teacher`
--

INSERT INTO `teacher` (`ID`, `username`, `password`) VALUES
(1, 'asd', 'qwe'),
(2, 'qweqwe', 'qweqwe'),
(3, 'kupa', 'dupa');

--
-- Indeksy dla zrzutów tabel
--

--
-- Indexes for table `questions`
--
ALTER TABLE `questions`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `teacherID` (`teacherID`);

--
-- Indexes for table `results`
--
ALTER TABLE `results`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `studentID` (`studentID`),
  ADD KEY `teacherID` (`teacherID`);

--
-- Indexes for table `student`
--
ALTER TABLE `student`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `teacher`
--
ALTER TABLE `teacher`
  ADD PRIMARY KEY (`ID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT dla tabeli `questions`
--
ALTER TABLE `questions`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
--
-- AUTO_INCREMENT dla tabeli `results`
--
ALTER TABLE `results`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT dla tabeli `student`
--
ALTER TABLE `student`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT dla tabeli `teacher`
--
ALTER TABLE `teacher`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
