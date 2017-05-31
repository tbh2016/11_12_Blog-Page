DROP DATABASE IF EXISTS BlogData_Test;

CREATE DATABASE BlogData_Test;

USE BlogData_Test;

CREATE TABLE ContactInfo (
	contactInfoId INT NOT NULL AUTO_INCREMENT,
    address VARCHAR(200) NOT NULL,
    contactEmail VARCHAR(100) NOT NULL,
    contactPhone VARCHAR(15) NOT NULL,
    PRIMARY KEY (contactInfoId)
    
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;

INSERT INTO ContactInfo VALUES 
(1, "401 S. Main", "email@email.com", "(222)333-5555");

CREATE TABLE Pictures (
    pictureId INT NOT NULL AUTO_INCREMENT,
    pictureData longblob NOT NULL,
    pictureName VARCHAR(200) NULL,
    PRIMARY KEY (pictureId)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;


CREATE TABLE HashTags (
	hashTagId INT NOT NULL AUTO_INCREMENT,
    hashTag VARCHAR(100) NOT NULL,
    PRIMARY KEY (hashTagId)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;

INSERT INTO HashTags VALUES 
(1, "#blessed"),
(2, "#99poblems"),
(3, "#bof"),
(4, "#Guild4Eva"),
(5, "#projectsuck");

CREATE TABLE Users (
	userId INT NOT NULL AUTO_INCREMENT,
    userName VARCHAR(20) NOT NULL,
    userFirstName VARCHAR(20) NOT NULL,
    userLastName VARCHAR(20) NOT NULL,
    isActive BOOLEAN NOT NULL DEFAULT TRUE,
    password VARCHAR(100) NOT NULL,
    KEY userName (userName),
    PRIMARY KEY (userId)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;

INSERT INTO Users VALUES 
(1, "admin", "admin", "admin", true, "$2a$10$DCIk1ZeP5/DU1Za5InnnpO3vp6OHAXQIjT1Ydlt7eDMn0B8X8LDRq"),
(2, "Tbone", "Thuan", "F T W", true, "$2a$10$DCIk1ZeP5/DU1Za5InnnpO3vp6OHAXQIjT1Ydlt7eDMn0B8X8LDRq"),
(3, "AdventrueTime", "Cory", "McMillen", true, "$2a$10$DCIk1ZeP5/DU1Za5InnnpO3vp6OHAXQIjT1Ydlt7eDMn0B8X8LDRq"),
(4, "TokyoJ", "Jayce", "Crowther", true, "$2a$10$DCIk1ZeP5/DU1Za5InnnpO3vp6OHAXQIjT1Ydlt7eDMn0B8X8LDRq"),
(5, "ThatKidYouHate88", "Rob", "Kacic", true, "$2a$10$DCIk1ZeP5/DU1Za5InnnpO3vp6OHAXQIjT1Ydlt7eDMn0B8X8LDRq");

CREATE TABLE Authorities (
 userName varchar(20) NOT NULL,
 authority varchar(20) NOT NULL,
 FOREIGN KEY (userName) REFERENCES Users(userName)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO Authorities (`username`, `authority`) VALUES
('admin', 'ROLE_ADMIN'),
('admin', 'ROLE_MANAGER'),
('admin', 'ROLE_USER'),
('Tbone', 'ROLE_ADMIN'),
('Tbone', 'ROLE_MANAGER'),
('Tbone', 'ROLE_USER'),
('AdventrueTime', 'ROLE_ADMIN'),
('AdventrueTime', 'ROLE_MANAGER'),
('AdventrueTime', 'ROLE_USER'),
('TokyoJ', 'ROLE_ADMIN'),
('TokyoJ', 'ROLE_MANAGER'),
('TokyoJ', 'ROLE_USER'),
('ThatKidYouHate88', 'ROLE_ADMIN'),
('ThatKidYouHate88', 'ROLE_MANAGER'),
('ThatKidYouHate88', 'ROLE_USER');

CREATE TABLE Category (
	categoryId INT NOT NULL AUTO_INCREMENT,
    categoryName VARCHAR (100) NOT NULL,
    categoryDescription VARCHAR(500) NULL,
    PRIMARY KEY (categoryId)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;

INSERT INTO Category VALUES 
(1, "Sports", "All things sports"),
(2, "Entertainment", "Music, Movies, Media and More !"),
(3, "Cats", "Cats, Cats, Cats"),
(4, "Bird", "It's the word"),
(5, "Vaporizer", "The wonderful world of vaping");

CREATE TABLE PageType (
	pageTypeId INT NOT NULL AUTO_INCREMENT,
    pageType VARCHAR(30) NOT NULL,
    pageTypeDescription VARCHAR(300) NOT NULL,
    PRIMARY KEY (pageTypeId)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;

INSERT INTO PageType VALUES 
(1, "Home", "Landing page for the site, Consists of site template with 3 most recent blog posts, intro content and hashtag links"),
(2, "StaticPage", "Basic business info site (about, contact, locations"),
(3, "BlogHome", "Landing site for the blogs -- allows search, displays the blogs, sorting by hashtags"),
(4, "BlogEntry", "Specific blog entry, following blog and site template"),
(5, "AdminPage", "Admin Portal");

CREATE TABLE SiteData (
	siteId INT NOT NULL AUTO_INCREMENT,
    title VARCHAR(200) NOT NULL,
    content TEXT NOT NULL,
    publishedDate DATE NULL,
    startDate DATE NULL,
    endDate DATE NULL,
    pageTypeId INT NOT NULL,
    userId INT NOT NULL,
    categoryId INT NULL,
    PRIMARY KEY (siteId),
    FOREIGN KEY (userId) REFERENCES Users(userId),
    FOREIGN KEY (categoryId) REFERENCES Category (categoryId),
    FOREIGN KEY (pageTypeId) REFERENCES PageType(pageTypeId)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;

INSERT INTO SiteData VALUES 
(1, "Home", "Welcome to the home page", "2017/05/31", null, null, 1, 1, null),
(2, "All My blogs", "Dive into this blog life with me", "2017/05/31", null, null, 3, 2, null),
(3, "About Us", "Im so sorry Thuan I used the same text here", "2017/06/01", null, null, 2, 3, null),
--  ADDED IN
(7, "Contact Us", "Here is some information about us", "2017/06/01", null, null, 2, 3, null),
(4, "New Vape", "Picked up a new vape, dig the pics, shit's legit", "2017/06/03", null, null, 4, 4, 5),
(5, "Kitties !!!!!", "Out walking I saw all these kitties !!!", "2017/06/05", null, null, 4, 5, 3),
(6, "Admin Page", "Set Up Your Site", null, null, null, 5,1,1);



CREATE TABLE Links (
	linkId INT NOT NULL AUTO_INCREMENT,
    linkName VARCHAR(100) NOT NULL,
    linkReference VARCHAR(500) NOT NULL,
    isMainLink BOOL NOT NULL DEFAULT 0,
    siteId INT NOT NULL,
    positioning INT NULL,
    PRIMARY KEY (linkId),
    FOREIGN KEY (siteId) REFERENCES SiteData(siteId)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;

INSERT INTO Links VALUES 
(1, "Home", "/Page/displayHomePage", true, 1, 1),
(2, "Blogs", "/Page/displayMainBlogPage", true, 2, 4),
(3, "About", "/Page/displayStaticPage", true, 3, 2),
(7, "Contact Us", "/Page/displayStaticPage", true, 7, 3),
-- TWO BELOW NEEED WORK 
(4, "Vape Life", "/blogs/newvape", false, 4, null),
(5, "Kitties", "/blogs/kitties", false, 5, null),
(6, "Admin Page", "/admin", false, 6, 5);




CREATE TABLE Comments( 
	commentId INT NOT NULL AUTO_INCREMENT,
    siteId INT NOT NULL,
    userName VARCHAR(100) NOT NULL,
    title VARCHAR(200) NOT NULL,
    content TEXT NOT NULL,    
    commentDate DATETIME NOT NULL,
    PRIMARY KEY (commentId),
    FOREIGN KEY (siteId) REFERENCES SiteData(siteId)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;

INSERT INTO Comments VALUES 
(1, 4, "admin", "Cool Vape", "Sweet Ass Vape Broseph", "2017/06/10 "),
(2, 4, "admin", "F***boy", "Vaping is stupid and you're stupid", "2017/06/09"),
(3, 4, "admin", "Healthy ?", "I;ve heard it's supposed to be healty ? Is that true", "2017/06/014"),
(4, 5, "admin", "O M G", "Those are SOOOOOO cute", "2017/06/06"),
(5, 5, "admin", "<3<3<3", "I want the grey one ! Where did you see those little scoundrels", "2017/07/09");


CREATE TABLE SitePictureBridge (
	siteId INT NOT NULL,
    pictureId INT NOT NULL,
    PRIMARY KEY (siteId, pictureId),
    FOREIGN KEY (siteId) REFERENCES SiteData(siteId),
    FOREIGN KEY (pictureId) REFERENCES Pictures(pictureId)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;


CREATE TABLE SiteHashTagBridge (
	siteId INT NOT NULL,
    hashTagId INT NOT NULL,
    PRIMARY KEY (siteId, hashTagId),
    
    FOREIGN KEY (siteId) REFERENCES SiteData(siteId),
    FOREIGN KEY (hashTagId) REFERENCES HashTags(hashTagId)

) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;

INSERT INTO SiteHashTagBridge VALUES 
(4, 1),
(4, 2),
(5, 2),
(5, 4),
(5, 5);
