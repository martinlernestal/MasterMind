CREATE database MASTERMIND;

use mastermind;

CREATE TABLE games (
    endDate timestamp primary key not null,
    playTime long not null,
    userName varchar(100) not null,
    score int not null,
    color1 varchar(100) not null,
    color2 varchar(100) not null,
    color3 varchar(100) not null,
    color4 varchar(100) not null
    
);

CREATE TABLE rounds (
	id int primary key not null auto_increment,
    timeofgame timestamp null,
    timeofguess timestamp null,
    guessedcolors varchar(255) null,
    resultcolors varchar(255) null
);